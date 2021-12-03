package com.example.yarmarka.ui.account.skills

import com.example.yarmarka.model.Project
import com.example.yarmarka.model.Skill
import com.example.yarmarka.model.Tag

interface OnSkillClickListener {

    fun onSkillTappedListener(skill: Skill)

    fun onSkillDeleteItemClicked(skill: Skill)
}