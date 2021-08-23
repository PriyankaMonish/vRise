package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

data class Taglist (
    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null,

    @field:SerializedName("message")
    @field:JsonField(name = arrayOf("message"))
    val message: String? = null,

    @field:SerializedName("display")
    @field:JsonField(name = arrayOf("display"))
    val display: String? = null,

    @field:SerializedName("data")
    @field:JsonField(name = arrayOf("data"))
    val data: Datas? = null
        )