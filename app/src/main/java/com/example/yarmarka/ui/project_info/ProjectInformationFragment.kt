package com.example.yarmarka.ui.project_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.FragmentProjectInformationBinding
import com.example.yarmarka.domain.model.Project
import com.example.yarmarka.ui.main.tags.TagsRecyclerAdapter

class ProjectInformationFragment : Fragment() {

    private val binding by viewBinding(FragmentProjectInformationBinding::bind)

    private lateinit var mAdapter: TagsRecyclerAdapter
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

        initData()
        initListeners(view)

    }

    private fun initData(){
        binding.projectTitle.text = project?.title
//        binding.leaderText.text = project?.leader
        binding.typePrjText.text = project?.type_name
        binding.goalText.text = project?.goal
        binding.ideaText.text = project?.idea
        binding.timeText.text = project?.date_start + "-" + project?.date_end
        binding.reqText.text = project?.requirements
        binding.tvMestNumber.text = project?.difficulty.toString()

        when (project?.difficulty) {
            1 -> binding.constraintLayoutDifficulty.background = AppCompatResources.getDrawable(requireContext(), R.drawable.round_green_background)
            2 -> binding.constraintLayoutDifficulty.background = AppCompatResources.getDrawable(requireContext(), R.drawable.round_orange_background)
            else -> binding.constraintLayoutDifficulty.background = AppCompatResources.getDrawable(requireContext(), R.drawable.round_red_background)
        }
        mAdapter = TagsRecyclerAdapter(listOf())
        if (project?.tags != null) {
            mAdapter = TagsRecyclerAdapter(project?.tags!!)
            binding.rcvCardTags.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
        if (project?.vacant_places == 0) {
            binding.constraintLayoutStatusPositive.visibility = View.INVISIBLE
            binding.constraintLayoutStatusNegative.visibility = View.VISIBLE
        }
    }

    private fun initListeners(view: View) {

        binding.btnMyProjectsClose.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.submitBtn.setOnClickListener {
            val action: ProjectInformationFragmentDirections.ActionProjectInformationFragmentToApplicationFragment =
                ProjectInformationFragmentDirections.actionProjectInformationFragmentToApplicationFragment(project!!)
            action.project = project!!
            view.findNavController().navigate(action)
        }

        binding.btnProjectInformationAccount.setOnClickListener {
            view.findNavController().navigate(R.id.action_projectInformationFragment_to_accountFragment)
        }
    }
}