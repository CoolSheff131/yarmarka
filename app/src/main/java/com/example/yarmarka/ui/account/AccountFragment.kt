package com.example.yarmarka.ui.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentAccountBinding
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.network.services.ApiServiceAuth
import com.example.yarmarka.network.services.ApiServiceCandidates
import com.example.yarmarka.ui.account.dialog_quit.DialogQuit
import com.example.yarmarka.ui.account.dialog_quit.OnDialogClickedListener
import com.example.yarmarka.ui.filters.FiltersViewModel
import com.example.yarmarka.utils.fm
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment(), OnDialogClickedListener {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    private lateinit var mViewModel: AccountViewModel

    private lateinit var rootView: View

    private var accountData: Candidate? = null

    //lateinit var preferences: SharedPreferences

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
        Log.d("AUTH","UNAUTHED")
        editor.remove("token")
        editor.apply()
        rootView.findNavController().navigate(R.id.action_accountFragment_to_onBoardingFragment)

//        val api = ApiServiceAuth.buildService()
//        val token = preferences.getString("token", "")
//        if (token != "") {
//            if (token != null) {
//                Log.d("AUTH", "UNAUTH $token")
//                api.logout(token).enqueue(object :Callback<Void> {
//                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
//
//                    }
//
//                    override fun onFailure(call: Call<Void>, t: Throwable) {
//                        TODO("Not yet implemented")
//                    }
//
//                })
//
//            }
//        }
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

    private fun getLogoutDataObserver(): Observer<String>{
        return object : Observer<String> {
            override fun onChanged(t: String?) {
                TODO("Not yet implemented")
            }
        }
    }
}