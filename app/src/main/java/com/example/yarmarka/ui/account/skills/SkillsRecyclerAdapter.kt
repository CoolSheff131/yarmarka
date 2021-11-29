package com.example.yarmarka.ui.account.skills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Skill
import com.example.yarmarka.model.Tag

class SkillsRecyclerAdapter(
    private val skillsList: List<Skill>
) : RecyclerView.Adapter<SkillsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SkillsRecyclerViewHolder(inflater, parent)
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
}
