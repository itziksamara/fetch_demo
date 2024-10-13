package com.itzik.samara.apps.fetchdemo.bl.models

import com.google.gson.annotations.SerializedName

data class Item(
    @JvmField
    @SerializedName("id")
    val id: Int? = null,
    @JvmField
    @SerializedName("listId")
    val listId: Int,
    @JvmField
    @SerializedName("name")
    val name: String? = null
)
