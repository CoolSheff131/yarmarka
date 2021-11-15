package com.example.yarmarka.repository

import com.example.yarmarka.model.Project
import com.example.yarmarka.network.services.ApiServiceProjects
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ProjectsRepository {

    private val api = ApiServiceProjects.buildService()

    fun getProjects(page: Int = 0) {
        api.getProjects(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getProjectsObserver(): Observer<List<Project>> {
        return object: Observer<List<Project>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Project>) {

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        }
    }
}