package com.example.yarmarka.ui.my_applications

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.App
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentMyApplicationsBinding
import com.example.yarmarka.domain.model.Project
import com.example.yarmarka.ui.main.applications.ApplicationsRecyclerAdapter
import com.example.yarmarka.ui.main.projects.OnProjectClickListener
import javax.inject.Inject

class MyApplicationsFragment : Fragment(), OnProjectClickListener {

    private val binding by viewBinding(FragmentMyApplicationsBinding::bind)

    @Inject
    lateinit var mViewModel: MyApplicationsViewModel

    private lateinit var mAdapter: ApplicationsRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

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
        mAdapter = ApplicationsRecyclerAdapter(listOf(), this)
    }

    private fun initListeners(view: View) {
        binding.btnMyApplicationsClose.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    private fun loadData() {
        mViewModel.applicationsList.observe(viewLifecycleOwner) {
            if (it != null) {
                mAdapter = ApplicationsRecyclerAdapter(it, this)
                binding.rcvMyApplications.adapter = mAdapter
                mAdapter.notifyDataSetChanged()
            }
        }
        val preferences = (activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?: null) as SharedPreferences
        val token: String = preferences.getString("token", "").toString()
        if (token != "") {
            mViewModel.getApplicationsList(token = token)
        }
    }

    override fun onProjectItemClicked(project: Project) {
        val action: MyApplicationsFragmentDirections.ActionMyApplicationsFragmentToProjectInformationFragment =
            MyApplicationsFragmentDirections.actionMyApplicationsFragmentToProjectInformationFragment(project)
        action.project = project
        view?.findNavController()?.navigate(action)
    }

    override fun onButtonClicked(project: Project) {
        val action: MyApplicationsFragmentDirections.ActionMyApplicationsFragmentToProjectInformationFragment =
            MyApplicationsFragmentDirections.actionMyApplicationsFragmentToProjectInformationFragment(project)
        action.project = project
        view?.findNavController()?.navigate(action)
    }
}