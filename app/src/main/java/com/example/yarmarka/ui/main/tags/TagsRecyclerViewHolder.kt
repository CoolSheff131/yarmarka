package com.example.yarmarka.ui.main.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.TagListItemBinding
import com.example.yarmarka.domain.model.Tag

class TagsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.tag_list_item, parent, false)) {

    private val binding by viewBinding(TagListItemBinding::bind)

    fun bind(tag: Tag) {
        binding.tvTag.text = tag.tag
    }
}
