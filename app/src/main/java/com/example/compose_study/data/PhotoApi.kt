package com.example.compose_study.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoApi {
    @GET("v2/list")
    suspend fun getPhotoList(@Query("page") page: Int, @Query("limit") limit: Int): List<PhotoResponse>

    @GET("id/{id}/info")
    suspend fun getPhoto(@Path("id") id: Int): PhotoResponse
}
