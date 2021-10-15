package com.ceiba.factoryimplementation.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ceiba.vehicle.model.VehicleModel

fun String.filterListVehcile(listVehicle: MutableList<VehicleModel>): MutableList<VehicleModel>{
    val list = listVehicle.filter {
        return@filter it.placa.toLowerCase().contains(this.toLowerCase())
    }
    return list.toMutableList()
}

fun Int.showNameType(): String{
    return when(this){
        1 -> {"Auto"}
        2 -> {"Moto"}
        3 -> {"Camioneta"}
        4 -> {"Camion"}
        else -> {"Auto"}
    }
}
fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
