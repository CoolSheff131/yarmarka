package com.example.yarmarka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Candidate (
    var id: Int? = null,
    var fio: String? = null,
    var about: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var numz: String? = null,
    var course: Int? = null,
    var training_group:  String? = null,
    var skills: List<Skill>? = null,
    var experience: List<Int>? = null
): Parcelable