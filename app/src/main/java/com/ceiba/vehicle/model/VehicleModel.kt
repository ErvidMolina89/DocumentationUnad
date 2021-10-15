package com.ceiba.vehicle.model

class VehicleModel(placa: String, type: Int, image: String?,
                   color: String?, modelo: String?,
                   pasageros: Int?) {
    var id: Int? = null
    var placa: String = placa
    var modelo: String? = modelo
    var image: String? = image
    var color: String? = color
    var pasageros: Int? = pasageros
    var typeVehicle: Int = type
}