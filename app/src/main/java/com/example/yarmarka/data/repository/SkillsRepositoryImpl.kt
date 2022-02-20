package com.example.yarmarka.data.repository

import com.example.yarmarka.data.remote.api.SkillsApi
import com.example.yarmarka.domain.model.Skill
import com.example.yarmarka.domain.repository.SkillsRepository
import io.reactivex.Observable
import javax.inject.Inject

class SkillsRepositoryImpl @Inject constructor(
    private val skillsApi: SkillsApi
): SkillsRepository {

    override fun getSkills(): Observable<List<Skill>> {
        return skillsApi.getSkills()
    }

    override fun searchSkills(name: String): Observable<List<Skill>> {
        return skillsApi.searchSkills(name = name)
    }
}