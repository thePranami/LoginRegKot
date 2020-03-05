package com.example.loginregkot.ResponseModel

import com.example.loginregkot.PojoModel.AddressModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddressModelResponse {
    @SerializedName("msg")
    @Expose
    private var msg: String? = null
    @SerializedName("error")
    @Expose
    private var error: String? = null
    @SerializedName("data")
    @Expose
    private var data: List<AddressModel>? = null

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String) {
        this.msg = msg
    }

    fun getError(): String? {
        return error
    }

    fun setError(error: String) {
        this.error = error
    }

    fun getData(): List<AddressModel>? {
        return data
    }

    fun setData(data: List<AddressModel>) {
        this.data = data
    }
}