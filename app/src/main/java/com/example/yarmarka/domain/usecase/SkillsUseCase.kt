package com.example.yarmarka.domain.usecase

import com.example.yarmarka.data.repository.SkillsRepositoryImpl
import com.example.yarmarka.domain.model.Skill
import io.reactivex.Observable
import javax.inject.Inject

class SkillsUseCase @Inject constructor(
    private val skillsRepositoryImpl: SkillsRepositoryImpl
) {

    fun getSkills(): Observable<List<Skill>> {
        return skillsRepositoryImpl.getSkills()
    }

    fun searchSkills(name: String): Observable<List<Skill>> {
        return skillsRepositoryImpl.searchSkills(name = name)
    }
}