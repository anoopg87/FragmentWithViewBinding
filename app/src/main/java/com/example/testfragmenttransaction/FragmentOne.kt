package com.example.testfragmenttransaction

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testfragmenttransaction.databinding.FragmentOneBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentOne: Fragment() {

    private var viewBinding: FragmentOneBinding by viewBinding()

    @Inject
    lateinit var presenter: Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOneBinding.inflate(inflater, container, false)
        viewBinding.fragmentName.setOnClickListener {
            (requireActivity() as MainActivity).commitFragment(FragmentTwo())
        }
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            viewBinding.fragmentName.text = presenter.getFirstMessage()
        }, 100)

    }

}