package com.example.yarmarka.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.model.CandidateUpdate
import com.example.yarmarka.model.ResponseBody
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

    fun getAccountData(token: String) {
        api.getStudentById(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getAccountDataObserver())
    }

    fun updateAccountData(token: String, candidateUpdate: CandidateUpdate) {
        api.updateStudentInfo(token, candidateUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(updateAccountDataObserver())
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
                Log.d("testing", "error account + , ${e.message}")
                accountDataLiveData.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }

    private fun updateAccountDataObserver(): Observer<ResponseBody> {
        return object : Observer<ResponseBody> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ResponseBody) {
                Log.d("testing", "response - $t")
            }

            override fun onError(e: Throwable) {
                Log.d("testing", "error - $e")
            }

            override fun onComplete() {

            }
        }
    }
}