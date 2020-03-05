package com.example.loginregkot.PojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class CartPojoModel {
    @SerializedName("service")
    @Expose
    private var service: String? = null
    @SerializedName("quantity")
    @Expose
    private var quantity: String? = null
    @SerializedName("price")
    @Expose
    private var price: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null

    fun getService(): String? {
        return service
    }

    fun setService(service: String) {
        this.service = service
    }

    fun getQuantity(): String? {
        return quantity
    }

    fun setQuantity(quantity: String) {
        this.quantity = quantity
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String) {
        this.price = price
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

}