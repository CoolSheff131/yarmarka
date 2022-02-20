package com.example.yarmarka.ui.filters.supervisors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.SupervisorListItemBinding
import com.example.yarmarka.domain.model.SupervisorName

class SupervisorsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onSupervisorClickListener: OnSupervisorClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.supervisor_list_item, parent, false)) {

    private val binding by viewBinding(SupervisorListItemBinding::bind)

    fun bind(supervisor: SupervisorName) {
        binding.tvSupervisorName.text = supervisor.fio
        binding.btnDelete.setOnClickListener {
            onSupervisorClickListener.onSupervisorDeleteItemClicked(supervisor)
        }
    }
}