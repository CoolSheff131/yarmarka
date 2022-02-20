package com.example.yarmarka.data.remote.api

import com.example.yarmarka.domain.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectsApi {

    @GET("api/projects")
    fun getProjects(
        @Query("page") page: Int = 0
    ): Observable<ProjectListResponse>

    @GET("api/projects/filter")
    fun getFilteredProjects(
        @Query("type[]") type:  List<Int>?,
        @Query("state[]") state:  List<Int>?,
        @Query("supervisor[]") supervisor: List<Int>?,
        @Query("tags[]") tags:  List<Int>?,
        @Query("date_start") date_start: String?,
        @Query("date_end") date_end: String?,
        @Query("difficulty[]") difficulty: List<Int>?,
        @Query("title") title: String?,
        @Query("page") page: Int = 0
    ): Observable<ProjectListResponse>

    @GET("api/tags")
    fun getTags(): Observable<List<Tag>>

    @GET("api/types")
    fun getTypes(): Observable<List<Type>>

    @GET("api/states")
    fun getStates(): Observable<List<State>>


}