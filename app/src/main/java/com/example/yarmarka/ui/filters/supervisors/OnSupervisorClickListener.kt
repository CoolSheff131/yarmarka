package com.example.yarmarka.ui.filters.supervisors

import com.example.yarmarka.model.Project
import com.example.yarmarka.model.SupervisorName
import com.example.yarmarka.model.Tag

interface OnSupervisorClickListener {

    fun onSupervisorDeleteItemClicked(supervisor: SupervisorName)
}
