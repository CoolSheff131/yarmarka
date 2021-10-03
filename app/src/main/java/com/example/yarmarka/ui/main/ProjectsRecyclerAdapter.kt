package com.example.yarmarka.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Project

class ProjectsRecyclerAdapter (private val projectsList: List<Project>) : RecyclerView.Adapter<ProjectsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProjectsRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProjectsRecyclerViewHolder, position: Int) {
        val project: Project = projectsList[position]
        holder.bind(project)
    }

    override fun getItemCount(): Int = projectsList.size
}