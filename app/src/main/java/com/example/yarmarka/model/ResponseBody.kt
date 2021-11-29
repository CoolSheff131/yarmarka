package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseBody(
    var status: String
): Parcelable