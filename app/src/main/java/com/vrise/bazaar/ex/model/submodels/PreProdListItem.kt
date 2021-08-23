package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class PreProdListItem(

    @field:SerializedName("subcategory_local")
    @field:JsonField(name = arrayOf("subcategory_local"))
    val subcategoryLocal: String? = null,

    @field:SerializedName("subcategory")
    @field:JsonField(name = arrayOf("subcategory"))
    val subcategory: String? = null,

    @field:SerializedName("category_local")
    @field:JsonField(name = arrayOf("category_local"))
    val categoryLocal: String? = null,

    @field:SerializedName("category")
    @field:JsonField(name = arrayOf("category"))
    val category: String? = null,


    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String? = null,

    @field:SerializedName("size_type")
    @field:JsonField(name = arrayOf("size_type"))
    val sizeType: String? = null,

    @field:SerializedName("available_sizes")
    @field:JsonField(name = arrayOf("available_sizes"))
    val availableSizes: String? = null,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String? = null,

    @field:SerializedName("description")
    @field:JsonField(name = arrayOf("description"))
    val description: String? = null,

    @field:SerializedName("prefer_status")
    @field:JsonField(name = arrayOf("prefer_status"))
    var preferStatus: Int = 0,

    @field:SerializedName("stock_type")
    @field:JsonField(name = arrayOf("stock_type"))
    val stockType: String? = null,

    @field:SerializedName("subcategory_id")
    @field:JsonField(name = arrayOf("subcategory_id"))
    val subcategoryId: String? = null,

    @field:SerializedName("category_id")
    @field:JsonField(name = arrayOf("category_id"))
    val categoryId: String? = null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

    @field:SerializedName("commission")
    @field:JsonField(name = arrayOf("commission"))
    val commission: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("commission_type")
    @field:JsonField(name = arrayOf("commission_type"))
    val commissionType: String? = null,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null
)