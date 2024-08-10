package com.example.suitmediatest.data

import androidx.paging.PagingSource
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.api.ApiService
import com.example.suitmediatest.data.paging.UserPagingSource

class Repository(private val apiService: ApiService) {
    fun getUsers(): PagingSource<Int, DataItem> {
        return UserPagingSource(apiService)
    }
}