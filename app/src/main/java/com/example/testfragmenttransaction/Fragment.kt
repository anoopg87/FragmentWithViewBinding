package com.example.testfragmenttransaction

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> Fragment.viewBinding(): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        private var binding: T? = null
        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            this@viewBinding.viewLifecycleOwnerLiveData
                .observe(this@viewBinding) { newLifeCycleOwner ->
                    viewLifecycleOwner?.lifecycle?.removeObserver(this)
                    viewLifecycleOwner = newLifeCycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T = binding
            ?: throw IllegalStateException("The view binding instance should be inflated first")

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            binding = value
        }

    }