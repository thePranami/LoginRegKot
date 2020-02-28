package com.example.loginregkot.Activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.loginregkot.Utility.ApiInterface
import com.example.loginregkot.R
import com.example.loginregkot.PojoModel.ResultPojoModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val context: Context =this.applicationContext
        val phone = findViewById<EditText>(R.id.phone)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.pass)
        val login = findViewById<Button>(R.id.login)
        val newUser = findViewById<TextView>(R.id.newUser)
        val sharedPreference : SharedPreferences = this.getSharedPreferences("MyData", Context.MODE_PRIVATE)
        newUser.setOnClickListener {
            val loginIntent = Intent(context, RegisterActivity::class.java)
            startActivity(loginIntent)
        }
        login.setOnClickListener {
            userLogin()
//            var inputEmail = email.text.toString()
//            var inputPassword = password.text.toString()
//            //assert(inputEmail.equals(sharedPreference.getString("EMAIL", "")))
//            if(inputEmail.equals(sharedPreference.getString("EMAIL", ""))
//                && inputPassword.equals(sharedPreference.getString("PASSWORD", ""))){
//                val myToast = Toast.makeText(context,"Login Success", Toast.LENGTH_SHORT)
//                myToast.setGravity(Gravity.CENTER, 0, 0)
//                myToast.show()
//            }else{
//                Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
//            }
        }
    }
    fun userLogin(){
        val progressDialog = ProgressDialog(applicationContext)
        progressDialog.setMessage("User is being registered")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        val retrofit  = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiInterface: ApiInterface
        apiInterface= retrofit.create(ApiInterface::class.java)
        val call : Call<ResultPojoModel> = apiInterface.loginUser("LOGIN", phone.text.toString())
        call.enqueue(object : Callback<ResultPojoModel>{
            override fun onResponse(call: Call<ResultPojoModel>, response: Response<ResultPojoModel>) {
                progressDialog.dismiss()
                if (response.isSuccessful){
                    Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ResultPojoModel>, t: Throwable) {

            }
        })
    }
}
