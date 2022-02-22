package com.example.yarmarka.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.App
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentMainBinding
import com.example.yarmarka.domain.model.FilterObject
import com.example.yarmarka.domain.model.Project
import com.example.yarmarka.ui.main.projects.OnProjectClickListener
import com.example.yarmarka.ui.main.projects.ProjectsRecyclerAdapter
import com.example.yarmarka.utils.bundle
import com.github.ybq.android.spinkit.SpinKitView
import com.jakewharton.rxbinding2.view.enabled
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainFragment : Fragment(), OnProjectClickListener {

    private val binding by viewBinding(FragmentMainBinding::bind)
    lateinit var pgBar: SpinKitView
    @Inject
    lateinit var mViewModel: MainViewModel

    lateinit var mAdapter: ProjectsRecyclerAdapter
    private var page: Int = 1;

    //private var mCompositeDisposable: CompositeDisposable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        page = 1
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initListeners(view)
        loadApiData()

        //pgBar.visibility = View.GONE
    }

    private fun init() {
        pgBar = activity?.findViewById(R.id.pgBar)!!
        mAdapter = ProjectsRecyclerAdapter(emptyList(), this, requireContext())
    }

    private fun loadApiData(searchPart: String? = null) {

        val bundleFilters = bundle?.getParcelable<FilterObject>("filters")
        bundleFilters?.title = searchPart
        Log.d("mainSearch", "===========$bundleFilters")
        if (bundleFilters == null) {
            mViewModel.projectList.observe(viewLifecycleOwner) {
                if (it != null && it.data.size != 0) {
                    Log.d("mainSearch", "mere")
                    val col = mAdapter.getList().toMutableList()
                    pgBar.visibility = View.GONE
                    col.addAll(it.data)
                    mAdapter = ProjectsRecyclerAdapter(col, this, requireContext())
                    binding.rcvMainAllProjects.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
            }
            mViewModel.getProjectList(page)
            binding.btnDismissFilters.visibility = View.GONE
        } else {
            val check = bundleFilters.state == null &&
                    bundleFilters.type == null &&
                    bundleFilters.supervisor == null &&
                    bundleFilters.tags == null &&
                    bundleFilters.date_end == null &&
                    bundleFilters.date_start == null &&
                    bundleFilters.difficulty == null &&
                    bundleFilters.title == ""
            if (check) return
            mViewModel.filteredProjectList.observe(viewLifecycleOwner) {
                if (it != null) {
                    Log.d("mainSearch", "filtered")
                    pgBar.visibility = View.GONE
                    mAdapter = ProjectsRecyclerAdapter(it.data, this, requireContext())
                    binding.rcvMainAllProjects.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
            }
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
            binding.edtMainSearch.setText("")
            loadApiData()
        }

        binding.rcvMainAllProjects.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }

        binding.rcvMainAllProjects.addOnScrollListener(object :  RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
//                val l = binding.rcvMainAllProjects.layoutManager as LinearLayoutManager
//                Log.d("PAGINATION",""+l.findLastVisibleItemPosition()+" "+ mAdapter.getItemCount())
//                if (l.findLastVisibleItemPosition() >= mAdapter.getItemCount()-1) {
//                    Log.d("PAGINATION","MOOOORE")
//                    page++;
//                    loadApiData();
//                }
            }
        })

        val subscribe = RxTextView.textChanges(binding.edtMainSearch)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                Log.d("bundle", "$bundle ${bundle==null}")
//                if (bundle == null) bundle = Bundle()
//                val bundleFilters = bundle?.getParcelable<FilterObject>("filters")
//                bundleFilters?.title = it.toString()
//                bundle?.putParcelable("filters", bundleFilters)
//                Log.d("mainSearch", it.toString())
                if (bundle?.getParcelable<FilterObject>("filters") == null) {
                    bundle?.putParcelable("filters", FilterObject())
                }
                loadApiData(it.toString())
            }, {
                Log.d("testing", "error")
            })
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