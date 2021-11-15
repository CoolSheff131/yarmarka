package com.example.yarmarka.ui.filters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentFiltersBinding
import com.example.yarmarka.model.FilterObject
import com.example.yarmarka.model.Tag
import com.example.yarmarka.ui.main.MainViewModel
import com.example.yarmarka.ui.main.tags.OnTagClickListener
import com.example.yarmarka.ui.main.tags.TagsRecyclerAdapter

class FiltersFragment : Fragment(), OnTagClickListener {

    private val binding by viewBinding(FragmentFiltersBinding::bind)

    private lateinit var mViewModel: FiltersViewModel

    private var tags = listOf<Tag>()
    private val tagList = mutableListOf<Tag>()
    private lateinit var rcv: RecyclerView
    private lateinit var rcvAdapter: TagsRecyclerAdapter

//    private val typeCheckBoxes = listOf(
//        binding.checkResearch,
//        binding.checkHard,
//        binding.checkService
//    )
//
//    private val stateCheckBoxes = listOf(
//        binding.checkOpen,
//        binding.checkClose,
//        binding.checkActive
//    //TODO: add one more state
//    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(FiltersViewModel::class.java)
        init()
        initListeners(view)
        loadData()
    }

    private fun init() {
        rcv = binding.rcvTags
        rcvAdapter = TagsRecyclerAdapter(tagList, this)
        rcv.adapter = rcvAdapter
    }

    private fun initListeners(view: View) {

        binding.btnFilterClose.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.btnAddTag.setOnClickListener {
            var tag: Tag? = null
            for (i in tags) {
                //Log.d("testing", "${i.tag} == ${binding.spinnerTag.selectedItem.toString()}")
                if (i.tag == binding.spinnerTag.selectedItem.toString()) {
                    tag = i
                    break
                }
            }
            if (tag != null && !tagList.contains(tag)) {
                tagList.add(tag)

                rcvAdapter = TagsRecyclerAdapter(tagList, this)
                rcv.adapter = rcvAdapter
                rcvAdapter.notifyDataSetChanged()
            }
        }

        binding.btnFilterApply.setOnClickListener {
//            val filters = FilterObject(
//                type = getCheckedTypesList(),
//                state = getCheckedStatesList(),
//                supervisor = listOf(),
//            )
        }
    }

    private fun loadData() {
        loadSupervisors()
        loadTags()
    }

    private fun loadSupervisors() {
        mViewModel.supervisorsList.observe(viewLifecycleOwner, {
            val supervisors = mutableListOf<String>()
            if (it != null) {
                for (i in it) supervisors.add(i.fio!!)
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, supervisors)
            binding.spinnerSupervisor.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        mViewModel.getSupervisors()
    }

    private fun loadTags() {
        mViewModel.tagsList.observe(viewLifecycleOwner, {
            var tagNames = mutableListOf<String>()
            if (it != null) {
                tags = it
                for (i in it) tagNames.add(i.tag!!)
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tagNames)
            binding.spinnerTag.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        mViewModel.getTags()
    }

    override fun onTagDeleteItemClicked(tag: Tag) {
        tagList.remove(tag)
        rcvAdapter.notifyDataSetChanged()
    }

//    private fun getCheckedTypesList(): List<Int> {
//        val list = mutableListOf<Int>()
//
//        var num = 1
//        for (i in typeCheckBoxes) {
//           if (i.isChecked) {
//               list.add(num)
//           }
//            num++
//        }
//
//        return list
//    }
//
//    private fun getCheckedStatesList(): List<Int> {
//        val list = mutableListOf<Int>()
//
//        var num = 1
//        for (i in stateCheckBoxes) {
//            if (i.isChecked) {
//                list.add(num)
//            }
//            num++
//        }
//
//        return list
//    }
//
//    private fun getCheckedSupervisorsList(): List<Int> {
//        val list = mutableListOf<Int>()
//
//        var num = 1
//        for (i in stateCheckBoxes) {
//            if (i.isChecked) {
//                list.add(num)
//            }
//            num++
//        }
//
//        return list
//    }
}