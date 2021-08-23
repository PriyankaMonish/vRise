package com.vrise.bazaar.newpages.top

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

data class TopModel(

    @field:SerializedName("prefer_language")
    @field:JsonField(name = arrayOf("prefer_language"))
    var pre_lan: String = "1",

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    var uid: Int = 0,

    @field:SerializedName("notification_count")
    @field:JsonField(name = arrayOf("notification_count"))
    var noticicationCount: Int?,

    @field:SerializedName("favourites_count")
    @field:JsonField(name = arrayOf("favourites_count"))
    var favouritesCount: Int?,

    @field:SerializedName("cart_count")
    @field:JsonField(name = arrayOf("cart_count"))
    var cartCount: Int?,

    @field:SerializedName("location")
    @field:JsonField(name = arrayOf("location"))
    var location: String?,

    @field:SerializedName("latitude")
    @field:JsonField(name = arrayOf("latitude"))
    var lat: String?,

    @field:SerializedName("longitude")
    @field:JsonField(name = arrayOf("longitude"))
    var lng: String?,

    @field:SerializedName("book_count")
    @field:JsonField(name = arrayOf("book_count"))
    var bookCount: Int?
)