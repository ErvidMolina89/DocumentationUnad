package com.ceiba.vehicle.di

import com.ceiba.vehicle.infrastructure.service.InitializationVariablesRoom
import com.ceiba.vehicle.infrastructure.service.TypeVehiclesRoom
import com.ceiba.vehicle.repository.InitializationVariablesReposity
import com.ceiba.vehicle.repository.TypeVariablesReposity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ModuleRepository {
    @Binds
    abstract fun bindInitVariableRepository(initVariableRoom: InitializationVariablesRoom):
            InitializationVariablesReposity
    @Binds
    abstract fun bindTypeVehicle(typeRoom: TypeVehiclesRoom): TypeVariablesReposity
}