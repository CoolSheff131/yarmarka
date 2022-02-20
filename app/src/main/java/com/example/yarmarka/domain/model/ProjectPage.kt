package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProjectPage (
    var current_page: Int? = null,
    var first_page_url: String? = null,
    var from: Int? = null,
    var to: Int? = null,
    var total: Int? = null,
    var prev_page_url: String? = null,
    var per_page: Int? = null,
    var path: String? = null,
    var next_page_url: String? = null,
    var last_page_url: String? = null,
    var last_page: Int? = null,
    var project: Project? = null
): Parcelable