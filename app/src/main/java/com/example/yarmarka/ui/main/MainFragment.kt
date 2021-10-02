package com.example.yarmarka.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentLoginBinding
import com.example.yarmarka.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(view: View) {

        binding.btnMainBack.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.bthMainAccount.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_accountFragment)
        }

        binding.bthMainMyApplication.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_myApplicationsFragment2)
        }

        binding.btnMainFilter.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }

        binding.bthMainMotivation.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_motivationFragment2)
        }
    }

}