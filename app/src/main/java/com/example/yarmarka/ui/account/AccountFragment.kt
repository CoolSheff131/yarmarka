package com.example.yarmarka.ui.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.App
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentAccountBinding
import com.example.yarmarka.domain.model.Candidate
import com.example.yarmarka.domain.model.CandidateUpdate
import com.example.yarmarka.domain.model.Skill
import com.example.yarmarka.ui.account.dialog_quit.DialogQuit
import com.example.yarmarka.ui.account.dialog_quit.OnQuitDialogClickedListener
import com.example.yarmarka.ui.account.dialog_skills_choice.DialogSkills
import com.example.yarmarka.ui.account.dialog_skills_choice.OnSkillsDialogClickedListener
import com.example.yarmarka.ui.account.skills.OnSkillClickListener
import com.example.yarmarka.ui.account.skills.SkillsDeletableRecyclerAdapter
import com.example.yarmarka.ui.account.skills.SkillsRecyclerAdapter
import com.example.yarmarka.utils.fm
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import javax.inject.Inject


class AccountFragment : Fragment(), OnQuitDialogClickedListener, OnSkillClickListener,
    OnSkillsDialogClickedListener {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    @Inject
    lateinit var mViewModel: AccountViewModel

    private lateinit var rootView: View

    private var accountData: Candidate? = null

    private lateinit var rcv: RecyclerView
    private lateinit var mAdapter: SkillsRecyclerAdapter
    private lateinit var mAdapterDeletable: SkillsDeletableRecyclerAdapter
    private var skillsToDelete = mutableListOf<Skill>()

    private var isEditing = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        init()
        initListeners(view)
    }

    private fun init() {
        //preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: null) as SharedPreferences
        mAdapter = SkillsRecyclerAdapter(emptyList(), null)
        mAdapterDeletable = SkillsDeletableRecyclerAdapter(emptyList(), this)
        rcv = binding.rcvAccountTags
        val layoutManager = FlexboxLayoutManager()
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.alignItems = AlignItems.STRETCH
        rcv.layoutManager = layoutManager
        rcv.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
        loadAccountData()
    }

    private fun initListeners(view: View) {

        binding.btnAccountBack.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.btnProjects.setOnClickListener {
            view.findNavController().navigate(R.id.action_accountFragment_to_myProjectsFragment)
        }

        binding.btnRequests.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_accountFragment_to_myApplicationsFragment)
        }

        binding.btnAccountQuit.setOnClickListener {
            DialogQuit(this).show(fm, "dialog_account_quit")
        }

//        binding.btnAdmitAccountChanges.setOnClickListener {
//            val preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
//                ?: null) as SharedPreferences
//            val token = preferences.getString("token", "")
//            Log.d("testing", "$token")
//            if (token != "") {
//                mViewModel.updateAccountData(token!!, CandidateUpdate(
//                    binding.etAdditionalInfo.text.toString(),
//                    "04en krutoi telefon",
//                    mAdapter.getSkillsIds()
//                ))
//                mViewModel.getAccountData(token)
//            }
//        }

        binding.btnAccountAccept.setOnClickListener {
            Log.d("updating", "$accountData")
            val list: MutableList<Skill>? = accountData?.skills?.toMutableList()
            val ids = mutableListOf<Int>()
            ids.addAll(list!!.map { it.id!! })
            ids.removeAll(skillsToDelete.map { it.id })
            Log.d("updating", "$list")
            skillsToDelete.clear()
            updateAccountInfo(ids)
            removeEditMode()
        }

        binding.btnAccountEdit.setOnClickListener {
            if (!isEditing) {
                setEditMode()
            } else {
                removeEditMode()
                cancelEdit()
            }
        }

        binding.btnAccountAddSkill.setOnClickListener {
            //skills = accountData?.skills?.map { it.id!! }!!
            val skills = accountData?.skills
            DialogSkills(this, skills).show(fm, "dialog_account_skills")
        }
    }

    private fun setEditMode() {
        binding.tvAdditionalInfo.visibility = View.GONE
        binding.etAdditionalInfo.setText(binding.tvAdditionalInfo.text)
        binding.etAdditionalInfo.visibility = View.VISIBLE

        binding.tvPhone.visibility = View.INVISIBLE
        var text = binding.tvPhone.text.toString()
        binding.etPhone.setText(
            text
                .replace("+7", "")
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(" ", "")
        )
        binding.etPhone.visibility = View.VISIBLE

        binding.btnAccountAddSkill.visibility = View.VISIBLE
        mAdapterDeletable = SkillsDeletableRecyclerAdapter(mAdapter.getSkills(), this)
        rcv.adapter = mAdapterDeletable
        mAdapterDeletable.notifyDataSetChanged()

        //binding.btnAccountEdit.setBackgroundResource(R.drawable.ic_edit_active)
        binding.btnAccountEdit.setImageResource(R.drawable.ic_edit_active)
        binding.btnAccountQuit.visibility = View.GONE
        binding.btnAccountAccept.visibility = View.VISIBLE
        isEditing = true
    }

    private fun removeEditMode() {
        binding.tvAdditionalInfo.visibility = View.VISIBLE
        //binding.etAdditionalInfo.setText(binding.tvAdditionalInfo.text)
        binding.tvAdditionalInfo.text = binding.etAdditionalInfo.text
        binding.etAdditionalInfo.visibility = View.GONE

        binding.tvPhone.visibility = View.VISIBLE
        //binding.etPhone.setText(binding.tvPhone.text)
        binding.tvPhone.text = binding.etPhone.text
        binding.etPhone.visibility = View.GONE

        binding.btnAccountAddSkill.visibility = View.GONE
        mAdapter = SkillsRecyclerAdapter(mAdapterDeletable.getSkills(), null)
        rcv.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

        binding.btnAccountEdit.setImageResource(R.drawable.ic_edit)
        binding.btnAccountQuit.visibility = View.VISIBLE
        binding.btnAccountAccept.visibility = View.GONE
        isEditing = false
    }

    override fun onYesClicked() {
        val preferences = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE) as SharedPreferences
        val editor = preferences.edit()
        Log.d("AUTH", "UNAUTHED")
        editor.remove("token")
        editor.apply()
        rootView.findNavController().navigate(R.id.action_accountFragment_to_onBoardingFragment)
    }

    private fun loadAccountData() {
        mViewModel.accountData.observe(viewLifecycleOwner) {
            if (it != null) {
                accountData = it
                setAccountData(it)
            }
        }
        val preferences = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE) as SharedPreferences
        val token = preferences.getString("token", "")
        if (token != "") {
            mViewModel.getAccountData(token!!) {}
        }
    }

    private fun setAccountData(candidate: Candidate) {
        binding.tvName.text = candidate.fio
        binding.tvEmail.text = candidate.email
        binding.tvGroup.text = candidate.training_group
        binding.tvPhone.text = candidate.phone
        if (candidate.skills != null) {

            if (isEditing) {
                mAdapterDeletable = SkillsDeletableRecyclerAdapter(
                    candidate.skills!!, this
                )
                rcv.adapter = mAdapterDeletable
                mAdapterDeletable.notifyDataSetChanged()
            } else {
                mAdapter = SkillsRecyclerAdapter(
                    candidate.skills!!, null
                )
                rcv.adapter = mAdapter
                mAdapter.notifyDataSetChanged()
            }
        }
        //binding.etAdditionalInfo.setText(candidate.about)
        binding.tvAdditionalInfo.text = candidate.about
    }

    private fun updateAccountInfo(ids: List<Int>?) {
        val preferences = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE) as SharedPreferences
        val token = preferences.getString("token", "")
        Log.d("testing", "$token")
        Log.d("testing", binding.etPhone.text.toString())
        val update = CandidateUpdate(
            binding.etAdditionalInfo.text.toString(),
            binding.etPhone.text.toString(),
            ids
        )
        Log.d("updating", "$update")
        if (token != "") {
            mViewModel.updateAccountData(
                token!!, update
            ) {
                mViewModel.getAccountData(token) { loadAccountData() }
            }
        }
    }

    private fun cancelEdit() {
        val list: MutableList<Skill> = mAdapterDeletable.getSkills().toMutableList()
        list.addAll(skillsToDelete)
        skillsToDelete.clear()
        mAdapter = SkillsRecyclerAdapter(list.sortedBy { it.id }, this)
        rcv.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

        binding.tvPhone.text = accountData?.phone

        binding.tvAdditionalInfo.text = accountData?.about
    }

    override fun onSkillTappedListener(skill: Skill) {}

    override fun onSkillDeleteItemClicked(skill: Skill) {
//        updateAccountInfo(mAdapterDeletable.getSkillsIds())
        skillsToDelete.add(skill)
        val list: MutableList<Skill> = mAdapterDeletable.getSkills().toMutableList()
        list.removeAll(skillsToDelete)
        mAdapterDeletable = SkillsDeletableRecyclerAdapter(list, this)
        rcv.adapter = mAdapterDeletable
        mAdapterDeletable.notifyDataSetChanged()
    }

    override fun onAdmitClicked(chosenSkills: List<Skill>) {
        Log.d("skills", "${accountData?.skills}")
        var ids = mutableListOf<Int>()
        ids.addAll(chosenSkills.map { it.id!! })
        ids.removeAll(skillsToDelete.map { it.id })
        Log.d("skills", "${ids}")
        skillsToDelete.clear()
        accountData?.skills?.map { it.id!! }?.let { ids.addAll(it) }
        ids.sort()

        updateAccountInfo(ids)
    }
}
