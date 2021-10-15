package com.ceiba.vehicle.infrastructure.service

import com.ceiba.infrastructure.data_access.mapper.fromMutableVehicleModels
import com.ceiba.infrastructure.data_access.mapper.fromVehicleEntity
import com.ceiba.infrastructure.data_access.mapper.fromVehicleModel
import com.ceiba.vehicle.infrastructure.database.dao.VehicleDao
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.repository.VehicleReposity
import javax.inject.Inject

class VehiclesRoom @Inject constructor(private val vehicleDao: VehicleDao): VehicleReposity {

    override fun saveVehicles(vehicle: VehicleModel): Long {
        return vehicleDao.saveVehicle(vehicle.fromVehicleEntity())
    }

    override fun getListVehicle(): MutableList<VehicleModel>? {
        return vehicleDao.getAllCars()?.fromMutableVehicleModels()
    }

    override fun deleteVehicleSelected(vehicle: VehicleModel?): Int? {
        return vehicle?.id?.let { vehicleDao.deleteCar(it) }
    }

    override fun getVehicleForPlaca(placa: String): VehicleModel? {
        return vehicleDao.getVehicle(placa)?.fromVehicleModel()
    }

    override suspend fun updateVehicle(vehicle: VehicleModel): VehicleModel {
        vehicleDao.updateVehicle(vehicle.fromVehicleEntity())
        return vehicle
    }


}