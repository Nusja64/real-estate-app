package com.swg.realestate.data.repository

import android.util.Log
import com.swg.realestate.data.mapper.toDomain
import com.swg.realestate.data.remote.api.PropertyApi
import com.swg.realestate.domain.model.Property
import com.swg.realestate.domain.repository.PropertyRepository
import com.swg.realestate.domain.util.AppError
import com.swg.realestate.domain.util.ResponseResult
import retrofit2.HttpException
import java.io.IOException

class PropertyRepositoryImpl(
    private val api: PropertyApi
) : PropertyRepository {

    override suspend fun getProperties(): ResponseResult<List<Property>?> {
        return try {
            val response = api.getProperties()
            Log.d("API_DEBUG", "Response: $response")

            val data = response.toDomain()
            Log.d("API_DEBUG", "Mapped data: $data")

            ResponseResult.Success(data)

        } catch (e: IOException) {
            Log.e("API_ERROR", "No network error ${e.message}")
            ResponseResult.Error(AppError.NoInternet, e)

        } catch (e: HttpException) {
            Log.e("API_ERROR", "HttpException error ${e.message}")
            ResponseResult.Error(AppError.Server, e)

        } catch (e: Exception) {
            Log.e("API_ERROR", "Exception error data: ${e.message}")
            ResponseResult.Error(AppError.Unknown(e.message), e)
        }
    }
}
