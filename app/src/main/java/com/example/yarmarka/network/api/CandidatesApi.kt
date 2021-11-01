package com.example.yarmarka.network.api

import com.example.yarmarka.model.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface CandidatesApi {
    @GET("/api/candidate/{id}")
    fun getStudentById(@Path("id") id: Int): Single<Candidate>

    @PUT("/api/candidate/{id}")
    fun updateStudentInfo(
        @Path("id") id: Int,
        @Body candidateUpdate: CandidateUpdate
    )

    @GET("/api/skills")
    fun getSkills(): Observable<List<Skill>>

    @GET("/api/participations/{id}")
    fun getStudentParticipations(
        @Path("id") id: Int,
        @Query("page") page: Int
    ): Observable<List<Project>>

    @DELETE("/api/participations/{id}")
    fun deleteStudentParticipationRequest(
       @Path("id") id: Int,
       @Query("id_project") id_project: Int
    )

    @POST("/api/participations/{id}")
    fun createProjectRequest(
        @Path("id") id: Int,
        @Body participate: ParticipationCreate
    )
}