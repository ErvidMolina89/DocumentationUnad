package com.ceiba.vehicle.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.vehicle.infrastructure.database.dao.TypeDao
import com.ceiba.vehicle.infrastructure.database.dao.VehicleDao
import com.ceiba.vehicle.infrastructure.database.entity.TypeEntity
import com.ceiba.vehicle.infrastructure.database.entity.VehicleEntity

@Database(
    entities = [TypeEntity::class, VehicleEntity::class],
    version = 1, exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun typeDao(): TypeDao
    abstract fun carDao(): VehicleDao

}