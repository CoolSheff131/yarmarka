package com.example.yarmarka.network

import com.example.yarmarka.model.Candidate
import com.example.yarmarka.model.Project
import com.example.yarmarka.model.ProjectPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("/api/projects?{page}")
    fun getProjectsByPage(
        @Path("page") page: Int
    ): Call<ProjectPage>

    @GET("/api/projects/close")
    fun getClosedProjects(): Call<List<Project>>

    @GET("/api/projects/active")
    fun getActiveProjects(): Call<List<Project>>

    @GET("/api/projects/open")
    fun getOpenProjects(): Call<List<Project>>

    @GET("/api/projects/process")
    fun getProcessProjects(): Call<List<Project>>

    @GET("/api/projects/{projectId}")
    fun getProjectById(
        @Path("projectId") projectId: Int
    ): Call<Project>

    @POST("/api/project/{projectId}/{candidate}")
    fun addCandidate(
        @Path("projectId") projectId: Int,
        @Path("candidate") candidate: Candidate
    )
}