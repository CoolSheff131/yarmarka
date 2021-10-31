package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CandidateUpdate(
    var about: String? = null,
    var phone: String? = null,
    var skills: List<Int>? = null
): Parcelable