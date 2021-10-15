package com.ceiba.vehicle.ui.add.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.factoryimplementation.util.showImage
import com.ceiba.vehicle.infrastructure.service.VehiclesRoom
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.add.interfaz.AddViewModelDelegate
import com.ceiba.vehicle.ui.dialogue.DialogAddUrlImageVehicle
import com.ceiba.vehicle.util.showDialogImageUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val room: VehiclesRoom
    ): ViewModel() {

    private lateinit var delegate: AddViewModelDelegate

    fun valiadteAndCreateVehicle(vehicleModel: VehicleModel) {
        if (validateFields(vehicleModel)){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val long = room.saveVehicles(vehicleModel)
                    if (long.toInt() != 0) {
                        delegate.responseCreateVehicle()
                    }
                }
            }
        }
    }

    fun initDialogueImageVehicle(context: Context, image: CircleImageView?) {
        DialogAddUrlImageVehicle
            .getInstance()
            .withActionBtnOk {
                delegate.responseImageUrl(it)
                image?.showImage(it)
            }
        context.showDialogImageUrl()
    }

    private fun validateFields(vehicleModel: VehicleModel):Boolean{
        if (vehicleModel.placa.isNullOrEmpty()){
            errorValidateResponse("Necesita ingresar una placa")
            return false
        }
        if (vehicleModel.color.isNullOrEmpty()){
            errorValidateResponse("Necesita ingresar un color")
            return false
        }

        return true
    }

    private fun errorValidateResponse(mess: String){
        delegate.responseErrorValidate(mess)
    }



    fun setDelegate(delegate: AddViewModelDelegate) {
        this.delegate = delegate
    }
}