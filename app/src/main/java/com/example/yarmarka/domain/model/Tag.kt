package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    var id: Int? = null,
    var tag: String? = null
): Parcelable