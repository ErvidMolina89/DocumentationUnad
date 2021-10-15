package com.ceiba.vehicle.ui.delete.interfaz

import com.ceiba.vehicle.model.VehicleModel

interface DeleteViewModelDelegate {
    fun responseOnClickItemRecycler(vehicleModel: VehicleModel?)
    fun responseEmptySearch()
    fun responseGetListVehicle(listVehicle: MutableList<VehicleModel>?)
    fun responseEmptyListVehicle()
    fun responseErrorDelete()
    fun responseDeleteCorrect()
}