package com.simple.fetchrewardcompose.data.network

import com.simple.fetchrewardcompose.domain.model.ItemsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/hiring.json")
    suspend fun items(): ItemsResponse
}