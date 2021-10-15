package com.ceiba.factoryimplementation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ceiba.vehicle.R

fun ImageView.showImage(urlFoto : String? = null ) {
    if (urlFoto.isNullOrEmpty()) { return }
    Glide
        .with(context)
        .load(urlFoto)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.drawable.playa_la_roca)
        .skipMemoryCache(true)
        .centerCrop()
        .into(this)
}