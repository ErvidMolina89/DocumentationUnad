package com.ceiba.vehicle.ui.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.vehicle.databinding.FragmentDeleteBinding
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.delete.adapter.DeleteRecyclerViewAdapter
import com.ceiba.vehicle.ui.delete.interfaz.DeleteViewModelDelegate
import com.ceiba.vehicle.ui.delete.viewmodel.DeleteViewModel
import com.ceiba.vehicle.util.createToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteFragment : Fragment(), DeleteViewModelDelegate {

    private val deleteViewModel: DeleteViewModel by viewModels()
    private var _binding: FragmentDeleteBinding? = null
    private var adapter: DeleteRecyclerViewAdapter = DeleteRecyclerViewAdapter(
        this.context, emptyList<VehicleModel>().toMutableList())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //delegates
        deleteViewModel.setDelegate(this)

        //call recycler
        onStyleRecycler()
        deleteViewModel.listenerRecycler(adapter)
        deleteViewModel.listenerEditTextSearch(binding)
        deleteViewModel.getDataListVehicle()

        return root
    }

    private fun onStyleRecycler() {
        binding.recyclerViewSearchDelete.let {
            it.layoutManager  = LinearLayoutManager(this.context)
            this.adapter = DeleteRecyclerViewAdapter(
                this.context,
                emptyList<VehicleModel>().toMutableList()
            )
            binding.recyclerViewSearchDelete.adapter = this.adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun responseOnClickItemRecycler(vehicleModel: VehicleModel?) {
        deleteViewModel.deleteVehicle(vehicleModel)
    }

    override fun responseEmptySearch() {
        requireContext().createToast("No hay ningun vehiculo con esa placa, verificala")
    }

    override fun responseGetListVehicle(listVehicle: MutableList<VehicleModel>?) {
        binding.recyclerViewSearchDelete.post {
            deleteViewModel.setDataListRecycler()
        }
    }

    override fun responseEmptyListVehicle() {
        binding.recyclerViewSearchDelete.post {
            requireContext().createToast("No hay vehiculos registrados")
        }
    }

    override fun responseErrorDelete() {
        binding.recyclerViewSearchDelete.post {
            requireContext().createToast("No se pudo eliminar el vehiculo, intenta nuevamente")
        }
    }

    override fun responseDeleteCorrect() {
        binding.recyclerViewSearchDelete.post {
            requireContext().createToast("Se elimino correctamente el vehiculo")
        }
    }
}