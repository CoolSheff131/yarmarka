package com.example.yarmarka.network.api

import com.example.yarmarka.model.SupervisorName
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface SupervisorsApi {
    @GET("/api/supervisors/names")
    fun getSupervisorsNames(): Observable<List<SupervisorName>>
}