package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class SublistItem(

        @field:SerializedName("subscriptions")
        @field:JsonField(name = arrayOf("subscriptions"))
        val subscriptions: String? = null,

        @field:SerializedName("user_type")
        @field:JsonField(name = arrayOf("user_type"))
        val userType: String? = null,

        @field:SerializedName("code")
        @field:JsonField(name = arrayOf("code"))
        val code: String? = null,

        @field:SerializedName("route_id")
        @field:JsonField(name = arrayOf("route_id"))
        val routeId: String? = null,

        @field:SerializedName("img_link")
        @field:JsonField(name = arrayOf("img_link"))
        val imgLink: String? = null,

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("device_id")
        @field:JsonField(name = arrayOf("device_id"))
        val deviceIds: String? = null,

        @field:SerializedName("mobile")
        @field:JsonField(name = arrayOf("mobile"))
        val mobile: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        val status: String? = null
)