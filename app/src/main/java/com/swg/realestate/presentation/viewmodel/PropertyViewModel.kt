package com.swg.realestate.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swg.realestate.domain.repository.PropertyRepository
import com.swg.realestate.domain.util.ResponseResult
import com.swg.realestate.domain.util.toMessage
import com.swg.realestate.presentation.mapper.toUi
import com.swg.realestate.presentation.model.PropertyUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PropertyViewModel(
    private val repository: PropertyRepository
) : ViewModel() {


    private val _propertyState =
        MutableStateFlow<ResponseResult<List<PropertyUiModel>>>(ResponseResult.Loading)

    val propertyState: StateFlow<ResponseResult<List<PropertyUiModel>>> =
        _propertyState.asStateFlow()

    init {
        getProperties()
    }

    fun getProperties() {
        viewModelScope.launch {

            _propertyState.value = ResponseResult.Loading

            when (val result = repository.getProperties()) {

                is ResponseResult.Success -> {

                    val uiList = result.data
                        ?.map { it.toUi() }
                        ?: emptyList()

                    _propertyState.value = ResponseResult.Success(uiList)
                    Log.d("PropertyViewModel data", "SUCCESS: $uiList.re")
                }

                is ResponseResult.Error -> {
                    Log.e("PropertyViewModel", "ERROR: ${result.error.toMessage()}")
                    _propertyState.value = result

                }

                is ResponseResult.Loading -> {
                    _propertyState.value = ResponseResult.Loading
                }
            }
        }
    }
}

