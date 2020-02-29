package com.example.loginregkot.Adapter

import android.content.Context
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
import com.example.loginregkot.R
import com.squareup.picasso.Picasso

class ChildeServiceAdapter (private var childMutableList: MutableList<ChildServiceModel>)
    : RecyclerView.Adapter<ChildeServiceAdapter.MyViewHolder>() {
    lateinit var spinnerAdapter:ArrayAdapter<String>
    var price:Int = 0
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
        spinnerAdapter = ArrayAdapter(p0.itemView.context, android.R.layout.simple_spinner_item, qtyList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        p0.subServiceSpinner.adapter = spinnerAdapter

        p0.subServiceSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (p0.subServiceSpinner.selectedItem.equals("Select quantity")){
                    price = childModel.getPrice()as Int
                }else{
                    price = childModel.getPrice() as Int*p0.subServiceSpinner.selectedItem as Int
                    addToCart(p0.itemView.context, childModel.getId().toString(),
                        p0.subServiceSpinner.selectedItem.toString(), price.toString())
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

        fun addToCart(context: Context, subId: String, qty: String, price: String) {
            val requestQueue: RequestQueue
            requestQueue = Volley.newRequestQueue(context)
            val stringRequest = object :StringRequest(Request.Method.POST, url, Response.Listener<String> {
                    response ->
                Log.d("ADD_TO_CART", "onResponse: $response")
            }, Response.ErrorListener {

            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("API", "ADD_TO_CART")
                    headers.put("subid", subId)
                    headers.put("phone", "1234567890")
                    headers.put("quantity", qty)
                    headers.put("price", price)
                    return headers
                }
            }
            requestQueue.add(stringRequest)
        }
}