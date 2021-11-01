package com.example.yarmarka.model.api

data class CandidateUpdate (
    var about: String? = null,
    var phone: String? = null,
    var skills: List<Skill>? = emptyList<Skill>(),
)
