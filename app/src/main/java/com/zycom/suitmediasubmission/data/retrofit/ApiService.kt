package com.zycom.suitmediasubmission.data.retrofit

import com.zycom.suitmediasubmission.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun fetchUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): UserResponse

}