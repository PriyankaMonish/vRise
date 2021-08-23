package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.DeliveryboyData
import com.vrise.bazaar.ex.model.TrackData

class CategoryTree (

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null,

    @field:SerializedName("image_link")
    @field:JsonField(name = arrayOf("image_link"))
    val image_link: String? = null,

    @field:SerializedName("main_subcategories")
    @field:JsonField(name = arrayOf("main_subcategories"))
    val main_subcategories: List<MainSubCategories?>? = null
)