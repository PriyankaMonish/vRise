package com.vrise.bazaar.newpages.utilities.models.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class SellersItem (

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    var imgLink: String? = null,

    @field:SerializedName("store_name")
    @field:JsonField(name = arrayOf("store_name"))
    var name: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    var id: String? = null

)
