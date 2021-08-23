package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

data class Datas(
    @field:SerializedName("shops")
    @field:JsonField(name = arrayOf("shops"))
    val shops: ArrayList<Shoppss>? = null,



)
