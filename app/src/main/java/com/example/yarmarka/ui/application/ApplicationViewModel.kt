package com.example.yarmarka.ui.application

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Candidate
import com.example.yarmarka.model.ParticipationCreate
import com.example.yarmarka.model.ResponseBody
import com.example.yarmarka.model.Skill
import com.example.yarmarka.network.services.ApiServiceCandidates
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApplicationViewModel: ViewModel() {

    private val candidateApi = ApiServiceCandidates.buildService()

    private var participationCreateLiveData: MutableLiveData<ResponseBody> = MutableLiveData()
    private var studentSkillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()
    private var allSkillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()

    val accountData: MutableLiveData<ResponseBody>
        get() = participationCreateLiveData

    val studentSkills: MutableLiveData<List<Skill>?>
        get() = studentSkillsLiveData

    val allSkills: MutableLiveData<List<Skill>?>
        get() = allSkillsLiveData

    fun sendParticipationRequest(projectId: Int, token: String, participationCreate: ParticipationCreate, onNext: () -> Unit) {
        candidateApi.createProjectRequest(projectId, token, participationCreate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onNext)
            .subscribe(sendParticipationRequestObserver())
    }

    fun getStudentSkills(token: String) {
        candidateApi.getStudentSkills(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getStudentSkillsObserver())
    }

    fun getAllSkills() {
        candidateApi.getSkills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getAllSkillsObserver())
    }

    private fun sendParticipationRequestObserver(): Observer<ResponseBody> {
        return object : Observer<ResponseBody> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ResponseBody) {
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