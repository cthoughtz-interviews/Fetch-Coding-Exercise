package com.simple.fetchrewardcompose.domain.repository

import com.simple.fetchrewardcompose.data.network.ApiService
import com.simple.fetchrewardcompose.data.network.safeApiCall
import com.simple.fetchrewardcompose.data.network.toResult
import com.simple.fetchrewardcompose.domain.model.ItemsResponse
import javax.inject.Inject
import com.simple.fetchrewardcompose.FetchResult

interface ItemsRepository {
    suspend fun getItems(): FetchResult<ItemsResponse>
}

class ItemsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ItemsRepository {

    override suspend fun getItems(): FetchResult<ItemsResponse> {
        return safeApiCall { apiService.items() }.toResult()
    }
}