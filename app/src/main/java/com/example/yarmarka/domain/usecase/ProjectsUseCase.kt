package com.example.yarmarka.domain.usecase

import com.example.yarmarka.data.repository.ProjectsRepositoryImpl
import com.example.yarmarka.domain.model.*
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsUseCase @Inject constructor(
    private val projectsRepositoryImpl: ProjectsRepositoryImpl
) {

    fun getProjects(page: Int): Observable<ProjectListResponse> {
        return projectsRepositoryImpl.getProjects(page = page)
    }

    fun getFilteredProjects(
        type: List<Int>?,
        state: List<Int>?,
        supervisor: List<Int>?,
        tags: List<Int>?,
        date_start: String?,
        date_end: String?,
        difficulty: List<Int>?,
        title: String?,
        page: Int
    ): Observable<ProjectListResponse> {
        return projectsRepositoryImpl.getFilteredProjects(
            type = type,
            state = state,
            supervisor = supervisor,
            tags = tags,
            date_start = date_start,
            date_end = date_end,
            difficulty = difficulty,
            title = title,
            page = page
        )
    }

    fun getTags(): Observable<List<Tag>> {
        return projectsRepositoryImpl.getTags()
    }

    fun getTypes(): Observable<List<Type>> {
        return projectsRepositoryImpl.getTypes()
    }

    fun getStates(): Observable<List<State>> {
        return projectsRepositoryImpl.getStates()
    }
}