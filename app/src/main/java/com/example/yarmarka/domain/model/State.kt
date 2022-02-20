package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    var id: Int? = null,
    var state: String? = null
): Parcelable