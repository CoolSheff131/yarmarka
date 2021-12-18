package com.example.yarmarka.ui.application

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentApplicationBinding
import com.example.yarmarka.model.ParticipationCreate
import com.example.yarmarka.model.Project
import com.example.yarmarka.model.Skill
import com.example.yarmarka.ui.account.dialog_skills_choice.DialogSkills
import com.example.yarmarka.ui.account.dialog_skills_choice.OnSkillsDialogClickedListener
import com.example.yarmarka.ui.account.skills.OnSkillClickListener
import com.example.yarmarka.ui.account.skills.SkillsDeletableRecyclerAdapter
import com.example.yarmarka.ui.project_info.ProjectInformationFragmentArgs
import com.example.yarmarka.utils.fm
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jakewharton.rxbinding2.widget.checked

class ApplicationFragment : Fragment(), OnSkillClickListener, OnSkillsDialogClickedListener {

    private val binding by viewBinding(FragmentApplicationBinding::bind)

    private lateinit var mViewModel: ApplicationViewModel

    private lateinit var mAdapter: SkillsDeletableRecyclerAdapter

    private var skillsList = mutableListOf<Skill>()
    private var missingSkillsList = mutableListOf<Skill>()

    private var project: Project? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_application, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initListeners(view)
        loadData()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)
        mAdapter = SkillsDeletableRecyclerAdapter(emptyList(), this)

        val arg: ApplicationFragmentArgs by navArgs()
        project = arg.project
    }

    private fun initListeners(view: View) {

        binding.btnApplicationAccount.setOnClickListener {
            view.findNavController().navigate(R.id.action_applicationFragment_to_accountFragment)
        }

        binding.btnApplicationBack.setOnClickListener {
            view.findNavController().popBackStack()
        }

        mViewModel.studentSkills.observe(viewLifecycleOwner, {
            if (it != null) {
                val layoutManager = FlexboxLayoutManager()
                layoutManager.flexWrap = FlexWrap.WRAP
                layoutManager.flexDirection = FlexDirection.ROW
                layoutManager.alignItems = AlignItems.STRETCH
                binding.rcvApplicationSkills.layoutManager = layoutManager
                updateRecyclerView(it)

                skillsList.clear()
                skillsList.addAll(it)
            }
        })

        mViewModel.allSkills.observe(viewLifecycleOwner, {
            if (it != null) {
                val newMissings = mutableListOf<Skill>()
                for (i in it) {
                    if (!skillsList.contains(i)) {
                        Log.d("application_skills", "$i ${skillsList.contains(i)}")
                        newMissings.add(i)
                    }
                }
                missingSkillsList = newMissings
            }
        })

        binding.btnApplicationAddSkill.setOnClickListener {
            Log.d("application_skills", "$missingSkillsList}")
            DialogSkills(this, missingSkillsList).show(fm, "dialog_account_skills")
        }

        binding.btnSendParticipationRequest.setOnClickListener {
            if (!binding.checkBox.isChecked || binding.editTextExp.text.toString() == "") {
                binding.tvError.visibility = View.VISIBLE
            } else {
                binding.tvError.visibility = View.INVISIBLE

                val preferences = (activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
                    ?: null) as SharedPreferences
                val token = preferences.getString("token", "")
                if (token != "") {
                    mViewModel.sendParticipationRequest(
                        projectId = project?.id!!,
                        token = token!!,
                        participationCreate = ParticipationCreate(binding.editTextExp.text.toString(), skillsList.map { item->item.id!! })
                    )
                }
                view.findNavController().popBackStack()
            }
        }
    }

    private fun loadData() {
        loadStudentSkills()
        loadMissingSkills()
    }

    private fun loadStudentSkills() {
        val preferences = (activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?: null) as SharedPreferences
        val token = preferences.getString("token", "")
        if (token != "") {
            mViewModel.getStudentSkills(token!!)
        }
    }

    private fun loadMissingSkills() {
        mViewModel.getAllSkills()
    }

    private fun updateRecyclerView(skillList: List<Skill>) {
        mAdapter = SkillsDeletableRecyclerAdapter(skillList, this)
        binding.rcvApplicationSkills.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun onSkillTappedListener(skill: Skill) {}

    override fun onSkillDeleteItemClicked(skill: Skill) {
        skillsList.remove(skill)
        missingSkillsList.add(skill)
        updateRecyclerView(skillsList)
    }

    override fun onAdmitClicked(chosenSkills: List<Skill>) {
        skillsList.addAll(chosenSkills)
        missingSkillsList.removeAll(chosenSkills)
        updateRecyclerView(skillsList)
    }
}