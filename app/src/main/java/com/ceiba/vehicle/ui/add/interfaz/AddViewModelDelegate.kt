package com.ceiba.vehicle.ui.add.interfaz

interface AddViewModelDelegate {
    fun responseImageUrl(url: String)
    fun responseCreateVehicle()
    fun responseErrorValidate(mess: String)
}