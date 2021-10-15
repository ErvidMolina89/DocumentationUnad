package com.ceiba.vehicle.infrastructure.service

import com.ceiba.vehicle.infrastructure.database.dao.TypeDao
import com.ceiba.vehicle.infrastructure.database.dao.VehicleDao
import com.ceiba.vehicle.infrastructure.database.data.DataConfig
import com.ceiba.vehicle.repository.InitializationVariablesReposity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitializationVariablesRoom @Inject constructor(
        private val type: TypeDao,
        private val vehicle: VehicleDao):InitializationVariablesReposity {

    override fun InitializationDefaultVariables() {
        val types = type.getAllType()
        if (types.size == 0){
            GlobalScope.launch {
                onSuccessInsert(type.insertList(
                    DataConfig().inserTypesVehicles()))
            }
        }
    }

    private fun onSuccessInsert(list: List<Long>){
        if(list.size != 0){
            vehicle.insertList(DataConfig().inserVehicles())
        }
    }
}