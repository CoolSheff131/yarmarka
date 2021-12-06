package com.example.yarmarka.ui.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : Fragment() {

    private val binding by viewBinding(FragmentOnBoardingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val preferences: SharedPreferences =
            (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: null) as SharedPreferences
        val token = preferences.getString("token","")
        if(token != ""){
            view.findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
            return
        }
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(view: View) {

        binding.bthSignIn.setOnClickListener {
            view.findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

}