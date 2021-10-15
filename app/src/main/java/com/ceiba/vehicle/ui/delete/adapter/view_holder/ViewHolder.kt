package com.ceiba.vehicle.ui.delete.adapter.view_holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.factoryimplementation.util.showImage
import com.ceiba.factoryimplementation.util.showNameType
import com.ceiba.vehicle.R
import com.ceiba.vehicle.model.VehicleModel
import de.hdodenhof.circleimageview.CircleImageView

class ViewHolder (mView: View): RecyclerView.ViewHolder(mView) {

    val image              : CircleImageView = mView.findViewById(R.id.circleImageViewItemVehicle)
    val textViewPlaca      : TextView = mView.findViewById(R.id.textViewPlacaItemVehicle)
    val textViewModelo     : TextView = mView.findViewById(R.id.textViewModeloItemVehicle)
    val textViewType       : TextView = mView.findViewById(R.id.textViewTypeItemVehicle)
    val textViewPasageros  : TextView = mView.findViewById(R.id.textViewPasageroItemVehicle)
    val buttonDelete       : ImageView = mView.findViewById(R.id.imageViewDeleteItemVehicle)


    fun bindData(item: VehicleModel){
        image.showImage(item.image)
        textViewPlaca.text = item.placa
        textViewModelo.text = item.modelo
        textViewType.text = item.typeVehicle.showNameType()
        textViewPasageros.text = item.pasageros.toString()
    }

}