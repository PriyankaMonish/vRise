package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import java.io.Serializable

@JsonObject
data class RootlistItem(

        @field:SerializedName("area")
        @field:JsonField(name = arrayOf("area"))
        val area: String? = null,

        @field:SerializedName("agent_id")
        @field:JsonField(name = arrayOf("agent_id"))
        val agentId: String? = null,

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("sub_count")
        @field:JsonField(name = arrayOf("sub_count"))
        val subCount: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        val status: String? = null,

        @field:SerializedName("request_count")
        @field:JsonField(name = arrayOf("request_count"))
        val request_count: String? = null
) : Serializable