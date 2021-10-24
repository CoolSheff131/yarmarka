package com.example.yarmarka.model.api

data class Participation (
    var state: String? = null,
    var role: String? = null,
    var skills: List<Skill>? = null,
    var project: Project? = null
)
