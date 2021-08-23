package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class Colorlist
    (

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null,

    @field:SerializedName("color")
    @field:JsonField(name = arrayOf("color"))
    val color: String? = null

)
