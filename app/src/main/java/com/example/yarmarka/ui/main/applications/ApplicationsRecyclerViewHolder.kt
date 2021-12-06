package com.example.yarmarka.ui.main.applications

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.ApplicationListItemBinding
import com.example.yarmarka.model.Participation
import com.example.yarmarka.ui.main.projects.OnProjectClickListener
import com.example.yarmarka.utils.*

class ApplicationsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onProjectClickListener: OnProjectClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.application_list_item, parent, false)) {

    private val binding by viewBinding(ApplicationListItemBinding::bind)

    fun bind(participation: Participation) {
        binding.tvState.text = participation.state
        Log.d("bruh", "${participation.id_state}")
        when (participation.id_state) {
            1 -> {
                binding.tvState.setCompoundDrawablesWithIntrinsicBounds(consideration_state, 0, 0, 0);
                binding.btnProjects.setBackgroundResource(consideration_color)
                binding.colorLine.setBackgroundResource(consideration_color)
            }
            2 -> {
                binding.tvState.setCompoundDrawablesWithIntrinsicBounds(participation_state, 0, 0, 0);
                binding.btnProjects.setBackgroundResource(participation_color)
                binding.colorLine.setBackgroundResource(participation_color)
            }
            3 -> {
                binding.tvState.setCompoundDrawablesWithIntrinsicBounds(finished_state, 0, 0, 0);
                binding.btnProjects.setBackgroundResource(finished_color)
                binding.colorLine.setBackgroundResource(finished_color)
            }
            4 -> {
                binding.tvState.setCompoundDrawablesWithIntrinsicBounds(declined_state, 0, 0, 0);
                binding.btnProjects.setBackgroundResource(declined_color)
                binding.colorLine.setBackgroundResource(declined_color)
            }
            5 -> {
                binding.tvState.setCompoundDrawablesWithIntrinsicBounds(recalled_state, 0, 0, 0);
                binding.btnProjects.setBackgroundResource(recalled_color)
                binding.colorLine.setBackgroundResource(recalled_color)
            }
            6 -> {
                binding.tvState.setCompoundDrawablesWithIntrinsicBounds(excluded_state, 0, 0, 0);
                binding.btnProjects.setBackgroundResource(excluded_color)
                binding.colorLine.setBackgroundResource(excluded_color)
            }
        }
        binding.cardTitle.text = participation.project?.title
        binding.tvSupervisorName.text = participation.project?.supervisor_name
        binding.btnProjects.setOnClickListener {
            onProjectClickListener.onButtonClicked(participation.project!!)
        }
    }
}
