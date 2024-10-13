package com.itzik.samara.apps.fetchdemo.bl

import com.itzik.samara.apps.fetchdemo.bl.models.Item
import retrofit2.http.GET

interface FetchService {

    @GET("hiring.json")
    suspend fun getItems(): List<Item>

}