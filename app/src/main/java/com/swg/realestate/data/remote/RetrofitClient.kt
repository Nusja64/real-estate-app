package com.swg.realestate.data.remote

import com.swg.realestate.data.remote.api.PropertyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://private-9f1bb1-homegate3.apiary-mock.com/"

    val api: PropertyApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PropertyApi::class.java)
    }
}