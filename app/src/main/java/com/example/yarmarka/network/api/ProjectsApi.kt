package com.example.yarmarka.network.api

import com.example.yarmarka.model.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProjectsApi {

    @GET("/api/projects")
    fun getProjects(
        @Query("page") page: Int = 0
    ): Observable<List<ProjectPage>>

    @GET("/api/projects/filter")
    fun getFilteredProjects(
        @Body filters: FilterObject,
        @Query("page") page: Int = 0
    ): Observable<ProjectPage>

    @GET("/api/tags")
    fun getTags(): Observable<List<Tag>>

    @GET("/api/types")
    fun getTypes(): Observable<List<Type>>

    @GET("/api/states")
    fun getStates(): Observable<List<State>>


}