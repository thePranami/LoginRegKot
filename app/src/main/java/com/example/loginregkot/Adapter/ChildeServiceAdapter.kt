package com.example.loginregkot.Adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.loginregkot.PojoModel.ChildServiceModel
import com.example.loginregkot.PojoModel.ResultPojoModel
import com.example.loginregkot.R
import com.example.loginregkot.ResponseModel.ServiceResponse
import com.example.loginregkot.Utility.ApiInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChildeServiceAdapter (private var childMutableList: MutableList<ChildServiceModel>)
    : RecyclerView.Adapter<ChildeServiceAdapter.MyViewHolder>() {
    lateinit var spinnerAdapter:ArrayAdapter<String>
    lateinit var price:String
    val url:String = "https://loopintechies.com/homeservice/android/index.php/"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.child_sub_service, p0, false)
    return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val childModel = childMutableList[p1]
        p0.subServiceName.text= childModel.getServiceName()
        p0.subServicePrice.text = "₹ "+childModel.getPrice()
        //p0.subServiceQuantity.text = childModel.getQuantity()
        val qtyList = arrayOf("Select quantity","1","2","3","4","5","6","7")
        val qty: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        spinnerAdapter = ArrayAdapter(p0.itemView.context, android.R.layout.simple_spinner_item, qtyList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        p0.subServiceSpinner.adapter = spinnerAdapter

        p0.subServiceSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (p0.subServiceSpinner.selectedItemPosition==0){
                    price = childModel.getPrice().toString()
                    p0.subServicePrice.text = "₹ "+price
                }else if (p0.subServiceSpinner.selectedItemPosition>0){
                    val pr1 = childModel.getPrice()?.toInt()
                    val pr2 = p0.subServiceSpinner.selectedItemPosition
                    if (pr1 != null) {
                        price = (pr1*pr2).toString()
                        p0.subServicePrice.text = "₹ "+price
                        //addToCart(p0.itemView.context, childModel.getId().toString(),
                         //   "1234567890", p0.subServiceSpinner.selectedItem.toString(), price)
                        addToCartRetro(p0.itemView.context, childModel.getId().toString(),
                            "1234567890", p0.subServiceSpinner.selectedItem.toString(), price)
                        Log.e("aaaaa", price)
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun getItemCount(): Int {
        return childMutableList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var subServiceName: TextView
        var subServicePrice: TextView
        var subServiceQuantity: TextView
        var subServiceSpinner: Spinner

        init {
            subServiceName = itemView.findViewById<TextView>(R.id.subServiceName)
            subServicePrice = itemView.findViewById<TextView>(R.id.subServicePrice)
            subServiceQuantity = itemView.findViewById<TextView>(R.id.subServiceQuantity)
            subServiceSpinner = itemView.findViewById<Spinner>(R.id.subServiceSpinner)
        }
    }

        fun addToCart(context: Context, subId: String, phone:String, qty: String, price: String) {
            Toast.makeText(context, "fetch", Toast.LENGTH_SHORT).show()
            val requestQueue: RequestQueue = Volley.newRequestQueue(context)
            var stringRequest = object :StringRequest(Method.POST, url, Response.Listener<String> {
                    response ->
                Log.e("ADD_TO_CART", "onResponse: ${response}")
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("API", "ADD_TO_CART")
                    headers.put("subid", subId)
                    headers.put("phone", phone)
                    headers.put("quantity", qty)
                    headers.put("price", price)
                    return headers
                }
            }
            requestQueue.add(stringRequest)
        }
    fun addToCartRetro(context: Context, subId: String, phone:String, qty: String, price: String) {
        val retrofit = Retrofit.Builder().baseUrl("https://loopintechies.com/homeservice/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface: ApiInterface
        apiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<ResultPojoModel> = apiInterface.addToCartData("ADD_TO_CART",
            subId, phone, qty, price)
        call.enqueue(object : Callback<ResultPojoModel> {
            override fun onResponse(call: Call<ResultPojoModel>, response: retrofit2.Response<ResultPojoModel>) {
                Log.e("ADD_TO_CART", "onResponse: ${response}")
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<ResultPojoModel>, t: Throwable) {
                Log.e("response", "throw   "+t.message)
            }
        })
    }

}

