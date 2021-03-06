package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupervisorName(
    var id: Int? = null,
    var fio: String? = null
): Parcelable