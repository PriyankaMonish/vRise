package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class PrdlistItem(

        @field:SerializedName("category_name")
        @field:JsonField(name = arrayOf("category_name"))
        val categoryName: String? = null,

        @field:SerializedName("img_link")
        @field:JsonField(name = arrayOf("img_link"))
        val imgLink: String? = null,

        @field:SerializedName("description")
        @field:JsonField(name = arrayOf("description"))
        val description: String? = null,

        @field:SerializedName("language")
        @field:JsonField(name = arrayOf("language"))
        val language: String? = null,

        @field:SerializedName("subscription")
        @field:JsonField(name = arrayOf("subscription"))
        val subscription: String? = null,

        @field:SerializedName("agent_price")
        @field:JsonField(name = arrayOf("agent_price"))
        val agentPrice: String? = null,

        @field:SerializedName("price")
        @field:JsonField(name = arrayOf("price"))
        val price: String? = null,

        @field:SerializedName("agent_status")
        @field:JsonField(name = arrayOf("agent_status"))
        val agentStatus: String? = null,

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("language_name")
        @field:JsonField(name = arrayOf("language_name"))
        val languageName: String? = null,

        @field:SerializedName("category")
        @field:JsonField(name = arrayOf("category"))
        val category: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        var status: String? = null
) {
    override fun toString(): String {
        return "$id"
    }
}