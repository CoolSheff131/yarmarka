@file:JvmName("RxTextView")

package com.example.yarmarka.ui.account.dialog_skills_choice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.App
import com.example.yarmarka.R
import com.example.yarmarka.databinding.DialogSkillsChoiceBinding
import com.example.yarmarka.domain.model.Skill
import com.example.yarmarka.ui.account.skills.OnSkillClickListener
import com.example.yarmarka.ui.account.skills.SkillsDeletableRecyclerAdapter
import com.example.yarmarka.ui.account.skills.SkillsRecyclerAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DialogSkills(
    private val onDialogClickedListener: OnSkillsDialogClickedListener,
    private val availableSkills: List<Skill>?
) :
    DialogFragment(), OnSkillClickListener {

    private val binding by viewBinding(DialogSkillsChoiceBinding::bind)

    @Inject
    lateinit var mViewModel: DialogSkillsViewModel

    private lateinit var mAdapterSkillsChosen: SkillsDeletableRecyclerAdapter

    private lateinit var mAdapterSkills: SkillsRecyclerAdapter

    private val chosenSkills = mutableListOf<Skill>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

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
        //mAdapterSkills = SkillsRecyclerAdapter(emptyList())
        mAdapterSkills = SkillsRecyclerAdapter(emptyList(), this)

        mAdapterSkillsChosen = SkillsDeletableRecyclerAdapter(emptyList(), this)
    }

    private fun initListeners() {
        binding.btnAccountDialogSkillsAdmit.setOnClickListener {
            onDialogClickedListener.onAdmitClicked(chosenSkills)
            dismiss()
        }

        binding.btnAccountDialogSkillsCancel.setOnClickListener {
            dismiss()
        }

        val subscribe = RxTextView.textChanges(binding.etSkillsSearch)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadSkills(it.toString())
            }, {
                Log.d("testing", "error")
            })

    }

    private fun loadSkills(searchPart: String) {
        mViewModel.skills.observe(viewLifecycleOwner) {
            if (it != null && availableSkills != null) {
                val list = mutableListOf<Skill>()
                val skills = availableSkills
                //Log.d("application_skills", "$skills")
                for (i in it) {
                    if (skills.contains(i)) {
                        list.add(i)
                    }
                }
                //Log.d("application_skills", "$list")
                mAdapterSkills = SkillsRecyclerAdapter(list.sortedBy { item -> item.id }, this)
                binding.rcvDialogSkillsChoice.adapter = mAdapterSkills
                mAdapterSkills.notifyDataSetChanged()
            }
        }
        mViewModel.getSkills(searchPart)
    }

    private fun updateChosenSkills() {
        mAdapterSkillsChosen = SkillsDeletableRecyclerAdapter(chosenSkills, this)
        binding.rcvChosenSkills.adapter = mAdapterSkillsChosen
        mAdapterSkillsChosen.notifyDataSetChanged()
    }

    override fun onSkillTappedListener(skill: Skill) {
        if (!chosenSkills.contains(skill)) {
            chosenSkills.add(skill)
        }
        updateChosenSkills()
    }

    override fun onSkillDeleteItemClicked(skill: Skill) {
        chosenSkills.remove(skill)
        updateChosenSkills()
    }
}
