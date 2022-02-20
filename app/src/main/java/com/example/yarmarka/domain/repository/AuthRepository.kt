package com.example.yarmarka.domain.repository

interface AuthRepository {

    fun logout(token: String)
}