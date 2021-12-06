package com.example.yarmarka.ui.main.applications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yarmarka.R
import com.example.yarmarka.databinding.CardListItemBinding
import com.example.yarmarka.model.Participation
import com.example.yarmarka.model.Project
import com.example.yarmarka.ui.main.tags.TagsRecyclerAdapter
import com.example.yarmarka.utils.convertDate

class ApplicationsRecyclerViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val onProjectClickListener: OnApplicationClickListener,
    private val context: Context
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.application_list_item, parent, false)) {

    private val binding by viewBinding(CardListItemBinding::bind)

    private lateinit var mAdapter: TagsRecyclerAdapter

    fun bind(participation: Participation) {

    }
}
