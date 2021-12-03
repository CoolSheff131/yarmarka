package com.example.yarmarka.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.model.CandidateUpdate
import com.example.yarmarka.model.ResponseBody
import com.example.yarmarka.model.Skill
import com.example.yarmarka.network.services.ApiServiceCandidates
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AccountViewModel: ViewModel() {

    private val candidateApi = ApiServiceCandidates.buildService()

    private var accountDataLiveData: MutableLiveData<Candidate?> = MutableLiveData()
    private var skillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()

    val accountData: MutableLiveData<Candidate?>
        get() = accountDataLiveData

    val skills: MutableLiveData<List<Skill>?>
        get() = skillsLiveData

    fun getAccountData(token: String, onNext: () -> Unit) {
        candidateApi.getStudentById(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onNext)
            .subscribe(getAccountDataObserver())
    }

    fun getSkills(searchPart: String) {
        candidateApi.searchSkills(searchPart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSkillsObserver())
    }

    fun updateAccountData(token: String, candidateUpdate: CandidateUpdate, onNext: () -> Unit) {
        candidateApi.updateStudentInfo(token, candidateUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onNext)
            .subscribe(updateAccountDataObserver())
    }

    private fun getAccountDataObserver(): Observer<Candidate> {
        return object : Observer<Candidate> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Candidate) {
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

    private fun getSkillsObserver(): Observer<List<Skill>> {
        return object : Observer<List<Skill>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Skill>) {
                skills.postValue(t)
            }

            override fun onError(e: Throwable) {
                skills.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }
}