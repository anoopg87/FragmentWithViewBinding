package com.example.testfragmenttransaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.testfragmenttransaction.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        commitFragment(FragmentOne())
    }


    fun commitFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(viewBinding.fragmentContainer.id, fragment, fragment.javaClass.name)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        fragmentTransaction.commit()
    }

    fun popBack() {
        supportFragmentManager.popBackStack()
    }
}