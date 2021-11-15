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
import com.example.yarmarka.model.FilterObject

class FiltersFragment : Fragment() {

    private val binding by viewBinding(FragmentFiltersBinding::bind)

    private val typeCheckBoxes = listOf(
        binding.checkResearch,
        binding.checkHard,
        binding.checkService
    )

    private val stateCheckBoxes = listOf(
        binding.checkOpen,
        binding.checkClose,
        binding.checkActive
    //TODO: add one more state
    )

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
            val filters = FilterObject(
                type = getCheckedTypesList(),
                state = getCheckedStatesList(),
                supervisor = listOf(),
            )
        }
    }

    private fun getCheckedTypesList(): List<Int> {
        val list = mutableListOf<Int>()

        var num = 1
        for (i in typeCheckBoxes) {
           if (i.isChecked) {
               list.add(num)
           }
            num++
        }

        return list
    }

    private fun getCheckedStatesList(): List<Int> {
        val list = mutableListOf<Int>()

        var num = 1
        for (i in stateCheckBoxes) {
            if (i.isChecked) {
                list.add(num)
            }
            num++
        }

        return list
    }

    private fun getCheckedSupervisorsList(): List<Int> {
        val list = mutableListOf<Int>()

        var num = 1
        for (i in stateCheckBoxes) {
            if (i.isChecked) {
                list.add(num)
            }
            num++
        }

        return list
    }
}