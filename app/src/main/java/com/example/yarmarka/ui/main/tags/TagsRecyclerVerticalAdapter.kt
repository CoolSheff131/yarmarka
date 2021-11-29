package com.example.yarmarka.ui.main.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yarmarka.model.Tag

class TagsRecyclerVerticalAdapter(
    private val tagsList: List<Tag>,
    private val onTagClickListener: OnTagClickListener
) : RecyclerView.Adapter<TagsRecyclerVerticalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsRecyclerVerticalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TagsRecyclerVerticalViewHolder(inflater, parent, onTagClickListener)
    }

    override fun onBindViewHolder(holder: TagsRecyclerVerticalViewHolder, position: Int) {
        val tag: Tag = tagsList[position]
        holder.bind(tag)
    }

    override fun getItemCount(): Int = tagsList.size
}
