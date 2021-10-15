package com.ceiba.vehicle.ui.delete.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.vehicle.R
import com.ceiba.vehicle.model.VehicleModel
import com.ceiba.vehicle.ui.delete.adapter.view_holder.ViewHolder

class DeleteRecyclerViewAdapter  (
    val context : Context?,
    internal var listPizzas: MutableList<VehicleModel>
) : RecyclerView.Adapter<ViewHolder>() {

    private var listener: ((VehicleModel?)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.aggregate_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: VehicleModel = listPizzas[position]
        holder.bindData(item)

        setOnClickListeners(holder, item)
    }

    private fun setOnClickListeners(holder : ViewHolder, vehicle : VehicleModel?){

        holder.buttonDelete.setOnClickListener {
                listener?.invoke(vehicle)
            }
    }

    override fun getItemCount(): Int = listPizzas.size

    fun setData(listSearch : MutableList<VehicleModel>){
        this.listPizzas = listSearch
        notifyDataSetChanged()
    }

    fun onClickListener(listener : (VehicleModel?)-> Unit){
        this.listener = listener
    }
}