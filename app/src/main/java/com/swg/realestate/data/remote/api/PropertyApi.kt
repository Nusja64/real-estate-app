package com.swg.realestate.data.remote.api

import com.swg.realestate.data.remote.dto.PropertyResponseDto
import retrofit2.http.GET

interface PropertyApi {
    @GET("properties")
    suspend fun getProperties(): PropertyResponseDto
}