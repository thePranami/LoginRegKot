package com.example.loginregkot.PojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChildServiceModel {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("service_name")
    @Expose
    private var serviceName: String? = null
    @SerializedName("price")
    @Expose
    private var price: String? = null
    @SerializedName("quantity")
    @Expose
    private var quantity: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getServiceName(): String? {
        return serviceName
    }

    fun setServiceName(serviceName: String) {
        this.serviceName = serviceName
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String) {
        this.price = price
    }

    fun getQuantity(): String? {
        return quantity
    }

    fun setQuantity(quantity: String) {
        this.quantity = quantity
    }

}