package com.example.yarmarka.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.CardListItemBinding
import com.example.yarmarka.model.Project

class ProjectsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onProjectClickListener: OnProjectClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_list_item, parent, false)) {

    private val binding by viewBinding(CardListItemBinding::bind)

    fun bind(project: Project) {
        binding.cardTitle.text = project.title
        binding.tvDescription.text = project.goal
        binding.tvSupervisorName.text = project.leader
        binding.tvEstimatedTime.text = project.date_start + "-" + project.date_end
        binding.tvTotalPlaces.text = project.places.toString()
        binding.tvDifficulty.text = project.difficulty.toString() + "/10"
        binding.btnProjects.setOnClickListener {
            onProjectClickListener.onButtonClicked(project)
        }
    }

}