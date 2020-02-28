package com.example.loginregkot.Adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.loginregkot.Activity.ServiceViewActivity
import com.example.loginregkot.R
import com.example.loginregkot.PojoModel.ServicePojo
import com.squareup.picasso.Picasso

class ServiceAdapter(private var dataList: MutableList<ServicePojo>) : RecyclerView.Adapter<ServiceAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val servicePojo = dataList[position]
        holder.serviceName.text = servicePojo.getName()
        Picasso.with(holder.itemView.context).load(servicePojo.getImage()).into(holder.serviceImage)
        holder.serviceImage.setOnClickListener {
            val intent = Intent(holder.itemView.context, ServiceViewActivity::class.java)
            intent.putExtra("serviceId", servicePojo.getId())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

     class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
         var serviceImage: ImageView
         var serviceName: TextView

         init {
             serviceName = itemView.findViewById<TextView>(R.id.serviceName)
             serviceImage = itemView.findViewById<ImageView>(R.id.serviceImage)
         }
     }
}