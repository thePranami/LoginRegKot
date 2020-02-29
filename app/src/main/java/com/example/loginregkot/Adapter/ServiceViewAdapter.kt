package com.example.loginregkot.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.loginregkot.PojoModel.ServiceViewModel
import com.example.loginregkot.R
import com.squareup.picasso.Picasso

class ServiceViewAdapter(var dataList: MutableList<ServiceViewModel>, var onItemClck: OnItemClick): RecyclerView.Adapter<ServiceViewAdapter.MyViewHolder>() {
    //private lateinit var onItemClck: OnItemClick
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(p0.context)
            .inflate(R.layout.service_view, p0, false))
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val serviceViewModel = dataList[p1]
        p0.serviceViewName.text = serviceViewModel.getName()
        Picasso.with(p0.itemView.context).load(serviceViewModel.getImage()).into(p0.serviceViewImage)
        p0.clickBind("CHILD_SERVICE",serviceViewModel.getId().toString(),"1234567890", serviceViewModel.getName().toString(), onItemClck)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var serviceViewName: TextView
        var serviceViewImage: ImageView

        init {
            serviceViewName = itemView.findViewById<TextView>(R.id.serviceViewName)
            serviceViewImage = itemView.findViewById<ImageView>(R.id.serviceViewImage)
        }
         fun clickBind(API:String, serviceid:String, mobile:String, childServiceName:String, onItemClick: OnItemClick) {
            itemView.setOnClickListener {
                onItemClick.clickItem(API, serviceid, mobile, childServiceName)
            }
        }
    }
    interface OnItemClick{
        fun clickItem(API:String, serviceid:String, mobile:String, childServiceName:String)
    }
}