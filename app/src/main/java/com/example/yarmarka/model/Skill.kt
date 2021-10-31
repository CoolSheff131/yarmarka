package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skill(
    var id: Int? = null,
    var skill: String? = null
): Parcelable