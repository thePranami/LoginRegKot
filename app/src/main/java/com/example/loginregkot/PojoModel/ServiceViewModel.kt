package com.example.loginregkot.PojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServiceViewModel {
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("image")
    @Expose
    private var image: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("price")
    @Expose
    private var price: String? = null
    @SerializedName("total_service")
    @Expose
    private var totalService: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String) {
        this.price = price
    }

    fun getTotalService(): String? {
        return totalService
    }

    fun setTotalService(totalService: String) {
        this.totalService = totalService
    }
}