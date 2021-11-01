package com.example.yarmarka.model.api

data class ParticipationCreate (
    var idProject: Int? = null,
    var role: String? = null,
    var skills: List<Int>? = null
)
