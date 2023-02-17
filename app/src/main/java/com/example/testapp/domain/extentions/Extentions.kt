package com.example.testapp.domain.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String){
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .into(this)

}