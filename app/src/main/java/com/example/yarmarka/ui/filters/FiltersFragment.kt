package com.example.yarmarka.ui.filters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentFiltersBinding
import com.example.yarmarka.databinding.FragmentMainBinding

class FiltersFragment : Fragment() {

    private val binding by viewBinding(FragmentFiltersBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
    }

    private fun initListeners(view: View) {

        binding.btnFilterClose.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.btnFilterApply.setOnClickListener {
            
        }
    }
}