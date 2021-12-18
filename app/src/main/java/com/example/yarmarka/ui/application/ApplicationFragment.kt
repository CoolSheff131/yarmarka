package com.example.yarmarka.ui.application

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentApplicationBinding
import com.example.yarmarka.model.CandidateUpdate
import com.example.yarmarka.model.Skill
import com.example.yarmarka.ui.account.dialog_skills_choice.DialogSkills
import com.example.yarmarka.ui.account.dialog_skills_choice.OnSkillsDialogClickedListener
import com.example.yarmarka.ui.account.skills.OnSkillClickListener
import com.example.yarmarka.ui.account.skills.SkillsDeletableRecyclerAdapter
import com.example.yarmarka.utils.fm
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class ApplicationFragment : Fragment(), OnSkillClickListener, OnSkillsDialogClickedListener {

    private val binding by viewBinding(FragmentApplicationBinding::bind)

    private lateinit var mViewModel: ApplicationViewModel

    private lateinit var mAdapter: SkillsDeletableRecyclerAdapter

    private var skillsList = mutableListOf<Skill>()
    private var missingSkillsList = mutableListOf<Skill>()

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
                mAdapter = SkillsDeletableRecyclerAdapter(it, this)
                val layoutManager = FlexboxLayoutManager()
                layoutManager.flexWrap = FlexWrap.WRAP
                layoutManager.flexDirection = FlexDirection.ROW
                layoutManager.alignItems = AlignItems.STRETCH
                binding.rcvApplicationSkills.layoutManager = layoutManager
                binding.rcvApplicationSkills.adapter = mAdapter
                mAdapter.notifyDataSetChanged()

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

    override fun onSkillTappedListener(skill: Skill) {}

    override fun onSkillDeleteItemClicked(skill: Skill) {

    }

    override fun onAdmitClicked(chosenSkills: List<Skill>) {

    }
}