package com.example.testapp.presentation

import com.example.testapp.data.webservice.Api

object Common {
    private val BASE_URL = "https://run.mocky.io/v3/"
    val api: Api
        get() = RetrofitClient.getClient(BASE_URL).create(Api::class.java)
}