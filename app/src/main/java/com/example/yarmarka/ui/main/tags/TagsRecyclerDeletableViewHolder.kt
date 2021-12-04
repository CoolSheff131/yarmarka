package com.example.yarmarka.ui.main.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.TagListItemDeletableBinding
import com.example.yarmarka.model.Tag

class TagsRecyclerDeletableViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onTagClickListener: OnTagClickListener
): RecyclerView.ViewHolder(inflater.inflate(R.layout.tag_list_item_deletable, parent, false)) {

    private val binding by viewBinding(TagListItemDeletableBinding::bind)

    fun bind(tag: Tag) {
        binding.tvTag.text = tag.tag
        binding.btnDelete.setOnClickListener {
            onTagClickListener.onTagDeleteItemClicked(tag)
        }
    }
}
