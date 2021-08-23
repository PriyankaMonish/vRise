package com.vrise.bazaar.ex.model.mainmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
class CartIDList(
    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String = "0"
) : Serializable