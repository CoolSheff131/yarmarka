package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Participation(
    var id: Int? = null,
    var state: String? = null,
    var motivation: String? = null,
    var skills: List<Skill>? = null,
    var project: Project? = null
): Parcelable