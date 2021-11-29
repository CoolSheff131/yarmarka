package com.example.yarmarka.ui.main.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Tag

class TagsRecyclerHorizontalAdapter(
    private val tagsList: List<Tag>,
    private val onTagClickListener: OnTagClickListener
) : RecyclerView.Adapter<TagsRecyclerHorizontalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsRecyclerHorizontalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TagsRecyclerHorizontalViewHolder(inflater, parent, onTagClickListener)
    }

    override fun onBindViewHolder(holder: TagsRecyclerHorizontalViewHolder, position: Int) {
        val tag: Tag = tagsList[position]
        holder.bind(tag)
    }

    override fun getItemCount(): Int = tagsList.size
}
