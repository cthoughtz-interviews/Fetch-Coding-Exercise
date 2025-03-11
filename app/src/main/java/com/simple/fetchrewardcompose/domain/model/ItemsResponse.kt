package com.simple.fetchrewardcompose.domain.model


import com.google.gson.annotations.SerializedName

class ItemsResponse : ArrayList<ItemsResponse.ItemsResponseItem>(){
    data class ItemsResponseItem(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("listId")
        val listId: Int?,
        @SerializedName("name")
        val name: String?
    )
}