package com.example.yarmarka.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentMainBinding
import com.example.yarmarka.model.FilterObject
import com.example.yarmarka.model.Project
import com.example.yarmarka.ui.main.projects.OnProjectClickListener
import com.example.yarmarka.ui.main.projects.ProjectsRecyclerAdapter
import com.example.yarmarka.utils.bundle

class MainFragment : Fragment(), OnProjectClickListener {

    private val binding by viewBinding(FragmentMainBinding::bind)

    lateinit var mViewModel: MainViewModel
    lateinit var mAdapter: ProjectsRecyclerAdapter

    //private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

//    private val list = listOf(
//        Project(
//            id = 1,
//            title = "Платформа для размещения вузовских олимпиад",
//            goal = "Создать платформу (страничку) для рекламы олимпиад",
//            difficulty = 2,
//            places = 6,
//            //leader = "Лукьянов Н.Д."
//        ),
//        Project(
//            id = 2,
//            title = "Платформа для размещения вузовских олимпиад",
//            goal = "Создать платформу (страничку) для рекламы олимпиад",
//            difficulty = 5,
//            places = 6,
//            vacant_places = 0,
//            //leader = "Лукьянов Н.Д."
//        ),
//        Project(
//            id = 3,
//            title = "Платформа для размещения вузовских олимпиад",
//            goal = "Создать платформу (страничку) для рекламы олимпиад",
//            difficulty = 8,
//            places = 6,
//            //leader = "Лукьянов Н.Д."
//        ),
//    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initListeners(view)
//        val projectsAdapter = ProjectsRecyclerAdapter(list, this, requireContext())
//        projectsAdapter.notifyDataSetChanged()
//        mCompositeDisposable?.add()

        mAdapter = ProjectsRecyclerAdapter(emptyList(), this, requireContext())

        loadApiData()

        binding.rcvMainAllProjects.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        //loadApiData()
    }

    private fun loadApiData() {
        val bundleFilters = bundle?.getParcelable<FilterObject>("filters")
        Log.d("filters", "$bundleFilters")
        if (bundleFilters == null) {
            mViewModel.projectList.observe(viewLifecycleOwner, {
                if (it != null) {
                    mAdapter = ProjectsRecyclerAdapter(it, this, requireContext())
                    binding.rcvMainAllProjects.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
            })
            mViewModel.getProjectList()
            binding.btnDismissFilters.visibility = View.GONE
        } else {
            mViewModel.filteredProjectList.observe(viewLifecycleOwner, {
                if (it != null) {
                    Log.d("filters", "${it}")
                    mAdapter = ProjectsRecyclerAdapter(it, this, requireContext())
                    binding.rcvMainAllProjects.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
            })
            val filter = FilterObject(supervisor = listOf(4))
            Log.d("filter", "${filter==bundleFilters}")
            Log.d("filter", "${filter}")
            Log.d("filter", "${bundleFilters}")
            mViewModel.getFilteredProjectList(bundleFilters)
            binding.btnDismissFilters.visibility = View.VISIBLE
        }
    }

    private fun initListeners(view: View) {
        binding.bthMainAccount.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_accountFragment)
        }

        binding.btnMainFilter.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }

        binding.bthMainMotivation.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_motivationFragment)
        }

        binding.btnDismissFilters.setOnClickListener {
            bundle = null
            loadApiData()
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