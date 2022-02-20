package com.example.yarmarka.data.repository

import com.example.yarmarka.data.remote.api.CandidatesApi
import com.example.yarmarka.domain.model.*
import com.example.yarmarka.domain.repository.CandidatesRepository
import io.reactivex.Observable
import javax.inject.Inject

class CandidatesRepositoryImpl @Inject constructor(
    private val candidatesApi: CandidatesApi
) : CandidatesRepository {

    override fun getStudentById(token: String): Observable<Candidate> {
        return candidatesApi.getStudentById(token = token)
    }

    override fun updateStudentInfo(
        token: String,
        candidateUpdate: CandidateUpdate
    ): Observable<ResponseStatus> {
        return candidatesApi.updateStudentInfo(token = token, candidateUpdate = candidateUpdate)
    }

    override fun getStudentSkills(token: String): Observable<List<Skill>> {
        return candidatesApi.getStudentSkills(token = token)
    }

    override fun getStudentApplications(token: String, page: Int): Observable<List<Participation>> {
        return candidatesApi.getStudentApplications(token = token, page = page)
    }

    override fun getStudentParticipations(
        token: String,
        page: Int
    ): Observable<List<Participation>> {
        return candidatesApi.getStudentParticipations(token = token, page = page)
    }

    override fun deleteStudentParticipationRequest(
        token: String,
        id: Int
    ): Observable<ResponseStatus> {
        return candidatesApi.deleteStudentParticipationRequest(token = token, id = id)
    }

    override fun createProjectRequest(
        token: String,
        id: Int,
        participate: ParticipationCreate
    ): Observable<ResponseStatus> {
        return candidatesApi.createProjectRequest(token = token, id = id, participate = participate)
    }
}