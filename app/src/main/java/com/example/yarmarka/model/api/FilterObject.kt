package com.example.yarmarka.model.api

data class FilterObject (
     var types: List<Int>? = null,
     var states: List<Int>? = null,
     var supervisor: List<Int>? = null,
     var tags: List<Int>? = null,
     var dateStart: String? = null,
     var dateEnd: String? = null,
     var difficulty: List<Int>? = null,
     var title: String? = null
)
