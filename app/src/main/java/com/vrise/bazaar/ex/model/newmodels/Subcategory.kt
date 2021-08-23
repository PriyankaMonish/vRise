package com.vrise.bazaar.ex.model.newmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Subcategory(

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String? = null,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val img: String? = null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null
)