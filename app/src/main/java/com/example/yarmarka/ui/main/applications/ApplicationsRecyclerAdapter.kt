package com.example.yarmarka.ui.main.applications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Participation
import com.example.yarmarka.model.Project
import com.example.yarmarka.ui.main.projects.OnProjectClickListener

class ApplicationsRecyclerAdapter(
    private val participationList: List<Participation>,
    private val onApplicationClickListener: OnProjectClickListener
) : RecyclerView.Adapter<ApplicationsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ApplicationsRecyclerViewHolder(inflater, parent, onApplicationClickListener)
    }

    override fun onBindViewHolder(holder: ApplicationsRecyclerViewHolder, position: Int) {
        val participation = participationList[position]
        holder.bind(participation)
        holder.itemView.setOnClickListener {
            onApplicationClickListener.onButtonClicked(participation.project!!)
        }
    }

    override fun getItemCount(): Int = participationList.size
}