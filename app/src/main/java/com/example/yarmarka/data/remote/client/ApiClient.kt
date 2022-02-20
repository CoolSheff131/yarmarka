package com.example.yarmarka.data.remote.client

import com.example.yarmarka.data.remote.api.*
import com.example.yarmarka.domain.model.Skill
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
class ApiClient {
    companion object {

        private var retrofit: Retrofit? = null

        private fun getInstance(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://projects.tw1.ru/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit as Retrofit
            } else retrofit as Retrofit
        }

        @Synchronized
        fun buildServiceAuth(): AuthApi {
            return getInstance().create(AuthApi::class.java)
        }

        @Synchronized
        fun buildServiceCandidates(): CandidatesApi {
            return getInstance().create(CandidatesApi::class.java)
        }

        @Synchronized
        fun buildServiceProjects(): ProjectsApi {
            return getInstance().create(ProjectsApi::class.java)
        }

        @Synchronized
        fun buildServiceSupervisors(): SupervisorsApi {
            return getInstance().create(SupervisorsApi::class.java)
        }

        @Synchronized
        fun buildSkillsSupervisors(): SkillsApi {
            return getInstance().create(SkillsApi::class.java)
        }
    }
}