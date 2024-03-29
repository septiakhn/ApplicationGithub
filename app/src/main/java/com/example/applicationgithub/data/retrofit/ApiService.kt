package com.example.applicationgithub.data.retrofit

import com.example.applicationgithub.data.response.DetailUserResponse
import com.example.applicationgithub.data.response.GithubResponse
import com.example.applicationgithub.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getGithub(
        @Query("q") users: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getDetailFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getDetailFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}
