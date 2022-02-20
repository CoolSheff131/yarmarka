package com.example.yarmarka.ui.main.projects

import com.example.yarmarka.domain.model.Project

interface OnProjectClickListener {

    fun onProjectItemClicked(project:Project)

    fun onButtonClicked(project: Project)

}
