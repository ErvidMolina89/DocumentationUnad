package com.ceiba.vehicle.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "vehicleEntity", foreignKeys = [
    ForeignKey(entity = TypeEntity::class,
        parentColumns = ["id"],
        childColumns = ["TypeID"])
])
class VehicleEntity(
    @ColumnInfo(name = "Plate") var plate: String,
    @ColumnInfo(name = "Modelo") var modelo: String?,
    @ColumnInfo(name = "Image") var image: String?,
    @ColumnInfo(name = "Color") var color: String?,
    @ColumnInfo(name = "TypeID") var typeID: Int,
    @ColumnInfo(name = "NumeroPasagero") var pasagero: Int?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null
}