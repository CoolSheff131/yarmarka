package com.example.yarmarka.ui.main.applications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Project

class ApplicationsRecyclerAdapter(
    private val projectsList: List<Project>,
    private val onProjectClickListener: OnApplicationClickListener,
    private val context: Context
) : RecyclerView.Adapter<ApplicationsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ApplicationsRecyclerViewHolder(inflater, parent, onProjectClickListener, context)
    }

    override fun onBindViewHolder(holder: ApplicationsRecyclerViewHolder, position: Int) {
        val project: Project = projectsList[position]
        holder.bind(project)
        holder.itemView.setOnClickListener {
            onProjectClickListener.onProjectItemClicked(project)
        }
    }

    override fun getItemCount(): Int = projectsList.size
}