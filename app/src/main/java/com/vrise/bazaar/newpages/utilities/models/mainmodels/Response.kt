package com.vrise.bazaar.newpages.utilities.models.mainmodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.vrise.bazaar.newpages.utilities.models.submodels.Data

@JsonObject
data class Response(

        @field:SerializedName("data")
        @field:JsonField(name = arrayOf("data"))
        val data: Data? = null,

        @field:SerializedName("display")
        @field:JsonField(name = arrayOf("display"))
        val display: String? = null,

        @field:SerializedName("message")
        @field:JsonField(name = arrayOf("message"))
        val message: String? = null,

        @field:SerializedName("version")
        @field:JsonField(name = arrayOf("version"))
        val version: String? = null,

        @field:SerializedName("success")
        @field:JsonField(name = arrayOf("success"))
        val success: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        val status: Int? = null
)