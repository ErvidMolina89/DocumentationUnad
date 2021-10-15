package com.ceiba.infrastructure.data_access.mapper

import com.ceiba.vehicle.infrastructure.database.entity.TypeEntity
import com.ceiba.vehicle.model.TypeModels


fun List<TypeEntity>.fromMutableTypeModels():MutableList<TypeModels> {
    val list = emptyList<TypeModels>().toMutableList()
    this.forEach {
        val aggregate = TypeModels(it.id, it.name)
        list.add(aggregate)
    }
    return list
}
