package com.example.yarmarka.ui.my_projects

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentMyProjectsBinding
import com.example.yarmarka.model.Project
import com.example.yarmarka.ui.main.MainFragmentDirections
import com.example.yarmarka.ui.main.projects.OnProjectClickListener
import com.example.yarmarka.ui.main.projects.ProjectsRecyclerAdapter
import com.example.yarmarka.ui.my_applications.MyApplicationsViewModel

class MyProjectsFragment : Fragment(), OnProjectClickListener {

    private val binding by viewBinding(FragmentMyProjectsBinding::bind)

    private lateinit var mViewModel: MyProjectsViewModel

    private lateinit var mAdapter: ProjectsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_projects, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initListeners(view)
        loadData()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(MyProjectsViewModel::class.java)
        mAdapter = ProjectsRecyclerAdapter(listOf(), this, requireContext())
    }

    private fun initListeners(view: View) {
        binding.btnMyProjectsClose.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    private fun loadData() {
        mViewModel.participationsList.observe(viewLifecycleOwner, {
            if (it != null) {
                val list = mutableListOf<Project>()
                for (i in it) {
                    list.add(i.project!!)
                }
                mAdapter = ProjectsRecyclerAdapter(list, this, requireContext())
                binding.rcvMyProjects.adapter = mAdapter
                mAdapter.notifyDataSetChanged()
            }
        })
        val preferences = (activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?: null) as SharedPreferences
        val token: String = preferences.getString("token", "").toString()
        if (token != "") {
            mViewModel.getParticipationsList(token = "1234")
        }
    }

    override fun onProjectItemClicked(project: Project) {
        val action: MainFragmentDirections.ActionMainFragmentToProjectInformationFragment =
            MainFragmentDirections.actionMainFragmentToProjectInformationFragment(project)
        action.project = project
        view?.findNavController()?.navigate(action)
    }

    override fun onButtonClicked(project: Project) {
        val action: MainFragmentDirections.ActionMainFragmentToProjectInformationFragment =
            MainFragmentDirections.actionMainFragmentToProjectInformationFragment(project)
        action.project = project
        view?.findNavController()?.navigate(action)
    }
}