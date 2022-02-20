package com.example.yarmarka.ui.account.dialog_skills_choice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yarmarka.domain.model.Skill
import com.example.yarmarka.data.remote.client.ApiClient
import com.example.yarmarka.domain.usecase.SkillsUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DialogSkillsViewModel @Inject constructor(
    private val skillsUseCase: SkillsUseCase
): ViewModel() {

    private var skillsLiveData: MutableLiveData<List<Skill>?> = MutableLiveData()

    val skills: MutableLiveData<List<Skill>?>
        get() = skillsLiveData

    fun getSkills(searchPart: String) {
        skillsUseCase.searchSkills(searchPart)
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