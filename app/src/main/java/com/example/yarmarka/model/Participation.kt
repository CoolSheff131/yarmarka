package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Participation(
    var state: String? = null,
    var role: String? = null,
    var skills: List<Skill>? = null
): Parcelable