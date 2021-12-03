@file:JvmName("RxTextView")

package com.example.yarmarka.ui.account.dialog_skills_choice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.DialogSkillsChoiceBinding
import com.example.yarmarka.model.Skill
import com.example.yarmarka.ui.account.AccountViewModel
import com.example.yarmarka.ui.account.skills.OnSkillClickListener
import com.example.yarmarka.ui.account.skills.SkillsDeletableRecyclerAdapter
import com.example.yarmarka.ui.account.skills.SkillsRecyclerAdapter
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DialogSkills(private val onDialogClickedListener: OnSkillsDialogClickedListener): DialogFragment(), OnSkillClickListener {

    private val binding by viewBinding(DialogSkillsChoiceBinding::bind)

    private lateinit var mViewModel: AccountViewModel

    private lateinit var mAdapterSkillsChosen: SkillsDeletableRecyclerAdapter

    private lateinit var mAdapterSkills: SkillsRecyclerAdapter
    //private mAdapterSKills: RecyclerA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_skills_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initListeners()
        loadSkills("")
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        mAdapterSkills = SkillsRecyclerAdapter(emptyList())

        mAdapterSkillsChosen = SkillsDeletableRecyclerAdapter(emptyList(), this)
    }

    private fun initListeners() {
        binding.btnAccountDialogSkillsAdmit.setOnClickListener {
            onDialogClickedListener.onAdmitClicked()
            dismiss()
        }

        binding.btnAccountDialogSkillsCancel.setOnClickListener {
            dismiss()
        }

        binding.etSkillsSearch.textChanges()
            //.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe({
                Log.d("testing", "$it")
                //loadSkills(it.toString())
            }, {

            })
    }

    private fun loadSkills(searchPart: String) {
        mViewModel.skills.observe(viewLifecycleOwner, {
            if (it != null) {
                Log.d("testing", "$it")
                mAdapterSkills = SkillsRecyclerAdapter(it.sortedBy { it -> it.skill })
                binding.rcvDialogSkillsChoice.adapter = mAdapterSkills
                mAdapterSkills.notifyDataSetChanged()
            }
        })
        mViewModel.getSkills(searchPart)
    }

    override fun onSkillDeleteItemClicked(skill: Skill) {
        TODO("Not yet implemented")
    }
}