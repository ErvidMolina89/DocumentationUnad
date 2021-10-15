package com.ceiba.vehicle.repository

import com.ceiba.vehicle.model.TypeModels

interface TypeVariablesReposity {

    fun getTypeVehicles(): MutableList<TypeModels>
}