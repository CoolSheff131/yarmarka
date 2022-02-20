package com.example.yarmarka.di

import android.content.Context
import com.example.yarmarka.ui.account.AccountFragment
import com.example.yarmarka.ui.account.dialog_skills_choice.DialogSkills
import com.example.yarmarka.ui.application.ApplicationFragment
import com.example.yarmarka.ui.filters.FiltersFragment
import com.example.yarmarka.ui.main.MainFragment
import com.example.yarmarka.ui.my_applications.MyApplicationsFragment
import com.example.yarmarka.ui.my_projects.MyProjectsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
//    }

    fun inject(myProjectsFragment: MyProjectsFragment)
    fun inject(dialogSkills: DialogSkills)
    fun inject(accountFragment: AccountFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(myApplicationsFragment: MyApplicationsFragment)
    fun inject(applicationFragment: ApplicationFragment)
    fun inject(filtersFragment: FiltersFragment)
}