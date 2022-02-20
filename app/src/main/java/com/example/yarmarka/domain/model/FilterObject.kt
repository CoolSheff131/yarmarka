package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterObject(
    var type: List<Int>? = null,
    var state: List<Int>? = null,
    var supervisor: List<Int>? = null,
    var tags: List<Int>? = null,
    var date_start: String? = null,
    var date_end: String? = null,
    var difficulty: List<Int>? = null,
    var title: String? = null
): Parcelable