package com.example.yarmarka.ui.main.applications

import com.example.yarmarka.model.Project

interface OnApplicationClickListener {

    fun onProjectItemClicked(project:Project)

    fun onButtonClicked(project: Project)

}
