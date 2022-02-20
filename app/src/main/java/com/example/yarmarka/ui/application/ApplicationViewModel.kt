package com.example.yarmarka.ui.application

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.ParticipationCreate
import com.example.yarmarka.domain.model.ResponseStatus
import com.example.yarmarka.domain.model.Skill
import com.example.yarmarka.data.remote.client.ApiClient
import com.example.yarmarka.domain.usecase.CandidatesUseCase
import com.example.yarmarka.domain.usecase.SkillsUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApplicationViewModel @Inject constructor(
    private val candidatesUseCase: CandidatesUseCase,
    private val skillsUseCase: SkillsUseCase
) : ViewModel() {

    private var participationCreateLiveData: MutableLiveData<ResponseStatus> = MutableLiveData()
    private var studentSkillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()
    private var allSkillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()

    val studentSkills: MutableLiveData<List<Skill>?>
        get() = studentSkillsLiveData

    val allSkills: MutableLiveData<List<Skill>?>
        get() = allSkillsLiveData

    fun sendParticipationRequest(
        projectId: Int,
        token: String,
        participationCreate: ParticipationCreate
    ) {
        candidatesUseCase.createProjectRequest(
            token = token,
            id = projectId,
            participate = participationCreate
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(sendParticipationRequestObserver())
    }

    fun getStudentSkills(token: String) {
        candidatesUseCase.getStudentSkills(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getStudentSkillsObserver())
    }

    fun getAllSkills() {
        skillsUseCase.getSkills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getAllSkillsObserver())
    }

    private fun sendParticipationRequestObserver(): Observer<ResponseStatus> {
        return object : Observer<ResponseStatus> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ResponseStatus) {
                Log.d("application_skills", "response - $t")
            }

            override fun onError(e: Throwable) {
                Log.d("application_skills", "error - $e")
            }

            override fun onComplete() {

            }
        }
    }

    private fun getStudentSkillsObserver(): Observer<List<Skill>> {
        return object : Observer<List<Skill>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Skill>) {
                Log.d("application_skills", "response - $t")
                studentSkillsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("application_skills", "error - $e")
                studentSkillsLiveData.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }

    private fun getAllSkillsObserver(): Observer<List<Skill>> {
        return object : Observer<List<Skill>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Skill>) {
                Log.d("application_skills", "response - $t")
                allSkillsLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("application_skills", "error - $e")
                allSkillsLiveData.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }
}