package com.example.yarmarka.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentMainBinding
import com.example.yarmarka.model.Project

class MainFragment : Fragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private val list = listOf(
        Project(
            id = 1,
            title = "Платформа для размещения вузовских олимпиад",
            goal = "Создать платформу (страничку) для рекламы олимпиад",
            difficulty = 5,
            places = 6
        ),
        Project(
            id = 1,
            title = "Платформа для размещения вузовских олимпиад",
            goal = "Создать платформу (страничку) для рекламы олимпиад",
            difficulty = 5,
            places = 6
        ),
        Project(
            id = 1,
            title = "Платформа для размещения вузовских олимпиад",
            goal = "Создать платформу (страничку) для рекламы олимпиад",
            difficulty = 5,
            places = 6
        ),
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners(view)
        binding.rcvMainAllProjects.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProjectsRecyclerAdapter(list)
        }
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