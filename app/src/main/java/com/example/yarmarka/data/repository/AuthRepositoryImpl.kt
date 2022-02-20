package com.example.yarmarka.data.repository

import com.example.yarmarka.data.remote.api.AuthApi
import com.example.yarmarka.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
): AuthRepository {

    override fun logout(token: String) {
        authApi.logout(token = token)
    }
}