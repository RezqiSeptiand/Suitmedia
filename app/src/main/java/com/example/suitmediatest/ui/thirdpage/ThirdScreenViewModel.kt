package com.example.suitmediatest.ui.thirdpage

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.data.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class ThirdScreenViewModel(private val repository: Repository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    val users: Flow<PagingData<DataItem>> = Pager(
        config = PagingConfig(
            pageSize = 10, enablePlaceholders = false
        ),
        pagingSourceFactory = { repository.getUsers() }).flow.onStart { _isLoading.postValue(true) }
        .catch { e ->
            _isLoading.postValue(false)
            Log.e(ContentValues.TAG, "Error creating Pager", e)
        }.onEach { _isLoading.postValue(false) }.cachedIn(viewModelScope)
}
