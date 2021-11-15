package com.example.yarmarka.ui.main.projects

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Project

class ProjectsRecyclerAdapter(
    private val projectsList: List<Project>,
    private val onProjectClickListener: OnProjectClickListener,
    private val context: Context
) : RecyclerView.Adapter<ProjectsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProjectsRecyclerViewHolder(inflater, parent, onProjectClickListener, context)
    }

    override fun onBindViewHolder(holder: ProjectsRecyclerViewHolder, position: Int) {
        val project: Project = projectsList[position]
        holder.bind(project)
        holder.itemView.setOnClickListener {
            onProjectClickListener.onProjectItemClicked(project)
        }
    }

    override fun getItemCount(): Int = projectsList.size
}