package com.example.loginregkot.ResponseModel

import com.example.loginregkot.PojoModel.ChildServiceModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ChildServiceResponse {
    @SerializedName("msg")
    @Expose
    private var msg: String? = null
    @SerializedName("error")
    @Expose
    private var error: Int? = null
    @SerializedName("Service")
    @Expose
    private var service: List<ChildServiceModel>? = null

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String) {
        this.msg = msg
    }

    fun getError(): Int? {
        return error
    }

    fun setError(error: Int?) {
        this.error = error
    }

    fun getService(): List<ChildServiceModel>? {
        return service
    }

    fun setService(service: List<ChildServiceModel>) {
        this.service = service
    }
}