package com.example.loginregkot.ResponseModel

import com.example.loginregkot.PojoModel.ServicePojo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ServiceResponse {
    @SerializedName("data")
    @Expose
    private var service: List<ServicePojo>? = null

    fun getService(): List<ServicePojo>? {
        return service
    }

    fun setService(service: List<ServicePojo>) {
        this.service = service
    }
}