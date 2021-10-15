package com.ceiba.infrastructure.data_access.mapper

import com.ceiba.vehicle.infrastructure.database.entity.VehicleEntity
import com.ceiba.vehicle.model.VehicleModel


fun VehicleModel.fromVehicleEntity(): VehicleEntity {
   val entity =  VehicleEntity(this.placa, this.modelo, this.image,
       this.color, this.typeVehicle, this.pasageros)
    entity.id = this.id
    return entity
}

fun VehicleEntity.fromVehicleModel(): VehicleModel {
    val model = VehicleModel(this.plate, this.typeID, this.image,
        this.color, this.modelo, this.pasagero)
    model.id = this.id
    return model
}

fun List<VehicleEntity>.fromMutableVehicleModels():  MutableList<VehicleModel> {
    val list = emptyList<VehicleModel>().toMutableList()
    this.forEach {
        val aggregate = VehicleModel(it.plate, it.typeID, it.image,
            it.color, it.modelo, it.pasagero)
        aggregate.id = it.id
        list.add(aggregate)
    }
    return list
}
