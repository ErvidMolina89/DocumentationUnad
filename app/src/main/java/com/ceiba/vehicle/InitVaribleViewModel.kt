package com.ceiba.vehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.vehicle.infrastructure.service.InitializationVariablesRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InitVaribleViewModel @Inject constructor(
    private val room: InitializationVariablesRoom
    ): ViewModel() {

    fun validateDbAndInsertVariablesInit(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                room.InitializationDefaultVariables()
            }
        }
    }
}