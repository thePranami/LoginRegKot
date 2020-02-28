package com.example.loginregkot.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.loginregkot.PojoModel.ChildServiceModel
import com.example.loginregkot.R
import com.squareup.picasso.Picasso

class ChildeServiceAdapter (private var childMutableList: MutableList<ChildServiceModel>)
    : RecyclerView.Adapter<ChildeServiceAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.child_sub_service, p0, false)
    return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val childModel = childMutableList[p1]
        p0.subServiceName.text= childModel.getServiceName()
        p0.subServicePrice.text = childModel.getPrice()
        p0.subServiceQuantity.text = childModel.getQuantity()
    }

    override fun getItemCount(): Int {
        return childMutableList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var subServiceName: TextView
        var subServicePrice: TextView
        var subServiceQuantity: TextView

        init {
            subServiceName = itemView.findViewById<TextView>(R.id.subServiceName)
            subServicePrice = itemView.findViewById<TextView>(R.id.subServicePrice)
            subServiceQuantity = itemView.findViewById<TextView>(R.id.subServiceQuantity)
        }
    }
}