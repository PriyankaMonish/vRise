package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class ShopSubCategoriesItem {
    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null

    @field:SerializedName("select")
    @field:JsonField(name = arrayOf("select"))
    var select: Boolean = false

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local") as Array<String>)
    val name_local: String? = null

    @field:SerializedName("category")
    @field:JsonField(name = arrayOf("category"))
    val category: String? = null

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val img_link: String? = null
}
