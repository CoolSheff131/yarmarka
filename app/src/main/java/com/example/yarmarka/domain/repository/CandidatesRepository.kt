package com.example.yarmarka.domain.repository

import com.example.yarmarka.domain.model.*
import io.reactivex.Observable

interface CandidatesRepository {

    fun getStudentById(token: String): Observable<Candidate>

    fun updateStudentInfo(token: String, candidateUpdate: CandidateUpdate): Observable<ResponseStatus>

    fun getStudentSkills(token: String): Observable<List<Skill>>

    fun getStudentApplications(token: String, page: Int): Observable<List<Participation>>

    fun getStudentParticipations(token: String, page: Int): Observable<List<Participation>>

    fun deleteStudentParticipationRequest(token: String, id: Int): Observable<ResponseStatus>

    fun createProjectRequest(
        token: String,
        id: Int,
        participate: ParticipationCreate
    ): Observable<ResponseStatus>
}