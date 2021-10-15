package com.ceiba.vehicle.di

import android.content.Context
import androidx.room.Room
import com.ceiba.vehicle.infrastructure.database.LocalDatabase
import com.ceiba.vehicle.infrastructure.database.dao.TypeDao
import com.ceiba.vehicle.infrastructure.database.dao.VehicleDao
import com.ceiba.vehicle.util.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRoomDataBase {

    @Singleton
    @Provides
    fun providesRoomDataBase(@ApplicationContext context:Context) =
        Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideTypeVehicleDao(db : LocalDatabase): TypeDao = db.typeDao()

    @Singleton
    @Provides
    fun provideVehicleDao(db : LocalDatabase): VehicleDao = db.carDao()
}