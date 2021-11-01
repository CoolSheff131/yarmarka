package com.example.yarmarka.model.api

data class Candidate (
    var id: Int? = null,
    var fio: String? = null,
    var about: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var numZ: String? = null,
    var course: Int? = null,
    var training_group:  String? = null,
    var skills: List<Skill>? = emptyList<Skill>(),
    var experionce: String? = null
)