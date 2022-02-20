package com.example.yarmarka.domain.repository

import com.example.yarmarka.domain.model.Skill
import io.reactivex.Observable

interface SkillsRepository {

    fun getSkills(): Observable<List<Skill>>

    fun searchSkills(name: String): Observable<List<Skill>>
}