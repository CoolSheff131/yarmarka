package com.example.yarmarka.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Project
import com.example.yarmarka.network.services.ApiServiceProjects
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val api = ApiServiceProjects.buildService()

    private var projectListLiveData: MutableLiveData<List<Project>?> = MutableLiveData()

    val projectList: MutableLiveData<List<Project>?>
        get() = projectListLiveData

    fun getProjectList(page: Int = 0) {
        api.getProjects(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getProjectListObserverRx())
    }

    private fun getProjectListObserverRx(): Observer<List<Project>> {
        return object : Observer<List<Project>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Project>) {
                projectListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                projectListLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}