package com.ceiba.vehicle.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.vehicle.infrastructure.database.entity.TypeEntity

@Dao
interface TypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(obj: List<TypeEntity>): List<Long>

    @Query("SELECT * FROM typeEntity")
    fun getAllType(): List<TypeEntity>

    @Query("DELETE FROM typeEntity WHERE id = :id")
    fun deleteType(id: Int)
}