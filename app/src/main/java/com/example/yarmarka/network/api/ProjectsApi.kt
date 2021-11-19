package com.example.yarmarka.network.api

import com.example.yarmarka.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProjectsApi {

    @GET("/api/projects")
    fun getProjects(
        @Query("page") page: Int = 0
    ): Observable<List<Project>>

    @GET("/api/projects/filter")
    fun getFilteredProjects(
        @Query("type") type: IntArray?,
        @Query("state") state: IntArray?,
        @Query("supervisor") supervisor: IntArray?,
        @Query("tags") tags: IntArray?,
        @Query("date_start") date_start: String?,
        @Query("date_end") date_end: String?,
        @Query("difficulty") difficulty: IntArray?,
        @Query("title") title: String?,
        @Query("page") page: Int = 0
    ): Observable<List<Project>>

    @GET("/api/tags")
    fun getTags(): Observable<List<Tag>>

    @GET("/api/types")
    fun getTypes(): Observable<List<Type>>

    @GET("/api/states")
    fun getStates(): Observable<List<State>>


}