package com.example.yarmarka.network.services

import com.example.yarmarka.network.api.CandidatesApi
import com.example.yarmarka.network.api.ProjectsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceProjects {

    companion object {

        private var retrofit: Retrofit? = null

        private fun getInstance(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("тут название апи")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit as Retrofit
            } else retrofit as Retrofit
        }

        @Synchronized
        fun buildService(): ProjectsApi {
            return getInstance().create(ProjectsApi::class.java)
        }
    }
}