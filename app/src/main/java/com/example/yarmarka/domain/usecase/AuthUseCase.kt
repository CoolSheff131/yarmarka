package com.example.yarmarka.domain.usecase

import com.example.yarmarka.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl
) {

    fun logout(token: String) {
        authRepositoryImpl.logout(token = token)
    }
}