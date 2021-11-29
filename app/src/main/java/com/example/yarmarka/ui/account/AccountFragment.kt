package com.example.yarmarka.ui.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentAccountBinding
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.model.Tag
import com.example.yarmarka.ui.account.dialog_quit.DialogQuit
import com.example.yarmarka.ui.account.dialog_quit.OnDialogClickedListener
import com.example.yarmarka.ui.main.tags.OnTagClickListener
import com.example.yarmarka.ui.main.tags.TagsRecyclerHorizontalAdapter
import com.example.yarmarka.ui.main.tags.TagsRecyclerVerticalAdapter
import com.example.yarmarka.utils.fm
import com.google.android.flexbox.AlignItems

import com.google.android.flexbox.FlexDirection

import com.google.android.flexbox.FlexWrap

import com.google.android.flexbox.FlexboxLayoutManager




class AccountFragment : Fragment(), OnDialogClickedListener, OnTagClickListener {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    private lateinit var mViewModel: AccountViewModel

    private lateinit var rootView: View

    private var accountData: Candidate? = null

    private lateinit var rcv: RecyclerView
    private lateinit var mAdapter: TagsRecyclerVerticalAdapter

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
        mAdapter = TagsRecyclerVerticalAdapter(listOf(Tag(1, "Компьютерное зрение"), Tag(2, "Нейросетевые технологии"),
            Tag(3, "Веб"), Tag(4, "ООП")), this)
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
            view.findNavController().navigate(R.id.action_accountFragment_to_myApplicationsFragment2)
        }

        binding.btnAccountQuit.setOnClickListener {
            DialogQuit(this).show(fm, "dialog_account_quit")

        }
    }

    override fun onYesClicked() {
        val preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: null) as SharedPreferences
        val editor = preferences.edit()
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
        val preferences = (getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE) ?: null) as SharedPreferences
        val token = preferences.getString("token", "")
        if (token != "") {
            mViewModel.getAccountData(token!!)
        }
    }

    private fun setAccountData(candidate: Candidate) {
        binding.tvName.text = candidate.fio
        binding.tvEmail.text = candidate.email
        binding.tvGroup.text = candidate.training_group
        binding.tvPhone.text = candidate.phone
    }

    override fun onTagDeleteItemClicked(tag: Tag) {

    }
}
