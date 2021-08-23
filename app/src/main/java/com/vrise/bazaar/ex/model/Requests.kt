package com.vrise.bazaar.ex.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
class Requests (

    @field:SerializedName("display")
    @field:JsonField(name = arrayOf("display"))
    val display: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: Int? = null

)
