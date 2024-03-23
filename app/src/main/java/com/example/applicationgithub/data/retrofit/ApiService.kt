package com.example.applicationgithub.data.retrofit

import com.example.applicationgithub.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getGithub(
        @Path("users") users: String
    ): Call<GithubResponse>
}
