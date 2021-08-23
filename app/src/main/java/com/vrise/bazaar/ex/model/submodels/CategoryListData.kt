package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CategoryListData {
    @SerializedName("main_subcategory")
    @Expose
    var mainSubcategory: MainSubcategory? = null

    @SerializedName("subcategory_levels")
    @Expose
    var subcategoryLevels: List<SubcategoryLevel>? = null
}