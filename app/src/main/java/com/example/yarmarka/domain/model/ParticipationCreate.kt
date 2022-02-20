package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParticipationCreate(
    var motivation: String? = null,
    var skills: List<Int>? = null
): Parcelable