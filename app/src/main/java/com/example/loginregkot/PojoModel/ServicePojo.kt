package com.example.loginregkot.PojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServicePojo {
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("image")
    @Expose
    private var image: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null

    constructor(name: String?, image: String?, id: String?) {
        this.name = name
        this.image = image
        this.id = id
    }


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
}