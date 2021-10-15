package com.ceiba.vehicle.ui.add.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.vehicle.R
import com.ceiba.vehicle.infrastructure.service.TypeVehiclesRoom
import com.ceiba.vehicle.model.TypeModels
import com.ceiba.vehicle.ui.add.interfaz.InitializationViewModelDelegate
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@SuppressLint("StaticFieldLeak")
@HiltViewModel
class InitializationFragmentFieldsViewModel @Inject constructor(
    private val room: TypeVehiclesRoom,
    @ApplicationContext private val context: Context) : ViewModel() {

    private lateinit var delegate: InitializationViewModelDelegate
    private var types: MutableList<TypeModels> = emptyList<TypeModels>().toMutableList()

    fun getTypesVehicle(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                types = room.getTypeVehicles()
                delegate.fillSpinnerTypeRoom()
            }
        }
    }

    fun fillSpinnerPassenger(spinner: Spinner){
        val array = R.array.passanger_array
        ArrayAdapter.createFromResource(
            context,
            array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun fillSpinnerTypeVehicle(spinner: Spinner){
            ArrayAdapter<TypeModels>(
                context,
                android.R.layout.simple_spinner_item,
                types as ArrayList<TypeModels>
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
    }





    fun setDelegate(delegate: InitializationViewModelDelegate) {
        this.delegate = delegate
    }
}