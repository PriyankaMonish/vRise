package com.vrise.bazaar.ex.model.mainmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class Filters (

    @field:SerializedName("subcategory")
    @field:JsonField(name = arrayOf("subcategory"))
    var subcategory: String? = null,

    @field:SerializedName("breed")
    @field:JsonField(name = arrayOf("breed"))
    var breed: String? = null,

    @field:SerializedName("brand_id")
    @field:JsonField(name = arrayOf("brand_id"))
    var brand_id: String? = null
)


