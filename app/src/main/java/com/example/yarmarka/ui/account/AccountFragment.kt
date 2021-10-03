package com.example.yarmarka.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentAccountBinding
import com.example.yarmarka.ui.account.dialog_quit.DialogQuit
import com.example.yarmarka.ui.account.dialog_quit.OnDialogClickedListener
import com.example.yarmarka.utils.fm

class AccountFragment : Fragment(), OnDialogClickedListener {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    private lateinit var rootView: View

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
        initListeners(view)
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
        rootView.findNavController().navigate(R.id.action_accountFragment_to_onBoardingFragment)
    }
}