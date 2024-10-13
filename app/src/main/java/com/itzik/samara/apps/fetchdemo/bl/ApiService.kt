package com.itzik.samara.apps.fetchdemo.bl

object ApiService {

    val fetchService: FetchService by lazy {
        RetrofitManager.retrofit.create(FetchService::class.java)
    }
}