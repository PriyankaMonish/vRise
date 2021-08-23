package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class extrafields (
    @field:SerializedName("field")
    @field:JsonField(name = arrayOf("field"))
    val field: String? = null,

    @field:SerializedName("value")
    @field:JsonField(name = arrayOf("value"))
    val value: String? = null
)