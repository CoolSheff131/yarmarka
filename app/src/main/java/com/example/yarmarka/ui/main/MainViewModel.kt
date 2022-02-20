package com.example.yarmarka.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.FilterObject
import com.example.yarmarka.domain.model.Project
import com.example.yarmarka.domain.model.ProjectListResponse
import com.example.yarmarka.domain.usecase.ProjectsUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val projectsUseCase: ProjectsUseCase
) : ViewModel() {

    private var projectListLiveData: MutableLiveData<ProjectListResponse?> = MutableLiveData()
    private var filteredProjectListLiveData: MutableLiveData<ProjectListResponse?> = MutableLiveData()

    val projectList: MutableLiveData<ProjectListResponse?>
        get() = projectListLiveData

    val filteredProjectList: MutableLiveData<ProjectListResponse?>
        get() = filteredProjectListLiveData

    fun getProjectList(page: Int = 0) {
        projectsUseCase.getProjects(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getProjectListObserver())
    }

    fun getFilteredProjectList(filters: FilterObject, page: Int = 0) {
        projectsUseCase.getFilteredProjects(
            type = filters.type,
            state = filters.state,
            supervisor = filters.supervisor,
            tags = filters.tags,
            date_start = null,
            date_end = null,
            difficulty = filters.difficulty,
            title = filters.title,
            page = page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFilteredProjectListObserver())
    }

    private fun getProjectListObserver(): Observer<ProjectListResponse> {
        return object : Observer<ProjectListResponse> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: ProjectListResponse) {
                Log.d("testing", "$t")
                projectListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("testing", e.message+"")
                projectListLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }

    private fun getFilteredProjectListObserver(): Observer<ProjectListResponse> {
        return object : Observer<ProjectListResponse> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: ProjectListResponse) {
                Log.d("mainSearch", "${t}")
                filteredProjectListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("mainSearch", "===${e.message}")
                filteredProjectListLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}