package com.example.yarmarka.di

import com.example.yarmarka.data.remote.api.*
import com.example.yarmarka.data.remote.client.ApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideAuthClient(): AuthApi {
        return ApiClient.buildServiceAuth()
    }

    @Provides
    @Singleton
    fun provideCandidatesClient(): CandidatesApi {
        return ApiClient.buildServiceCandidates()
    }

    @Provides
    @Singleton
    fun provideProjectsClient(): ProjectsApi {
        return ApiClient.buildServiceProjects()
    }

    @Provides
    @Singleton
    fun provideSupervisorsClient(): SupervisorsApi {
        return ApiClient.buildServiceSupervisors()
    }

    @Provides
    @Singleton
    fun provideSkillsClient(): SkillsApi {
        return ApiClient.buildSkillsSupervisors()
    }
}