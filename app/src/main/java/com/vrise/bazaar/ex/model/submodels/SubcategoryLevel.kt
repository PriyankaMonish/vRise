package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SubcategoryLevel {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("sub_category")
    @Expose
    var subCategory: String? = null

    @SerializedName("sub_levels")
    @Expose
    var subLevels: List<SubLevel>? = null
}