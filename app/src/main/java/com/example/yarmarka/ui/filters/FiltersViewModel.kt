package com.example.yarmarka.ui.filters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.SupervisorName
import com.example.yarmarka.domain.model.Tag
import com.example.yarmarka.domain.usecase.ProjectsUseCase
import com.example.yarmarka.domain.usecase.SupervisorsUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    private val supervisorsUseCase: SupervisorsUseCase,
    private val projectsUseCase: ProjectsUseCase
) : ViewModel() {

    private var supervisorsLiveData: MutableLiveData<List<SupervisorName>?> = MutableLiveData()
    val supervisorsList: MutableLiveData<List<SupervisorName>?>
        get() = supervisorsLiveData

    private var tagsLiveData: MutableLiveData<List<Tag>?> = MutableLiveData()
    val tagsList: MutableLiveData<List<Tag>?>
        get() = tagsLiveData

    fun getSupervisors() {
        supervisorsUseCase.getSupervisorsNames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSupervisorsObserver())
    }

    fun getTags() {
        projectsUseCase.getTags()
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
                Log.d("filters", "$t")
                tagsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("filters", "${e.message}")
                tagsLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}