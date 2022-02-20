package com.example.yarmarka.domain.repository

import com.example.yarmarka.domain.model.*
import io.reactivex.Observable
import retrofit2.http.Query

interface ProjectsRepository {

    fun getProjects(page: Int): Observable<ProjectListResponse>

    fun getFilteredProjects(
        type: List<Int>?,
        state: List<Int>?,
        supervisor: List<Int>?,
        tags: List<Int>?,
        date_start: String?,
        date_end: String?,
        difficulty: List<Int>?,
        title: String?,
        page: Int
    ): Observable<ProjectListResponse>

    fun getTags(): Observable<List<Tag>>

    fun getTypes(): Observable<List<Type>>

    fun getStates(): Observable<List<State>>
}