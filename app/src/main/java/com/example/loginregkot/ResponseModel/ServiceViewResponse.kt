package com.example.loginregkot.ResponseModel

import com.example.loginregkot.PojoModel.ServiceViewModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServiceViewResponse {
    @SerializedName("error")
    @Expose
    private var error: Int? = null
    @SerializedName("data")
    @Expose
    private var data: List<ServiceViewModel>? = null

    fun getError(): Int? {
        return error
    }

    fun setError(error: Int?) {
        this.error = error
    }

    fun getData(): List<ServiceViewModel>? {
        return data
    }

    fun setData(data: List<ServiceViewModel>) {
        this.data = data
    }
}