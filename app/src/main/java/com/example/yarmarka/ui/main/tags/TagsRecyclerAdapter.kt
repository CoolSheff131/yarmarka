package com.example.yarmarka.ui.main.tags

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Project
import com.example.yarmarka.model.Tag

class TagsRecyclerAdapter(
    private val tagsList: List<Tag>,
    private val onTagClickListener: OnTagClickListener
) : RecyclerView.Adapter<TagsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TagsRecyclerViewHolder(inflater, parent, onTagClickListener)
    }

    override fun onBindViewHolder(holder: TagsRecyclerViewHolder, position: Int) {
        val tag: Tag = tagsList[position]
        holder.bind(tag)
    }

    override fun getItemCount(): Int = tagsList.size
}