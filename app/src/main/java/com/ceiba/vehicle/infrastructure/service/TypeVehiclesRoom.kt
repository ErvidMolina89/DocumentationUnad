package com.ceiba.vehicle.infrastructure.service

import com.ceiba.infrastructure.data_access.mapper.fromMutableTypeModels
import com.ceiba.vehicle.infrastructure.database.dao.TypeDao
import com.ceiba.vehicle.model.TypeModels
import com.ceiba.vehicle.repository.TypeVariablesReposity
import javax.inject.Inject

class TypeVehiclesRoom @Inject constructor(private val typeDao: TypeDao): TypeVariablesReposity {

    override fun getTypeVehicles():MutableList<TypeModels> {
        return typeDao.getAllType().fromMutableTypeModels()
    }

}