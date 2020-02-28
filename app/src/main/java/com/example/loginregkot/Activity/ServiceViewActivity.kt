package com.example.loginregkot.Activity

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.loginregkot.Adapter.ChildeServiceAdapter
import com.example.loginregkot.Adapter.ServiceViewAdapter
import com.example.loginregkot.Utility.ApiInterface
import com.example.loginregkot.PojoModel.ChildServiceModel
import com.example.loginregkot.PojoModel.ServiceViewModel
import com.example.loginregkot.R
import com.example.loginregkot.ResponseModel.ChildServiceResponse
import com.example.loginregkot.ResponseModel.ServiceViewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceViewActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    var serviceId: String? =null
    private var dataList: MutableList<ServiceViewModel> = mutableListOf()
    private var childList: MutableList<ChildServiceModel> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    private lateinit var serviceAdapter: ServiceViewAdapter
    private lateinit var childeServiceAdapter: ChildeServiceAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_view)

        recyclerView = findViewById(R.id.serviceViewRecycler)
        val intent = intent
        serviceId = intent.getStringExtra("serviceId").toString()
        getServiceView("SUBSERVICE", serviceId!!)
    }

   private fun getServiceView(API:String, serviceId:String){
       progerssProgressDialog= ProgressDialog(this)
       progerssProgressDialog.setTitle("Loading...")
       progerssProgressDialog.setCancelable(false)
       progerssProgressDialog.show()
       val retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
       val apiInterface: ApiInterface
       apiInterface = retrofit.create(ApiInterface::class.java)
       val call: Call<ServiceViewResponse> = apiInterface.getAllService(API, serviceId)
       call.enqueue(object : Callback<ServiceViewResponse> {
           override fun onResponse(call: Call<ServiceViewResponse>, response: Response<ServiceViewResponse>) {
               progerssProgressDialog.dismiss()
               dataList.addAll(response.body()?.getData()!!)
               serviceAdapter = ServiceViewAdapter(dataList, object :
                   ServiceViewAdapter.OnItemClick {
                   override fun clickItem(API: String, serviceid: String, mobile: String) {
                       getChildServiceView(API, serviceid, mobile)
                   }
               })
               recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
               recyclerView.adapter = serviceAdapter
               serviceAdapter.notifyDataSetChanged()
               //var serviceId = dataList.get().getId()
               Log.e("response", "response"+response.body().toString())
           }
           override fun onFailure(call: Call<ServiceViewResponse>, t: Throwable) {
               progerssProgressDialog.dismiss()
               Log.e("response", "throw   "+t.message)
           }
       })
   }

    private fun getChildServiceView(API:String, service_id:String, mobile:String){
        progerssProgressDialog= ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        val retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface: ApiInterface
        apiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<ChildServiceResponse> = apiInterface.getChildService(API, service_id, mobile)
        call.enqueue(object : Callback<ChildServiceResponse> {
            override fun onResponse(call: Call<ChildServiceResponse>, response: Response<ChildServiceResponse>) {
                progerssProgressDialog.dismiss()
                childList.clear()
                childList.addAll(response.body()?.getService()!!)
                childeServiceAdapter = ChildeServiceAdapter(childList)
                val childServiceRecycler = findViewById<RecyclerView>(R.id.childServiceRecycler)
                childServiceRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                childServiceRecycler.adapter = childeServiceAdapter
                Log.e("response", "response"+response.body().toString())
            }
            override fun onFailure(call: Call<ChildServiceResponse>, t: Throwable) {
                progerssProgressDialog.dismiss()
                Log.e("response", "throw   "+t.message)
            }
        })
    }
}
