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
            type = filters.type,
            state = filters.state,
            supervisor = filters.supervisor,
            tags = filters.tags,
            date_start = filters.date_start,
            date_end = filters.date_end,
            difficulty = filters.difficulty,
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

    private fun getFilteredProjectListObserver(): Observer<List<Project>> {
        return object : Observer<List<Project>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Project>) {
                Log.d("mainSearch", "$t")
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