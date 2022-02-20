package com.example.yarmarka.data.remote.api

import com.example.yarmarka.domain.model.Skill
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SkillsApi {

    @GET("api/skills")
    fun getSkills(): Observable<List<Skill>>

    @GET("api/skills")
    fun searchSkills(
        @Query("name") name: String
    ): Observable<List<Skill>>
}