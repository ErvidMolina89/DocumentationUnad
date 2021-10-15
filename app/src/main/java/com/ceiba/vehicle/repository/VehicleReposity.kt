package com.ceiba.vehicle.repository

import com.ceiba.vehicle.model.VehicleModel

interface VehicleReposity {

    fun saveVehicles(vehicle: VehicleModel): Long
    fun getListVehicle(): MutableList<VehicleModel>?
    fun deleteVehicleSelected(vehicle: VehicleModel?): Int?
    fun getVehicleForPlaca(placa: String): VehicleModel?
    suspend fun updateVehicle(vehicle: VehicleModel): VehicleModel?
}