package com.example.yarmarka.ui.account.dialog_skills_choice

import com.example.yarmarka.model.Skill

interface OnSkillsDialogClickedListener {

    fun onAdmitClicked(chosenSkills: List<Skill>)
}