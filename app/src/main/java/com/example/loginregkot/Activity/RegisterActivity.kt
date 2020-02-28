package com.example.loginregkot.Activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.loginregkot.*
import com.example.loginregkot.Adapter.ServiceAdapter
import com.example.loginregkot.PojoModel.ResultPojoModel
import com.example.loginregkot.PojoModel.ServicePojo
import com.example.loginregkot.ResponseModel.ServiceResponse
import com.example.loginregkot.Utility.ApiInterface
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList: MutableList<ServicePojo> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val context:Context=this.applicationContext
        val name  = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.pass)
        val register = findViewById<Button>(R.id.register)
        recyclerView = findViewById(R.id.serviceRecycler)
        val alreadyRegister = findViewById<TextView>(R.id.alreadyRegister)
        val sharedPreference : SharedPreferences = this.getSharedPreferences("MyData", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        alreadyRegister.setOnClickListener {
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        getAllService()
        register.setOnClickListener {
            registerUser()
            val inpuName = name.text
            val inputPhone = phone.text
            val inputEmail = email.text
            val inputPassword = password.text
            editor.putString("EMAIL", inputEmail.toString())
            editor.putString("PASSWORD", inputPassword.toString())
            editor.commit()
            val myToast = Toast.makeText(context,"Registration Success",Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.CENTER, 0, 0)
            myToast.show()
        }
    }
     fun registerUser(){
         val progressBar = ProgressBar(applicationContext)
         val layoutParams = LinearLayout.LayoutParams(
             ViewGroup.LayoutParams.WRAP_CONTENT,
             ViewGroup.LayoutParams.WRAP_CONTENT
         )
         layoutParams.gravity=Gravity.CENTER
         progressBar.layoutParams = layoutParams
         val layout = findViewById<LinearLayout>(R.id.registerLayout)
         layout?.addView(progressBar)
         progressBar.visibility=View.VISIBLE
        val retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface: ApiInterface
        apiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<ResultPojoModel> = apiInterface.registerUser("USERREGISTRATION",
            name.text.toString(), phone.text.toString(), email.text.toString())
        call.enqueue(object : Callback<ResultPojoModel> {
            override fun onResponse(call: Call<ResultPojoModel>, response: Response<ResultPojoModel>) {
                progressBar.visibility=View.GONE
                if (response.isSuccessful){
                    Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ResultPojoModel>, t: Throwable) {

            }
        })
    }
    fun getAllService(){
        progerssProgressDialog=ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        val retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface: ApiInterface
        apiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<ServiceResponse> = apiInterface.getService("SERVICE")
        call.enqueue(object : Callback<ServiceResponse>{
            override fun onResponse(call: Call<ServiceResponse>, response: Response<ServiceResponse>) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response.body()?.getService()!!)
                serviceAdapter = ServiceAdapter(dataList)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = serviceAdapter
                serviceAdapter.notifyDataSetChanged()
                //var serviceId = dataList.get().getId()
                Log.e("response", "response"+response.body().toString())
                Log.e("response", "data"+dataList)
            }
            override fun onFailure(call: Call<ServiceResponse>, t: Throwable) {
                progerssProgressDialog.dismiss()
                Log.e("response", "throw   "+t.message)
            }
        })
    }
}
