package com.example.loginregkot.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.loginregkot.PojoModel.CartPojoModel
import com.example.loginregkot.R
import kotlinx.android.synthetic.main.proceed_layout.view.*

class CartAdapter(private var cartList: MutableList<CartPojoModel>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val FOOTER_ITEM = 1
    private val HEADER_ITEM = 2

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val layoutInflater:LayoutInflater= LayoutInflater.from(p0.context)
        var view:View
        if (p1==HEADER_ITEM){
            view = layoutInflater.inflate(com.example.loginregkot.R.layout.cart_view, p0, false)
            return MyViewHolder(view)
        }else if (p1==FOOTER_ITEM){
            view = layoutInflater.inflate(com.example.loginregkot.R.layout.proceed_layout, p0, false)
            return FooterViewHolder(view)
        }
        return null!!
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
     if (p0 is MyViewHolder){
        val cartModel = cartList[p1]
         p0.cartItemName.text = cartModel.getService()
         p0.cartItemQty.text = cartModel.getQuantity()
         p0.cartItemPrice.text = "₹ "+cartModel.getPrice()
//        p0.cartItemName.text = cartModel?.get(p1)?.getService()
//        p0.cartItemQty.text = cartModel?.get(p1)?.getQuantity()
//        p0.cartItemPrice.text = "₹ "+cartModel?.get(p1)?.getPrice()
     }else if (p0 is FooterViewHolder){
         p0.proceedButton.text = "Proceed"
        Toast.makeText(p0.itemView.context, "Footer", Toast.LENGTH_LONG).show()
     }
    }

    override fun getItemCount(): Int {
        return if (cartList.isEmpty()){
            0
        }else {
            cartList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == cartList.size+1){
            FOOTER_ITEM
        }else{
            HEADER_ITEM
        }
    }

     class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var cartItemName:TextView
        lateinit var cartItemQty:TextView
        lateinit var cartItemPrice:TextView
        init {
            cartItemName=itemView.findViewById(com.example.loginregkot.R.id.cartItemName)
            cartItemQty=itemView.findViewById(com.example.loginregkot.R.id.cartItemQty)
            cartItemPrice=itemView.findViewById(com.example.loginregkot.R.id.cartItemPrice)
        }
    }
    class FooterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var proceedButton:Button
        init {
            proceedButton = itemView.findViewById(R.id.proceedCartButton)
        }
    }
}

