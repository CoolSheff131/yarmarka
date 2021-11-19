package com.example.yarmarka.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.FilterObject
import com.example.yarmarka.model.Project
import com.example.yarmarka.network.services.ApiServiceProjects
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val api = ApiServiceProjects.buildService()

    private var projectListLiveData: MutableLiveData<List<Project>?> = MutableLiveData()
    private var filteredProjectListLiveData: MutableLiveData<List<Project>?> = MutableLiveData()

    val projectList: MutableLiveData<List<Project>?>
        get() = projectListLiveData

    val filteredProjectList: MutableLiveData<List<Project>?>
        get() = filteredProjectListLiveData

    fun getProjectList(page: Int = 0) {
        api.getProjects(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getProjectListObserver())
    }

    fun getFilteredProjectList(filters: FilterObject, page: Int = 0) {
        api.getFilteredProjects(
            type = filters.type?.toIntArray(),
            state = filters.state?.toIntArray(),
            supervisor = filters.supervisor?.toIntArray(),
            tags = filters.tags?.toIntArray(),
            date_start = filters.date_start,
            date_end = filters.date_end,
            difficulty = filters.difficulty?.toIntArray(),
            title = "",
            page = page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFilteredProjectListObserver())
    }

    private fun getProjectListObserver(): Observer<List<Project>> {
        return object : Observer<List<Project>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Project>) {
                Log.d("testing", "here is not an error")
                projectListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("testing", "here is an error")
                Log.d("testing", e.message+"")
                com.example.yarmarka.utils.error = e.message!!
                projectListLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }

    private fun getFilteredProjectListObserver(): Observer<List<Project>> {
        return object : Observer<List<Project>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Project>) {
                filteredProjectListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                filteredProjectListLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}