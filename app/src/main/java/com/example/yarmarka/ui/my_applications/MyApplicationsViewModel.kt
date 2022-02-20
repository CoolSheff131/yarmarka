package com.example.yarmarka.ui.my_applications

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.Participation
import com.example.yarmarka.domain.usecase.CandidatesUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyApplicationsViewModel @Inject constructor(
    private val candidatesUseCase: CandidatesUseCase
) : ViewModel() {

    private var applicationsLiveData: MutableLiveData<List<Participation>?> = MutableLiveData()

    val applicationsList: MutableLiveData<List<Participation>?>
        get() = applicationsLiveData

    fun getApplicationsList(page: Int = 0, token: String) {
        candidatesUseCase.getStudentApplications(token = token, page = page)
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