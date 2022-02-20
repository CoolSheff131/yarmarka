package com.example.yarmarka.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProjectListResponse(
    val data: List<Project>,
    val projectCount: Int
) : Parcelable