package com.example.yarmarka.domain.repository

import com.example.yarmarka.domain.model.SupervisorName
import io.reactivex.Observable

interface SupervisorsRepository {

    fun getSupervisorsNames(): Observable<List<SupervisorName>>
}