package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParticipationCreate(
    var id_project: Int? = null,
    var role: String? = null,
    var skills: List<Int>? = null
): Parcelable