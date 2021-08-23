package com.vrise.bazaar.ex.model.submodels


import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class BillCategory(

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String,

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String

)