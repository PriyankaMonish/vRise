package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class shippingmethods (

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("method")
    @field:JsonField(name = arrayOf("method"))
    val method: String? = null,

    @field:SerializedName("description")
    @field:JsonField(name = arrayOf("description"))
    val description: String? = null,

 @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val img_link: String? = null
)