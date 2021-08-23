package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SizeStockPrice {
    @SerializedName("size_id")
    @Expose
    var sizeId: String? = null

    @SerializedName("size_name")
    @Expose
    var sizeName: String? = null

    @SerializedName("size_price")
    @Expose
    var sizePrice: Float? = null

    @SerializedName("stock")
    @Expose
    var stock: String? = null

    @SerializedName("pickup_offer_price")
    @Expose
    var pickupOfferPrice: Int? = null

    @SerializedName("pickup_discount")
    @Expose
    var pickupDiscount: String? = null

    var selectedPosition: Int = 0
}