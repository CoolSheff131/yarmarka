package com.example.yarmarka.data.remote.api

import com.example.yarmarka.domain.model.SupervisorName
import io.reactivex.Observable
import retrofit2.http.GET

interface SupervisorsApi {
    @GET("/api/supervisors/names")
    fun getSupervisorsNames(): Observable<List<SupervisorName>>
}