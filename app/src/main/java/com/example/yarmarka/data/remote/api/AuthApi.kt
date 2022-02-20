package com.example.yarmarka.data.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {
    @GET("campus_out")
    fun logout(@Header("x-api-key") token: String): Call<Void>
}