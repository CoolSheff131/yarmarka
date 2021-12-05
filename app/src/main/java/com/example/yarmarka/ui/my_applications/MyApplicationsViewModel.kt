package com.example.yarmarka.ui.my_applications

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Participation
import com.example.yarmarka.network.services.ApiServiceCandidates
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MyApplicationsViewModel : ViewModel() {

    private val api = ApiServiceCandidates.buildService()

    private var applicationsLiveData: MutableLiveData<List<Participation>?> = MutableLiveData()

    val applicationsList: MutableLiveData<List<Participation>?>
        get() = applicationsLiveData

    fun getApplicationsList(page: Int = 0, token: String) {
        api.getStudentApplications(page, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getApplicationsListObserver())
    }

    private fun getApplicationsListObserver(): Observer<List<Participation>> {
        return object : Observer<List<Participation>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Participation>) {
                Log.d("myThings", "$t")
                applicationsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("myThings", "${e.message}")
                applicationsLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}