package com.ceiba.vehicle.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ceiba.factoryimplementation.util.hideKeyboard
import com.ceiba.vehicle.R
import com.ceiba.vehicle.databinding.FragmentSearchBinding
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.search.interfaz.SearchViewModelDelegate
import com.ceiba.vehicle.ui.search.viewmodel.SearchViewModel
import com.ceiba.vehicle.util.createToast
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchViewModelDelegate {

    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchViewModel.setDelegate(this)
        listenerBtnSearch()
        listenerImageViewEdit()
        listenerBtnUpdate()
        listenerImageView()
        listenerBtnColor()

        return root
    }

    private fun listenerBtnSearch() {
        binding.btnSearchVehicle.setOnClickListener {
            val placa = binding.editTextSearch.text.toString()
            if(placa.isNullOrEmpty()){
                requireContext().createToast("Debe Ingresar una placa")
            }else searchViewModel.getVehicleSearch(placa)
            binding.editTextSearch.hideKeyboard(this.requireContext())
        }
    }

    private fun listenerImageView() {
        binding.ivIconoCamaraSearch.setOnClickListener {
            searchViewModel.initDialogueImageVehicle(requireContext(), binding.cvPhotoUserSearch)
        }
    }

    private fun listenerImageViewEdit(){
        binding.imageViewEditInfo.setOnClickListener {
            searchViewModel.enableFieldsUpdateVehicle(binding)
            binding.editTextSearch.hideKeyboard(this.requireContext())
        }
    }

    @SuppressLint("ResourceType")
    private fun listenerBtnColor() {
        binding.btnSelectColorBgSearch.setOnClickListener {
            this.context?.let { it1 ->
                MaterialColorPickerDialog
                    .Builder(it1)
                    .setColors(resources.getStringArray(R.array.color_array)) // Pass Predefined Hex Color
                    .setColorListener { color, colorHex ->
                        binding.CIVColorChangeSearch.setBackgroundColor(color)
                        searchViewModel.vehicle?.color = colorHex
                    }
                    .show()
            }
        }
    }

    private fun listenerBtnUpdate(){
        binding.btnUpdate.setOnClickListener {
            val vehicle = searchViewModel.vehicle
            vehicle?.modelo = binding.editTextModeloSearch.text.toString()
            vehicle?.pasageros = binding.spNumPasagerosSearch.selectedItem.toString().toInt()
            searchViewModel.updateVehicle(vehicle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun responseNotVehicle() {
        binding.btnSearchVehicle.post {
            requireContext().createToast("No se encontro ese vehiculo, Verifique")
        }
    }

    override fun responseCorrectVehicle() {
        binding.btnSearchVehicle.post {
            binding.clContainerInfoSearch.visibility = View.VISIBLE
            searchViewModel.fillSpinnerPassenger(binding.spNumPasagerosSearch)
            binding.spNumPasagerosSearch.isEnabled = false
            searchViewModel.bindInfoVehicle(binding)
        }
    }
}