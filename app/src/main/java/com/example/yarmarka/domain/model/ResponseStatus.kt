package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseStatus(
    var status: String
): Parcelable