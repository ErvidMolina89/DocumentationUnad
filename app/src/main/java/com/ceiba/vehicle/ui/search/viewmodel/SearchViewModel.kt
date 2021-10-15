package com.ceiba.vehicle.ui.search.viewmodel

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.factoryimplementation.util.showImage
import com.ceiba.factoryimplementation.util.showNameType
import com.ceiba.vehicle.R
import com.ceiba.vehicle.databinding.FragmentSearchBinding
import com.ceiba.vehicle.infrastructure.service.VehiclesRoom
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.dialogue.DialogAddUrlImageVehicle
import com.ceiba.vehicle.ui.search.interfaz.SearchViewModelDelegate
import com.ceiba.vehicle.util.showDialogImageUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val room : VehiclesRoom,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private lateinit var delegate: SearchViewModelDelegate
    private lateinit var binding : FragmentSearchBinding
    var vehicle : VehicleModel? = null

    fun getVehicleSearch(placa: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                vehicle = room.getVehicleForPlaca(placa)
                if (vehicle == null || vehicle?.placa == ""){
                    delegate.responseNotVehicle()
                }else delegate.responseCorrectVehicle()
            }
        }
    }

    fun fillSpinnerPassenger(spinner: Spinner){
        val array = R.array.passanger_array
        ArrayAdapter.createFromResource(
            context,
            array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun bindInfoVehicle(binding: FragmentSearchBinding){
        this.binding = binding
        binding.cvPhotoUserSearch.showImage(vehicle?.image)
        binding.textViewPlacaSearch.text = vehicle?.placa
        binding.textViewTypeSearch.text = vehicle?.typeVehicle?.showNameType()
        vehicle?.pasageros?.let { binding.spNumPasagerosSearch.setSelection(it - 1) }
        binding.editTextModeloSearch.setText(vehicle?.modelo)
        binding.CIVColorChangeSearch.setBackgroundColor(Color.parseColor(vehicle?.color))
    }

    fun initDialogueImageVehicle(context: Context, image: CircleImageView) {
        DialogAddUrlImageVehicle
            .getInstance()
            .withActionBtnOk {
                image.showImage(it)
                vehicle?.image = it
            }
        context.showDialogImageUrl()
    }

    fun enableFieldsUpdateVehicle(binding: FragmentSearchBinding) {
        binding.spNumPasagerosSearch.isEnabled = true
        binding.editTextModeloSearch.isEnabled = true
        binding.editTextModeloSearch.isFocusable = true
        binding.textViewTitleColor.visibility = View.GONE
        binding.btnSelectColorBgSearch.visibility = View.VISIBLE
        binding.llContentImageCamera.visibility = View.VISIBLE
        binding.btnUpdate.visibility = View.VISIBLE
        binding.imageViewEditInfo.visibility = View.GONE
    }

    private fun enableFieldsCorrectUpdate(){
//        binding.btnUpdate.post {
            binding.spNumPasagerosSearch.isEnabled = false
            binding.editTextModeloSearch.isEnabled = false
            binding.editTextModeloSearch.isFocusable = false
            binding.textViewTitleColor.visibility = View.VISIBLE
            binding.btnSelectColorBgSearch.visibility = View.GONE
            binding.llContentImageCamera.visibility = View.GONE
            binding.btnUpdate.visibility = View.GONE
            binding.imageViewEditInfo.visibility = View.VISIBLE
//        }
    }

    fun updateVehicle(vehicleModel: VehicleModel?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                vehicle = vehicleModel?.let { room.updateVehicle(it) }
                binding.btnUpdate.post {
                    enableFieldsCorrectUpdate()
                    bindInfoVehicle(binding)
                }
            }
        }
    }





    fun setDelegate(delegate: SearchViewModelDelegate) {
        this.delegate = delegate
    }
}