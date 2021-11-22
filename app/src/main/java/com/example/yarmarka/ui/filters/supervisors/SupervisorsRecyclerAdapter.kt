package com.example.yarmarka.ui.filters.supervisors

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.SupervisorName

class SupervisorsRecyclerAdapter(
    private val supervisorsList: List<SupervisorName>,
    private val onSupervisorClickListener: OnSupervisorClickListener
) : RecyclerView.Adapter<SupervisorsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SupervisorsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SupervisorsRecyclerViewHolder(inflater, parent, onSupervisorClickListener)
    }

    override fun onBindViewHolder(holder: SupervisorsRecyclerViewHolder, position: Int) {
        val supervisor: SupervisorName = supervisorsList[position]
        holder.bind(supervisor)
    }

    override fun getItemCount(): Int = supervisorsList.size
}