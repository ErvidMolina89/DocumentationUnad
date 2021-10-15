package com.ceiba.vehicle.infrastructure.database.data

import com.ceiba.vehicle.infrastructure.database.entity.TypeEntity
import com.ceiba.vehicle.infrastructure.database.entity.VehicleEntity

class DataConfig {

    fun inserTypesVehicles(): MutableList<TypeEntity>{
        val list = emptyList<TypeEntity>().toMutableList()
        list.add(TypeEntity(1, "Auto"))
        list.add(TypeEntity(2, "Moto"))
        list.add(TypeEntity(3, "Camioneta"))
        list.add(TypeEntity(4, "Camion"))
        return list
    }

    fun inserVehicles(): MutableList<VehicleEntity> {
        val list = emptyList<VehicleEntity>().toMutableList()
        list.add(VehicleEntity("YTH456", "2019", "https://publimotos.com/images/2020/abril/motos-mas-economicas/bajaj-boxer-ct100/bajaj-boxer-ct100-01.jpg", "#000000", 2, 2))
        list.add(VehicleEntity("TFR123", "2020", "https://imganuncios.mitula.net/renault_logan_2015_renault_logan_familier_2015_3200132599656920963.jpg", "#FF0000", 1, 4))
        list.add(VehicleEntity("FRT632", "2021", "https://www.motor.com.co/files/article_main/uploads/2020/06/17/5eea6ab0624a9.jpeg", "#FFFFFF", 3, 5))
        list.add(VehicleEntity("FGT785", "2022", "https://imganuncios.mitula.net/hino_2007_se_vende_camion_hino_color_blanco_rojo_3280129606141371075.jpg", "#FF0000", 4, 3))
        return list
    }
}