package com.example.yarmarka.ui.account.dialog_skills_choice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.model.Skill
import com.example.yarmarka.network.services.ApiServiceCandidates
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DialogSkillsViewModel(): ViewModel() {

    private val candidateApi = ApiServiceCandidates.buildService()

    private var skillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()

    val skills: MutableLiveData<List<Skill>?>
        get() = skillsLiveData

    fun getSkills(searchPart: String) {
        candidateApi.searchSkills(searchPart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSkillsObserver())
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