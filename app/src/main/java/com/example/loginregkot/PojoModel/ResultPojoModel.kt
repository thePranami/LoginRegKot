package com.example.loginregkot.PojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultPojoModel {
    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("error")
    @Expose
    var error: String? = null
}

