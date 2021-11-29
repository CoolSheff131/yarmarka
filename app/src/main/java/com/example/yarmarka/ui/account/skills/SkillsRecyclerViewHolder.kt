package com.example.yarmarka.ui.account.skills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.SkillListItemBinding
import com.example.yarmarka.databinding.TagListItemHorizontalBinding
import com.example.yarmarka.model.Skill
import com.example.yarmarka.model.Tag

class SkillsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup
): RecyclerView.ViewHolder(inflater.inflate(R.layout.skill_list_item, parent, false)) {

    private val binding by viewBinding(SkillListItemBinding::bind)

    fun bind(skill: Skill) {
        binding.tvSkill.text = skill.skill
    }
}
