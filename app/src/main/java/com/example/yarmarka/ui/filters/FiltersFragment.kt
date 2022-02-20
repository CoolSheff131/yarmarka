package com.example.yarmarka.ui.filters

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.App
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentFiltersBinding
import com.example.yarmarka.domain.model.FilterObject
import com.example.yarmarka.domain.model.SupervisorName
import com.example.yarmarka.domain.model.Tag
import com.example.yarmarka.ui.filters.supervisors.OnSupervisorClickListener
import com.example.yarmarka.ui.filters.supervisors.SupervisorsRecyclerAdapter
import com.example.yarmarka.ui.main.tags.OnTagClickListener
import com.example.yarmarka.ui.main.tags.TagsRecyclerDeletableAdapter
import com.example.yarmarka.utils.addZeroToDate
import com.example.yarmarka.utils.bundle
import java.util.*
import javax.inject.Inject

class FiltersFragment : Fragment(), OnTagClickListener, OnSupervisorClickListener {

    private val binding by viewBinding(FragmentFiltersBinding::bind)

    @Inject
    lateinit var mViewModel: FiltersViewModel

    private var tags = listOf<Tag>()
    private val tagList = mutableListOf<Tag>()
    private lateinit var rcvTags: RecyclerView
    private lateinit var rcvTagsAdapter: TagsRecyclerDeletableAdapter

    private var shortSupervisorNames = mutableMapOf<SupervisorName, String>()
    private val supervisorList = mutableListOf<SupervisorName>()
    private lateinit var rcvSupervisors: RecyclerView
    private lateinit var rcvSupervisorsAdapter: SupervisorsRecyclerAdapter

    private lateinit var typeCheckBoxes: List<CheckBox>
    private lateinit var stateCheckBoxes: List<CheckBox>
    private lateinit var difficultyCheckBoxes: List<CheckBox>

    val c = Calendar.getInstance()

    var year_start = c.get(Calendar.YEAR)
    var year_end = c.get(Calendar.YEAR)

    var month_start = c.get(Calendar.MONTH)
    var month_end = c.get(Calendar.MONTH)

    var day_start = c.get(Calendar.DAY_OF_MONTH)
    var day_end = c.get(Calendar.DAY_OF_MONTH)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners(view)
        loadData()
    }

    private fun init() {
        rcvTags = binding.rcvTags
        rcvTagsAdapter = TagsRecyclerDeletableAdapter(tagList, this)
        rcvTags.adapter = rcvTagsAdapter

        rcvSupervisors = binding.rcvSupervisors
        rcvSupervisorsAdapter = SupervisorsRecyclerAdapter(supervisorList, this)
        rcvSupervisors.adapter = rcvSupervisorsAdapter

        typeCheckBoxes = listOf(
            binding.checkResearch,
            binding.checkHard,
            binding.checkService
        )

        stateCheckBoxes = listOf(
            binding.checkProcess,
            binding.checkOpen,
            binding.checkActive,
            binding.checkClose,
        )

        difficultyCheckBoxes = listOf(
            binding.checkDif1,
            binding.checkDif2,
            binding.checkDif3
        )
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

                rcvTags.adapter = null
                rcvTagsAdapter = TagsRecyclerDeletableAdapter(tagList, this)
                rcvTags.adapter = rcvTagsAdapter
                rcvTagsAdapter.notifyDataSetChanged()
            }
        }

        binding.btnAddSupervisor.setOnClickListener {
            var supervisor: SupervisorName? = null
            for (i in shortSupervisorNames.keys) {
                if (shortSupervisorNames[i] == binding.spinnerSupervisor.selectedItem.toString()) {
                    supervisor = i
                    break
                }
            }
            if (supervisor != null && !supervisorList.contains(supervisor)) {
                supervisorList.add(supervisor)

                rcvSupervisorsAdapter = SupervisorsRecyclerAdapter(supervisorList, this)
                rcvSupervisors.adapter = rcvSupervisorsAdapter
                rcvSupervisorsAdapter.notifyDataSetChanged()
            }
        }

        binding.btnDateStart.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                { view, year, month, day ->
                    binding.btnDateStart.text = "${addZeroToDate(day)}.${addZeroToDate(month+1)}.$year"
                    year_start = year
                    month_start = month
                    day_start = day
                },
                year_start,
                month_start,
                day_start
            )
            dpd.show()
        }

        binding.btnDateEnd.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                { view, year, month, day ->
                    binding.btnDateEnd.text = "${addZeroToDate(day)}.${addZeroToDate(month+1)}.$year"
                    year_end = year
                    month_end = month
                    day_end = day
                },
                year_end,
                month_end,
                day_end
            )
            dpd.show()
        }

        binding.btnFilterApply.setOnClickListener {
            if (bundle == null) bundle = Bundle()

            val filters = FilterObject(
                type = getCheckedTypesList(),
                state = getCheckedStatesList(),
                supervisor = getChosenSupervisors(),
                tags = getChosenTags(),
                date_start = getChosenDateStart(),
                date_end = getChosenDateEnd(),
                difficulty = getCheckedDifficulties()
            )
            Log.d("testing", filters.toString())

            bundle?.putParcelable("filters", filters)
            view.findNavController().popBackStack()
        }
    }

    private fun loadData() {
        loadSupervisors()
        loadTags()
    }

    private fun loadSupervisors() {
        mViewModel.supervisorsList.observe(viewLifecycleOwner, {
            val supervisorNames = mutableListOf<String>()
            if (it != null) {
                for (i in it) {
                    if (i.fio != null) {
                        val shortName = i.fio!!.split(" ")[0] + " " +
                                i.fio!!.split(" ")[1][0] + ". " +
                                i.fio!!.split(" ")[2][0] + "."
                        supervisorNames.add(shortName)
                        shortSupervisorNames[i] = shortName
                    }
                }
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, supervisorNames)
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
                for (i in it) {
                    if (i.tag != null) {
                        tagNames.add(i.tag!!)
                    }
                }
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tagNames)
            binding.spinnerTag.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        mViewModel.getTags()
    }

    override fun onTagDeleteItemClicked(tag: Tag) {
        tagList.remove(tag)
        rcvTagsAdapter.notifyDataSetChanged()
    }

    override fun onSupervisorDeleteItemClicked(supervisor: SupervisorName) {
        supervisorList.remove(supervisor)
        rcvSupervisorsAdapter.notifyDataSetChanged()
    }

    private fun getCheckedTypesList(): List<Int>? {
        var list: MutableList<Int>? = null

        var num = 1
        for (i in typeCheckBoxes) {
           if (i.isChecked) {
               if (list == null) {
                   list = mutableListOf()
               }
               list.add(num)
           }
            num++
        }

        return list
    }

    private fun getCheckedStatesList(): List<Int>? {
        var list: MutableList<Int>? = null

        var num = 1
        for (i in stateCheckBoxes) {
            if (i.isChecked) {
                if (list == null) {
                    list = mutableListOf()
                }
                list.add(num)
            }
            num++
        }

        return list
    }

    private fun getChosenSupervisors(): List<Int>? {
        var list: MutableList<Int>? = null

        for (i in supervisorList) {
            list = mutableListOf()
            if (i.id != null) {
                list.add(i.id!!)
            }
        }

        return list
    }

    private fun getChosenTags(): List<Int>? {
        var list: MutableList<Int>? = null

        for (i in tagList) {
            list = mutableListOf()
            if (i.id != null) {
                list.add(i.id!!)
            }
        }

        return list
    }

    private fun getChosenDateStart(): String {
        var date = binding.btnDateStart.text

        return date.split(".")[2] + "-" +
                date.split(".")[1] + "-" +
                date.split(".")[0]
    }

    private fun getChosenDateEnd(): String {
        var date = binding.btnDateEnd.text

        return date.split(".")[2] + "-" +
                date.split(".")[1] + "-" +
                date.split(".")[0]
    }

    private fun getCheckedDifficulties(): List<Int>? {
        var list: MutableList<Int>? = null

        var num = 1
        for (i in difficultyCheckBoxes) {
            if (i.isChecked) {
                if (list == null) {
                    list = mutableListOf()
                }
                list.add(num)
            }
            num++
        }

        return list
    }
}
