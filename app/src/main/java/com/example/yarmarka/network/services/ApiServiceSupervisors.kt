package com.example.yarmarka.network.services

import com.example.yarmarka.network.api.CandidatesApi
import com.example.yarmarka.network.api.ProjectsApi
import com.example.yarmarka.network.api.SupervisorsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceSupervisors {

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
        fun buildService(): SupervisorsApi {
            return getInstance().create(SupervisorsApi::class.java)
        }
    }
}