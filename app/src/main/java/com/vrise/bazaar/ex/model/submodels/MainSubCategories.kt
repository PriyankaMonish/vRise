package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class MainSubCategories (
    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    var id: String? = null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    var name: String? = null,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null,

    @field:SerializedName("category")
    @field:JsonField(name = arrayOf("category"))
    val category: String? = null
        )