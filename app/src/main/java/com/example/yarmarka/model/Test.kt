package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Test(
    var data: List<Project>,
    var request: String
): Parcelable