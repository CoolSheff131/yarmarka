package com.example.yarmarka.ui.filters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.SupervisorName
import com.example.yarmarka.model.Tag
import com.example.yarmarka.network.services.ApiServiceProjects
import com.example.yarmarka.network.services.ApiServiceSupervisors
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FiltersViewModel : ViewModel() {

    private val apiProjects = ApiServiceProjects.buildService()
    private val apiSupervisors = ApiServiceSupervisors.buildService()

    private var supervisorsLiveData: MutableLiveData<List<SupervisorName>?> = MutableLiveData()
    val supervisorsList: MutableLiveData<List<SupervisorName>?>
        get() = supervisorsLiveData

    private var tagsLiveData: MutableLiveData<List<Tag>?> = MutableLiveData()
    val tagsList: MutableLiveData<List<Tag>?>
        get() = tagsLiveData

    fun getSupervisors() {
        apiSupervisors.getSupervisorsNames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSupervisorsObserver())
    }

    fun getTags() {
        apiProjects.getTags()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getTagsObserver())
    }

    private fun getSupervisorsObserver(): Observer<List<SupervisorName>> {
        return object : Observer<List<SupervisorName>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<SupervisorName>) {
                supervisorsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                supervisorsLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }

    private fun getTagsObserver(): Observer<List<Tag>> {
        return object : Observer<List<Tag>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Tag>) {
                tagsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                tagsLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}