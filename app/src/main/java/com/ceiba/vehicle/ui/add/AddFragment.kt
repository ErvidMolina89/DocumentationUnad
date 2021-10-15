package com.ceiba.vehicle.ui.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ceiba.vehicle.R
import com.ceiba.vehicle.databinding.FragmentAddBinding
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.add.interfaz.AddViewModelDelegate
import com.ceiba.vehicle.ui.add.interfaz.InitializationViewModelDelegate
import com.ceiba.vehicle.ui.add.viewmodel.AddViewModel
import com.ceiba.vehicle.ui.add.viewmodel.InitializationFragmentFieldsViewModel
import com.ceiba.vehicle.util.createToast
import com.ceiba.vehicle.util.showDialogImageUrl
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment(), InitializationViewModelDelegate, AddViewModelDelegate {

    private val addViewModel: AddViewModel by viewModels()
    private val initFieldsViewModel: InitializationFragmentFieldsViewModel by viewModels()
    private var _binding: FragmentAddBinding? = null
    private var selectColor = ""
    private var selectImage = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initFieldsViewModel.setDelegate(this)
        addViewModel.setDelegate(this)
        initFieldsViewModel.getTypesVehicle()
        initFieldsViewModel.fillSpinnerPassenger(binding.spNumPasageros)

        listenerBtnColor()
        listenerImageView()
        listenerBtnCreate()
        return root

    }

    private fun listenerImageView() {
        binding.ivIconoCamara.setOnClickListener {
            addViewModel.initDialogueImageVehicle(requireContext(), binding.CIVPhoteVevicle)
        }
    }

    @SuppressLint("ResourceType")
    private fun listenerBtnColor(){
        binding.btnSelectColorBg.setOnClickListener {
            this.context?.let { it1 ->
                MaterialColorPickerDialog
                    .Builder(it1)
                    .setColors(resources.getStringArray(R.array.color_array)) // Pass Predefined Hex Color
                    .setColorListener { color, colorHex ->
                        binding.CIVColorChange.setBackgroundColor(color)
                        selectColor = colorHex
                    }
                    .show()
            }
        }
    }


    private fun listenerBtnCreate(){
        binding.btnCrear.setOnClickListener {
            val plate = binding.editTextPlacaNew.text.toString()
            val type = binding.spTypeVehicle.selectedItemPosition + 1
            val model = binding.editTextModeloNew.text.toString()
            val passagenr = binding.spNumPasageros.selectedItem.toString().toInt()
            addViewModel.valiadteAndCreateVehicle(
                VehicleModel(plate, type, selectImage, selectColor,
                model, passagenr))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun fillSpinnerTypeRoom() {
        initFieldsViewModel.fillSpinnerTypeVehicle(binding.spTypeVehicle)
    }

    override fun responseImageUrl(url: String) {
        selectImage = url
    }

    override fun responseCreateVehicle() {
        binding.btnCrear.post {
            requireContext().createToast("Se creo el vehiculo")
        }
    }

    override fun responseErrorValidate(mess: String) {
        requireContext().createToast(mess)
    }
}