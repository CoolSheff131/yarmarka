package com.example.yarmarka.ui.main.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.TagListItemVerticalBinding
import com.example.yarmarka.model.Tag

class TagsRecyclerVerticalViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onTagClickListener: OnTagClickListener
): RecyclerView.ViewHolder(inflater.inflate(R.layout.tag_list_item_vertical, parent, false)) {

    private val binding by viewBinding(TagListItemVerticalBinding::bind)

    fun bind(tag: Tag) {
        binding.tvTag.text = tag.tag
    }
}
