package com.example.yarmarka.network.api

import com.example.yarmarka.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface CandidatesApi {
    @GET("api/candidate/{id}")
    fun getStudentById(@Header("x-api-key") token: String): Single<Candidate>

    @PUT("api/candidate/{id}")
    fun updateStudentInfo(
        @Header("x-api-key") token: String,
        @Body candidateUpdate: CandidateUpdate
    )

    @GET("api/skills")
    fun getSkills(): Observable<List<Skill>>

    @GET("api/candidate/skills")
    fun getStudentSkills(@Header("x-api-key") token: String): Observable<List<Skill>>

    @GET("api/participations")
    fun getStudentParticipations(
        @Query("page") page: Int,
        @Header("x-api-key") token: String
    ): Observable<List<Project>>

    @DELETE("api/participations/{id}")
    fun deleteStudentParticipationRequest(
       @Path("id") id: Int,
       @Query("x-api-key") token: String
    )

    @POST("api/participations/{id}")
    fun createProjectRequest(
        @Path("id") id: Int,
        @Path("x-api-key") token: String,
        @Body participate: ParticipationCreate
    )
}