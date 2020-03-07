package com.example.loginregkot.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.example.loginregkot.Adapter.CartAdapter
import com.example.loginregkot.PojoModel.CartPojoModel
import com.example.loginregkot.R
import com.example.loginregkot.ResponseModel.CartPojoResponse
import com.example.loginregkot.Utility.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var cartAdapter: CartAdapter
    var cartList: MutableList<CartPojoModel> = mutableListOf()
    lateinit var proceedButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recyclerView = findViewById(R.id.cartRecycler)
        proceedButton = findViewById(R.id.proceedButton)
        proceedButton.setOnClickListener {
            val addIntent = Intent(applicationContext, AddressActivity::class.java)
            startActivity(addIntent)
        }
        getCartData()
    }

    private fun getCartData(){
        val retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var apiInterface:ApiInterface
        apiInterface = retrofit.create(ApiInterface::class.java)
        val call:Call<CartPojoResponse> = apiInterface.getCartItem("SHOW_CART", "1234567890")
        call.enqueue(object :Callback<CartPojoResponse>{
            override fun onResponse(call: Call<CartPojoResponse>, response: Response<CartPojoResponse>) {
                cartList.addAll(response.body()?.getData()!!)
                cartAdapter = CartAdapter(cartList)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = cartAdapter
            }

            override fun onFailure(call: Call<CartPojoResponse>, t: Throwable) {

            }
        })
    }
}
