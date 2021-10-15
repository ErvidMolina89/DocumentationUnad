package com.ceiba.vehicle.infrastructure.database.dao

import androidx.room.*
import com.ceiba.vehicle.infrastructure.database.entity.VehicleEntity

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<VehicleEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVehicle(carEntity: VehicleEntity?): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateVehicle(carEntity: VehicleEntity?): Int

    @Query("SELECT * FROM vehicleEntity")
    fun getAllCars(): List<VehicleEntity>?

    @Query("SELECT * FROM vehicleEntity WHERE Plate = :placa")
    fun getVehicle(placa: String): VehicleEntity?

    @Query("DELETE FROM vehicleEntity WHERE id = :id")
    fun deleteCar(id: Int): Int
}