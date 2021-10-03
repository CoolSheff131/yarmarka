package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    var id: Int? = null,
    var title: String? = null,
    var places: Int? = null,
    var goal: String? = null,
    var idea: String? = null,
    var difficulty: Int? = null,
    var date_start: String? = null,
    var date_end: String? = null,
    var requirements: String? = null,
    var customer: String? = null,
    var expected_result: String? = null,
    var additional_inf: String? = null,
    var result: String? = null,
    var updated_at: String? = null,
    var deleted_at: String? = null,
    var type_name: String? = null,
    var user_name: String? = null,
    var vacant_places: Int? = null,
    var state_name: String? = null,
    var leader: String? = null
) : Parcelable
