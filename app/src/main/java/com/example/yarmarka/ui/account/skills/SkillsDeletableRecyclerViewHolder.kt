package com.example.yarmarka.ui.account.skills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.SkillListItemBinding
import com.example.yarmarka.databinding.SkillListItemDeletableBinding
import com.example.yarmarka.databinding.TagListItemHorizontalBinding
import com.example.yarmarka.model.Skill
import com.example.yarmarka.model.Tag

class SkillsDeletableRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val onSkillClickListener: OnSkillClickListener
): RecyclerView.ViewHolder(inflater.inflate(R.layout.skill_list_item_deletable, parent, false)) {

    private val binding by viewBinding(SkillListItemDeletableBinding::bind)

    fun bind(skill: Skill) {
        binding.tvSkill.text = skill.skill
        binding.btnDelete.setOnClickListener {
            onSkillClickListener.onSkillDeleteItemClicked(skill)
        }
    }
}
