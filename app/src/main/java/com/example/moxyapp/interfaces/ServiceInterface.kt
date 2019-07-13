package com.example.moxyapp.interfaces

import com.example.moxyapp.models.ProductsList
import com.example.moxyapp.models.Token
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ServiceInterface {

    @FormUrlEncoded
    @POST("api-token-auth/")
    fun login(@Field("username") username: String, @Field("password") password: String): Call<Token>

    @GET("api/main/product/")
    fun getProducts() : Call<ProductsList>
}