package com.example.yarmarka.data.repository

import com.example.yarmarka.data.remote.api.SupervisorsApi
import com.example.yarmarka.domain.model.SupervisorName
import com.example.yarmarka.domain.repository.SupervisorsRepository
import io.reactivex.Observable
import javax.inject.Inject

class SupervisorsRepositoryImpl @Inject constructor(
    private val supervisorsApi: SupervisorsApi
): SupervisorsRepository {

    override fun getSupervisorsNames(): Observable<List<SupervisorName>> {
        return supervisorsApi.getSupervisorsNames()
    }
}