package com.example.yarmarka.domain.usecase

import com.example.yarmarka.data.repository.SupervisorsRepositoryImpl
import com.example.yarmarka.domain.model.SupervisorName
import io.reactivex.Observable
import javax.inject.Inject

class SupervisorsUseCase @Inject constructor(
    private val supervisorsRepositoryImpl: SupervisorsRepositoryImpl
) {

    fun getSupervisorsNames(): Observable<List<SupervisorName>> {
        return supervisorsRepositoryImpl.getSupervisorsNames()
    }
}