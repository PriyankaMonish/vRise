package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class Valuess (

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String?=null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name:String? = null,


    var isSelected:Boolean = false
)
