package com.example.testapp.data.webservice

import com.example.testapp.data.entity.GitHubUser
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {

    @GET("users")
    fun getAllAsync(): Deferred<List<GitHubUser>>
}