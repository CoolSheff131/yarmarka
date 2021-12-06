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
import androidx.recyclerview.widget.RecyclerView
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
    private var page: Int = 1;
    //private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        page = 1
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
        binding.rcvMainAllProjects.addOnScrollListener(object :  RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val l = binding.rcvMainAllProjects.layoutManager as LinearLayoutManager
                Log.d("PAGINATION",""+l.findLastVisibleItemPosition()+" "+ mAdapter.getItemCount())
                if (l.findLastVisibleItemPosition() >= mAdapter.getItemCount()-1) {
                    Log.d("PAGINATION","MOOOORE")
                    page++;
                    loadApiData();
                }
            }
        })
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
                if (it != null && it.size != 0) {
                    val col = mAdapter.getList().toMutableList()
                    col.addAll(it)
                    mAdapter = ProjectsRecyclerAdapter(col, this, requireContext())
                    binding.rcvMainAllProjects.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
            })
            mViewModel.getProjectList(page)
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