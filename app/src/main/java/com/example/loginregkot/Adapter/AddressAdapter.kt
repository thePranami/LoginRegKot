package com.example.loginregkot.Adapter

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.example.loginregkot.PojoModel.AddressModel
import com.example.loginregkot.R
import kotlinx.android.synthetic.main.address_view.view.*

class AddressAdapter :RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    private var context:Context
    private var addressList:MutableList<AddressModel>
    lateinit var addressId:String

    constructor(context:Context, addressList:MutableList<AddressModel>){
        this.context=context
        this.addressList=addressList
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.address_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return addressList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val addressModel:AddressModel = addressList[p1]
        p0.oldAddress = addressModel.getAID().toString()
        p0.addressHolderName.text = addressModel.getName()
        p0.itemView.houseName.text = addressModel.getHouse()
        p0.itemView.localityName.text = addressModel.getLocality()
        p0.itemView.addressType.text = addressModel.getAType()
        p0.itemView.addressRadioButton.isChecked = p1 == p0.unCheck
        addressId = addressModel.getAID().toString()

        p0.itemView.addressRadioButton.setOnClickListener {
            p0.unCheck
            p0.oldAddress = addressModel.getAID().toString()
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var addressHolderName:TextView
        private var addressRadioButton:RadioButton
        var unCheck = -1
        lateinit var oldAddress: String
        private var houseName:TextView
        private var localityName:TextView
        private var addressType:TextView
        private var editAddress:Button
        private var deleteAddress:Button
        init {
            addressRadioButton=itemView.findViewById(R.id.addressRadioButton)
            addressHolderName=itemView.findViewById(R.id.addressHolderName)
            houseName=itemView.findViewById(R.id.houseName)
            localityName=itemView.findViewById(R.id.localityName)
            addressType=itemView.findViewById(R.id.addressType)
            editAddress=itemView.findViewById(R.id.editAddress)
            deleteAddress=itemView.findViewById(R.id.deleteAddress)

            val onClickListener = View.OnClickListener {
                unCheck=adapterPosition
            }
            itemView.setOnClickListener(onClickListener)
            addressRadioButton.setOnClickListener(onClickListener)
        }
    }
}