package com.crustpizza.newstest.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("loadImage")
fun ImageView.bindImage(url:String){
        Glide.with(this.context)
            .load(url)
            .into(this)
}