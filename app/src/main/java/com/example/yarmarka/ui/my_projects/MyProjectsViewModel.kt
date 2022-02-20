package com.example.yarmarka.ui.my_projects

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.Participation
import com.example.yarmarka.data.remote.client.ApiClient
import com.example.yarmarka.domain.usecase.CandidatesUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyProjectsViewModel @Inject constructor(
    private val candidatesUseCase: CandidatesUseCase
) : ViewModel() {

    private var participationsLiveData: MutableLiveData<List<Participation>?> = MutableLiveData()

    val participationsList: MutableLiveData<List<Participation>?>
        get() = participationsLiveData

    fun getParticipationsList(page: Int = 0, token: String) {
        candidatesUseCase.getStudentParticipations(token = token, page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getParticipationsListObserver())
    }

    private fun getParticipationsListObserver(): Observer<List<Participation>> {
        return object : Observer<List<Participation>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Participation>) {
                Log.d("myThings", "$t")
                participationsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("myThings", "${e.message}")
                participationsLiveData.postValue(null)
            }

            override fun onComplete() {}
        }
    }
}