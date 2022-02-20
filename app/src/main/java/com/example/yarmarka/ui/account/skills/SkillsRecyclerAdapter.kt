package com.example.yarmarka.ui.account.skills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.domain.model.Skill

class SkillsRecyclerAdapter(
    private val skillsList: List<Skill>,
    private val onSkillClickListener: OnSkillClickListener?
) : RecyclerView.Adapter<SkillsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SkillsRecyclerViewHolder(inflater, parent, onSkillClickListener)
    }

    override fun onBindViewHolder(holder: SkillsRecyclerViewHolder, position: Int) {
        val skill: Skill = skillsList[position]
        holder.bind(skill)
    }

    override fun getItemCount(): Int = skillsList.size

    fun getSkillsIds(): List<Int> {
        val list = mutableListOf<Int>()
        for (i in skillsList) {
            if (i.id != null) {
                list.add(i.id!!)
            }
        }

        return list
    }

    fun getSkills(): List<Skill> {
        return skillsList
    }
}
