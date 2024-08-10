package com.example.suitmediatest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmediatest.data.api.ApiService
import com.example.suitmediatest.data.response.DataItem

class UserPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getUsers(currentPage, params.loadSize)
            val products = response.data?.filterNotNull() ?: emptyList()

            LoadResult.Page(
                data = products,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (products.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
