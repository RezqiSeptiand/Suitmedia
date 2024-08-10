package com.example.suitmediatest.data.di

import android.content.Context
import com.example.suitmediatest.data.Repository
import com.example.suitmediatest.data.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}