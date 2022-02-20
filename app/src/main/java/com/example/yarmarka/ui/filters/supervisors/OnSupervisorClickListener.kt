package com.example.yarmarka.ui.filters.supervisors

import com.example.yarmarka.domain.model.SupervisorName

interface OnSupervisorClickListener {

    fun onSupervisorDeleteItemClicked(supervisor: SupervisorName)
}
