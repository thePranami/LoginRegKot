package com.example.loginregkot.PojoModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class AddressModel {
    @SerializedName("AID")
    @Expose
    private var aID: String? = null
    @SerializedName("Title")
    @Expose
    private var title: String? = null
    @SerializedName("Name")
    @Expose
    private var name: String? = null
    @SerializedName("House")
    @Expose
    private var house: String? = null
    @SerializedName("Locality")
    @Expose
    private var locality: String? = null
    @SerializedName("AType")
    @Expose
    private var aType: String? = null
    @SerializedName("Email")
    @Expose
    private var email: String? = null
    @SerializedName("phone")
    @Expose
    private var phone: String? = null
    @SerializedName("Status")
    @Expose
    private var status: String? = null

    fun getAID(): String? {
        return aID
    }

    fun setAID(aID: String) {
        this.aID = aID
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getHouse(): String? {
        return house
    }

    fun setHouse(house: String) {
        this.house = house
    }

    fun getLocality(): String? {
        return locality
    }

    fun setLocality(locality: String) {
        this.locality = locality
    }

    fun getAType(): String? {
        return aType
    }

    fun setAType(aType: String) {
        this.aType = aType
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

}