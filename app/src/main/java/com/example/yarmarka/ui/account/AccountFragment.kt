package com.example.yarmarka.ui.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentAccountBinding
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.model.CandidateUpdate
import com.example.yarmarka.model.Skill
import com.example.yarmarka.model.Tag
import com.example.yarmarka.ui.account.dialog_quit.DialogQuit
import com.example.yarmarka.ui.account.dialog_quit.OnDialogClickedListener
import com.example.yarmarka.ui.account.skills.SkillsRecyclerAdapter
import com.example.yarmarka.ui.main.tags.OnTagClickListener
import com.example.yarmarka.ui.main.tags.TagsRecyclerVerticalAdapter
import com.example.yarmarka.utils.fm
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager


class AccountFragment : Fragment(), OnDialogClickedListener {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    private lateinit var mViewModel: AccountViewModel

    private lateinit var rootView: View

    private var accountData: Candidate? = null

    private lateinit var rcv: RecyclerView
    private lateinit var mAdapter: SkillsRecyclerAdapter

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
        mAdapter = SkillsRecyclerAdapter(emptyList())
        rcv = binding.rcvAccountTags
        val layoutManager = FlexboxLayoutManager()
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.alignItems = AlignItems.STRETCH
        rcv.setLayoutManager(layoutManager)
        rcv.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
        Log.d("testing", "${mAdapter.itemCount}");
        Log.d("testing", "${mAdapter.toString()}");
        mViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
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
                .navigate(R.id.action_accountFragment_to_myApplicationsFragment2)
        }

        binding.btnAccountQuit.setOnClickListener {
            DialogQuit(this).show(fm, "dialog_account_quit")
        }

        binding.btnAdmitAccountChanges.setOnClickListener {
            val preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
                ?: null) as SharedPreferences
            val token = preferences.getString("token", "")
            Log.d("testing", "$token")
            if (token != "") {
                mViewModel.updateAccountData(token!!, CandidateUpdate(
                    binding.etAdditionalInfo.text.toString(),
                    "04en krutoi telefon",
                    mAdapter.getSkillsIds()
                ))
                mViewModel.getAccountData(token)
            }
        }

        binding.btnAccountAddSkill.setOnClickListener {

        }
    }

    override fun onYesClicked() {
        val preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?: null) as SharedPreferences
        val editor = preferences.edit()
        Log.d("AUTH", "UNAUTHED")
        editor.remove("token")
        editor.apply()
        rootView.findNavController().navigate(R.id.action_accountFragment_to_onBoardingFragment)
    }

    private fun loadAccountData() {
        mViewModel.accountData.observe(viewLifecycleOwner, {
            if (it != null) {
                accountData = it
                Log.d("testing", "$it")
                setAccountData(it)
            }
        })
        val preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?: null) as SharedPreferences
        val token = preferences.getString("token", "")
        Log.d("testing", "$token")
        if (token != "") {
            mViewModel.getAccountData(token!!)
        }
    }

    private fun setAccountData(candidate: Candidate) {
        binding.tvName.text = candidate.fio
        binding.tvEmail.text = candidate.email
        binding.tvGroup.text = candidate.training_group
        binding.tvPhone.text = candidate.phone
        if (candidate.skills != null) {
            mAdapter = SkillsRecyclerAdapter(listOf(
                Skill(1, "Mobile"),
                Skill(2, "OOP"),
                Skill(3, "API"),
                Skill(4, "Super jokes")
            ))
            rcv.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
        binding.etAdditionalInfo.setText(candidate.about)
    }

}
