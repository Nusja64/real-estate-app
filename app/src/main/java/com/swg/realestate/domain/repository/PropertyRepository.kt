package com.swg.realestate.domain.repository

import com.swg.realestate.domain.model.Property
import com.swg.realestate.domain.util.ResponseResult

interface PropertyRepository {
    suspend fun getProperties(): ResponseResult<List<Property>?>
}