package com.ceiba.vehicle.ui.delete.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.factoryimplementation.util.filterListVehcile
import com.ceiba.vehicle.databinding.FragmentDeleteBinding
import com.ceiba.vehicle.infrastructure.service.VehiclesRoom
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.delete.adapter.DeleteRecyclerViewAdapter
import com.ceiba.vehicle.ui.delete.interfaz.DeleteViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(
    private val room: VehiclesRoom
): ViewModel() {
    private lateinit var delegate: DeleteViewModelDelegate
    private var list: MutableList<VehicleModel>? = emptyList<VehicleModel>().toMutableList()
    private lateinit var adapter: DeleteRecyclerViewAdapter

    fun listenerRecycler(adapter: DeleteRecyclerViewAdapter){
        this.adapter = adapter
        adapter.let {
            adapter.onClickListener {
                delegate.responseOnClickItemRecycler(it)
            }
        }
    }

    fun listenerEditTextSearch(binding: FragmentDeleteBinding){
        binding.editTextSearchDelete.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() != ""){
                    val filters = list?.let { p0.toString().filterListVehcile(it) }
                    if (filters?.size == 0) {
                        delegate.responseEmptySearch()
                    }
                    filters?.let { adapter.setData(it) }
                }else list?.let { adapter.setData(it) }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    fun getDataListVehicle(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                list = room.getListVehicle()
                if (list?.size == 0){
                    delegate.responseEmptyListVehicle()
                }else delegate.responseGetListVehicle(list)
            }
        }
    }

    fun setDataListRecycler(){
        list?.let { adapter.setData(it)}
    }

    fun deleteVehicle(vehicle: VehicleModel?){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val delete = room.deleteVehicleSelected(vehicle)
                if (delete == null || delete == 0) {
                    delegate.responseErrorDelete()
                } else {
                    delegate.responseDeleteCorrect()
                    getDataListVehicle()
                }
            }
        }
    }


    fun setDelegate(delegate: DeleteViewModelDelegate) {
        this.delegate = delegate
    }
}