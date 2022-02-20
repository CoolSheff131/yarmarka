package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Type(
    var id: Int? = null,
    var type: String? = null
): Parcelable