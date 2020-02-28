package com.example.loginregkot.Utility

import com.example.loginregkot.PojoModel.ResultPojoModel
import com.example.loginregkot.ResponseModel.ChildServiceResponse
import com.example.loginregkot.ResponseModel.ServiceResponse
import com.example.loginregkot.ResponseModel.ServiceViewResponse
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface ApiInterface {

    @FormUrlEncoded
    @POST("index.php")
     fun registerUser(@Field("API") API:String,
                     @Field("name") name:String,
                     @Field("phone") phone:String,
                     @Field("email") email:String
    ) : Call<ResultPojoModel>

    @FormUrlEncoded
    @POST("index.php")
    fun loginUser(@Field("API") API:String,
                  @Field("phone") phone:String
    ) : Call<ResultPojoModel>

    @FormUrlEncoded
    @POST("index.php")
    fun getService(@Field("API") API:String)
    : Call<ServiceResponse>

    //
    @FormUrlEncoded
    @POST("index.php")
    fun getAllService(@Field("API") API:String,
                      @Field("serviceid") serviceid:String)
    : Call<ServiceViewResponse>

    //
    @POST("index.php")
    @FormUrlEncoded
    fun getChildService(@Field("API") API:String,
                        @Field("service_id") service_id:String,
                        @Field("mobile") mobile:String)
    : Call<ChildServiceResponse>
}

