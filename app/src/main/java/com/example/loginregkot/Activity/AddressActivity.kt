package com.example.loginregkot.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.loginregkot.Adapter.AddressAdapter
import com.example.loginregkot.PojoModel.AddressModel
import com.example.loginregkot.R
import com.example.loginregkot.ResponseModel.AddressModelResponse
import com.example.loginregkot.Utility.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    var addressList: MutableList<AddressModel> = mutableListOf()
    lateinit var addressAdapter: AddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        recyclerView=findViewById(R.id.addressRecycler)
        getAddress()

    }

    private fun getAddress(){
        val retrofit:Retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterFace: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call:Call<AddressModelResponse> = apiInterFace.getAddressData("ADDRESS_LIST", "8081448062")
        call.enqueue(object :Callback<AddressModelResponse>{
            override fun onResponse(call: Call<AddressModelResponse>, response: Response<AddressModelResponse>) {
                addressList.addAll(response.body()?.getData()!!)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                addressAdapter = AddressAdapter(applicationContext, addressList)
                recyclerView.adapter = addressAdapter
            }

            override fun onFailure(call: Call<AddressModelResponse>, t: Throwable) {

            }
        })
    }
}
