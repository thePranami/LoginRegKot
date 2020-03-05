package com.example.loginregkot.ResponseModel

import com.example.loginregkot.PojoModel.CartPojoModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CartPojoResponse {
    @SerializedName("msg")
    @Expose
    private var msg: String? = null
    @SerializedName("error")
    @Expose
    private var error: Int? = null
    @SerializedName("data")
    @Expose
    private var data: List<CartPojoModel>? = null

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

    fun getData(): List<CartPojoModel>? {
        return data
    }

    fun setData(data: List<CartPojoModel>) {
        this.data = data
    }
}