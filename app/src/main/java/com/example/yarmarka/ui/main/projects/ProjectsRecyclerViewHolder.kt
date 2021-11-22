package com.example.yarmarka.ui.main.projects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.CardListItemBinding
import com.example.yarmarka.model.Project
import com.example.yarmarka.utils.convertDate

class ProjectsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onProjectClickListener: OnProjectClickListener,
    private val context: Context
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_list_item, parent, false)) {

    private val binding by viewBinding(CardListItemBinding::bind)

    fun bind(project: Project) {
        binding.cardTitle.text = project.title
        binding.tvDescription.text = project.goal
        binding.tvSupervisorName.text = project.supervisor_name
        binding.tvEstimatedTime.text = convertDate(project.date_start) + " - " + convertDate(project.date_end)
        binding.tvTotalPlaces.text = project.places.toString()
        binding.tvDifficulty.text = project.difficulty.toString() + "/10"
        when (project.difficulty) {
            in 1..4 -> binding.constraintLayoutDifficulty.background = AppCompatResources.getDrawable(context, R.drawable.round_green_background)
            in 5..7 -> binding.constraintLayoutDifficulty.background = AppCompatResources.getDrawable(context, R.drawable.round_orange_background)
            else -> binding.constraintLayoutDifficulty.background = AppCompatResources.getDrawable(context, R.drawable.round_red_background)
        }
        if (project.vacant_places == 0) {
            binding.tvRecruitmentOpen.visibility = View.INVISIBLE
            binding.tvRecruitmentClose.visibility = View.VISIBLE
        }
        binding.btnProjects.setOnClickListener {
            onProjectClickListener.onButtonClicked(project)
        }
    }

}