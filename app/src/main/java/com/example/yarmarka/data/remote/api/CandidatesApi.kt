package com.example.yarmarka.data.remote.api

import com.example.yarmarka.domain.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface CandidatesApi {
    @GET("api/candidate")
    fun getStudentById(@Header("x-api-key") token: String): Observable<Candidate>

    @PUT("api/candidate")
    fun updateStudentInfo(
        @Header("x-api-key") token: String,
        @Body candidateUpdate: CandidateUpdate
    ): Observable<ResponseStatus>

    @GET("api/candidate/skills")
    fun getStudentSkills(@Header("x-api-key") token: String): Observable<List<Skill>>

    @GET("api/participations")
    fun getStudentApplications(
        @Query("page") page: Int,
        @Header("x-api-key") token: String
    ): Observable<List<Participation>>

    @GET("api/participations/projects")
    fun getStudentParticipations(
        @Query("page") page: Int,
        @Header("x-api-key") token: String
    ): Observable<List<Participation>>

    @DELETE("api/participations/{id}")
    fun deleteStudentParticipationRequest(
       @Path("id") id: Int,
       @Query("x-api-key") token: String
    ): Observable<ResponseStatus>

    @POST("api/participations/{id}")
    fun createProjectRequest(
        @Path("id") id: Int,
        @Header("x-api-key") token: String,
        @Body participate: ParticipationCreate
    ): Observable<ResponseStatus>
}