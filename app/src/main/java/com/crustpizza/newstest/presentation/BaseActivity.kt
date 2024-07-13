package com.crustpizza.newstest.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BINDING : ViewDataBinding> : AppCompatActivity() {

    private var _binding: BINDING? = null

    val mBinding
        get() = _binding

    abstract fun getBinding(): BINDING

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = getBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}