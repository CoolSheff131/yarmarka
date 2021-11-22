package com.example.yarmarka.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.network.services.ApiServiceCandidates
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AccountViewModel: ViewModel() {

    private val api = ApiServiceCandidates.buildService()

    private var accountDataLiveData: MutableLiveData<Candidate?> = MutableLiveData()

    val accountData: MutableLiveData<Candidate?>
        get() = accountDataLiveData


    fun getAccountData() {
        api.getStudentById("de61ecc80827a86a3fd41c53743b3006295161331c587585ec135e209f91008b")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getAccountDataObserver())
    }

    private fun getAccountDataObserver(): Observer<Candidate> {
        return object : Observer<Candidate> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Candidate) {
                Log.d("testing", "===$t")
                accountDataLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("testing", "error + , ${e.message}")
                accountDataLiveData.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }
}