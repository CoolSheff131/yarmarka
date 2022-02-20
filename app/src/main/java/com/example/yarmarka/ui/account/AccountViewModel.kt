package com.example.yarmarka.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.Candidate
import com.example.yarmarka.domain.model.CandidateUpdate
import com.example.yarmarka.domain.model.ResponseStatus
import com.example.yarmarka.domain.model.Skill
import com.example.yarmarka.domain.usecase.CandidatesUseCase
import com.example.yarmarka.domain.usecase.SkillsUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val candidatesUseCase: CandidatesUseCase,
    private val skillsUseCase: SkillsUseCase
): ViewModel() {

    private var accountDataLiveData: MutableLiveData<Candidate?> = MutableLiveData()
    private var skillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()

    val accountData: MutableLiveData<Candidate?>
        get() = accountDataLiveData

    val skills: MutableLiveData<List<Skill>?>
        get() = skillsLiveData

    fun getAccountData(token: String, onNext: () -> Unit) {
        candidatesUseCase.getStudentById(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onNext)
            .subscribe(getAccountDataObserver())
    }

    fun getSkills(searchPart: String) {
        skillsUseCase.searchSkills(searchPart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSkillsObserver())
    }

    fun updateAccountData(token: String, candidateUpdate: CandidateUpdate, onNext: () -> Unit) {
        candidatesUseCase.updateStudentInfo(token, candidateUpdate)
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

    private fun updateAccountDataObserver(): Observer<ResponseStatus> {
        return object : Observer<ResponseStatus> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ResponseStatus) {
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