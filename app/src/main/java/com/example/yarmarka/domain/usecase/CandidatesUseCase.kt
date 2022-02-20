package com.example.yarmarka.domain.usecase

import com.example.yarmarka.data.repository.CandidatesRepositoryImpl
import com.example.yarmarka.domain.model.*
import io.reactivex.Observable
import javax.inject.Inject

class CandidatesUseCase @Inject constructor(
    private val candidatesRepositoryImpl: CandidatesRepositoryImpl
) {

    fun getStudentById(token: String): Observable<Candidate> {
        return candidatesRepositoryImpl.getStudentById(token = token)
    }

    fun updateStudentInfo(
        token: String,
        candidateUpdate: CandidateUpdate
    ): Observable<ResponseStatus> {
        return candidatesRepositoryImpl.updateStudentInfo(token = token, candidateUpdate = candidateUpdate)
    }

    fun getStudentSkills(token: String): Observable<List<Skill>> {
        return candidatesRepositoryImpl.getStudentSkills(token = token)
    }

    fun getStudentApplications(token: String, page: Int): Observable<List<Participation>> {
        return candidatesRepositoryImpl.getStudentApplications(token = token, page = page)
    }

    fun getStudentParticipations(
        token: String,
        page: Int
    ): Observable<List<Participation>> {
        return candidatesRepositoryImpl.getStudentParticipations(token = token, page = page)
    }

    fun deleteStudentParticipationRequest(
        token: String,
        id: Int
    ): Observable<ResponseStatus> {
        return candidatesRepositoryImpl.deleteStudentParticipationRequest(token = token, id = id)
    }

    fun createProjectRequest(
        token: String,
        id: Int,
        participate: ParticipationCreate
    ): Observable<ResponseStatus> {
        return candidatesRepositoryImpl.createProjectRequest(
            token = token,
            id = id,
            participate = participate
        )
    }
}