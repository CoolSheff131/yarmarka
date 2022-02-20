package com.example.yarmarka.data.repository

import com.example.yarmarka.data.remote.api.ProjectsApi
import com.example.yarmarka.domain.model.*
import com.example.yarmarka.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsApi: ProjectsApi
): ProjectsRepository {

    override fun getProjects(page: Int): Observable<ProjectListResponse> {
        return projectsApi.getProjects(page = page)
    }

    override fun getFilteredProjects(
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
        return projectsApi.getFilteredProjects(
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

    override fun getTags(): Observable<List<Tag>> {
        return projectsApi.getTags()
    }

    override fun getTypes(): Observable<List<Type>> {
        return projectsApi.getTypes()
    }

    override fun getStates(): Observable<List<State>> {
        return projectsApi.getStates()
    }
}