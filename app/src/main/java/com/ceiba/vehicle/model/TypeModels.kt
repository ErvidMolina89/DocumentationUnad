package com.ceiba.vehicle.model

class TypeModels(id: Int, name: String) {
    var id: Int = id
    var name: String = name

    override fun toString(): String {
        return name
    }
}