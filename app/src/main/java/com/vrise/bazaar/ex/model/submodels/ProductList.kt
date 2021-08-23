package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ProductList {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("name_local")
    @Expose
    var nameLocal: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("subcategory_id")
    @Expose
    var subcategoryId: String? = null

    @SerializedName("brand_id")
    @Expose
    var brandId: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("ecom_product_id")
    @Expose
    var ecomProductId: String? = null

    @SerializedName("seller_id")
    @Expose
    var sellerId: String? = null

    @SerializedName("product_code")
    @Expose
    var productCode: String? = null

    @SerializedName("product_status")
    @Expose
    var productStatus: String? = null

    @SerializedName("delivery_slots")
    @Expose
    var deliverySlots: String? = null

    @SerializedName("spot_delivery")
    @Expose
    var spotDelivery: Int? = null

    @SerializedName("slot_delivery")
    @Expose
    var slotDelivery: String? = null

    @SerializedName("commission")
    @Expose
    var commission: String? = null

    @SerializedName("commission_type")
    @Expose
    var commissionType: String? = null

    @SerializedName("delivery_note")
    @Expose
    var deliveryNote: String? = null

    @SerializedName("added_date")
    @Expose
    var addedDate: String? = null

    @SerializedName("shop")
    @Expose
    var shop: String? = null

    @SerializedName("recommend")
    @Expose
    var recommend: String? = null

    @SerializedName("sort_order")
    @Expose
    var sortOrder: String? = null

    @SerializedName("publish_status")
    @Expose
    var publishStatus: String? = null

    @SerializedName("veg_non")
    @Expose
    var vegNon: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("breed")
    @Expose
    var breed: String? = null

    @SerializedName("age")
    @Expose
    var age: String? = null

    @SerializedName("quality")
    @Expose
    var quality: String? = null

    @SerializedName("color")
    @Expose
    var color: String? = null

    @SerializedName("weight")
    @Expose
    var weight: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("size_type")
    @Expose
    var sizeType: String? = null

    @SerializedName("available_sizes")
    @Expose
    var availableSizes: String? = null

    @SerializedName("stock_type")
    @Expose
    var stockType: String? = null

    @SerializedName("sprdtid")
    @Expose
    var sprdtid: String? = null

    @SerializedName("store_name")
    @Expose
    var storeName: String? = null

    @SerializedName("spot_ids")
    @Expose
    var spotIds: String? = null

    @SerializedName("slot_ids")
    @Expose
    var slotIds: String? = null

    @SerializedName("pickup")
    @Expose
    var pickup: String? = null

    @SerializedName("pickup_option")
    @Expose
    var pickupOption: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("category_local")
    @Expose
    var categoryLocal: String? = null

    @SerializedName("subcategory")
    @Expose
    var subcategory: String? = null

    @SerializedName("subcategory_local")
    @Expose
    var subcategoryLocal: String? = null

    @SerializedName("daily_product")
    @Expose
    var dailyProduct: String? = null

    @SerializedName("spot_status")
    @Expose
    var spotStatus: String? = null

    @SerializedName("slot_status")
    @Expose
    var slotStatus: String? = null

    @SerializedName("stock")
    @Expose
    var stock: String? = null

    @SerializedName("pickup_offer_status")
    @Expose
    var pickupOfferStatus: String? = null

    @SerializedName("brand")
    @Expose
    var brand: String? = null

    @SerializedName("img_link")
    @Expose
    var imgLink: String? = null

    @SerializedName("fav_status")
    @Expose
    var favStatus: Int? = null

    @SerializedName("size_stock_price")
    @Expose
    var sizeStockPrice: List<SizeStockPrice>? = null

    @SerializedName("delivery_times")
    @Expose
    var deliveryTimes: List<DeliveryTime>? = null

    @SerializedName("next_available")
    @Expose
    var nextAvailable: String? = null



    @SerializedName("available_status")
    @Expose
    var availableStatus: String? = null

    @SerializedName("rating")
    @Expose
    var rating: Int? = null


    @SerializedName("shop_open_status")
    @Expose
    var shop_open_status: String? = null

    @SerializedName("shop_next_open")
    @Expose
    var shop_next_open: String? = null
}