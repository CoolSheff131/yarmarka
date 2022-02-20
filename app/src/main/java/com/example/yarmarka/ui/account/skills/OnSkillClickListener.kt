package com.example.yarmarka.ui.account.skills

import com.example.yarmarka.domain.model.Skill

interface OnSkillClickListener {

    fun onSkillTappedListener(skill: Skill)

    fun onSkillDeleteItemClicked(skill: Skill)
}