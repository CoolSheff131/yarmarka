package com.example.yarmarka.ui.project_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentProjectInformationBinding
import com.example.yarmarka.model.Project

class ProjectInformationFragment : Fragment() {

    private val binding by viewBinding(FragmentProjectInformationBinding::bind)

    private var project: Project? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arg: ProjectInformationFragmentArgs by navArgs()
        project = arg.project
        Log.d("TAG", project.toString())

        initListeners(view)
    }

    private fun initListeners(view: View) {

        binding.btnMyProjectsClose.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.submitBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_projectInformationFragment_to_applicationFragment)
        }

        binding.btnProjectInformationAccount.setOnClickListener {
            view.findNavController().navigate(R.id.action_projectInformationFragment_to_accountFragment)
        }
    }


}