package com.example.yarmarka.network.services

import com.example.yarmarka.network.api.CandidatesApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceCandidates {

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
        fun buildService(): CandidatesApi {
            return getInstance().create(CandidatesApi::class.java)
        }
    }
}