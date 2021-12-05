package com.example.yarmarka.ui.my_applications

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
import com.example.yarmarka.databinding.FragmentMyApplicationsBinding
import com.example.yarmarka.model.Project
import com.example.yarmarka.ui.main.MainFragmentDirections
import com.example.yarmarka.ui.main.MainViewModel
import com.example.yarmarka.ui.main.projects.OnProjectClickListener
import com.example.yarmarka.ui.main.projects.ProjectsRecyclerAdapter

class MyApplicationsFragment : Fragment(), OnProjectClickListener {

    private val binding by viewBinding(FragmentMyApplicationsBinding::bind)

    private lateinit var mViewModel: MyApplicationsViewModel

    private lateinit var mAdapter: ProjectsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_applications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initListeners(view)
        loadData()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(MyApplicationsViewModel::class.java)
        mAdapter = ProjectsRecyclerAdapter(listOf(), this, requireContext())
    }

    private fun initListeners(view: View) {
        binding.btnMyApplicationsClose.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    private fun loadData() {
        mViewModel.applicationsList.observe(viewLifecycleOwner, {
            if (it != null) {
                val list = mutableListOf<Project>()
                for (i in it) {
                    list.add(i.project!!)
                }
                mAdapter = ProjectsRecyclerAdapter(list, this, requireContext())
                binding.rcvMyApplications.adapter = mAdapter
                mAdapter.notifyDataSetChanged()
            }
        })
        val preferences = (activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?: null) as SharedPreferences
        val token: String = preferences.getString("token", "").toString()
        if (token != "") {
            mViewModel.getApplicationsList(token = "1234")
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