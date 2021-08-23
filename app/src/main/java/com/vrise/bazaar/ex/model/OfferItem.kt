package com.vrise.bazaar.ex.model


import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class OfferItem(
    
    @field:SerializedName("id")
    val id: String,
    
    @field:SerializedName("offer_caegory")
    val offerCaegory: String,
    
    @field:SerializedName("offer_caegory_name")
    val offerCaegoryName: String,
    
    @field:SerializedName("offer_code")
    val offerCode: String,
    
    @field:SerializedName("offer_description")
    val offerDescription: String,
    
    @field:SerializedName("offer_from_date")
    val offerFromDate: String,
    
    @field:SerializedName("offer_min_amount")
    val offerMinAmount: String,
    
    @field:SerializedName("offer_purchase_amount")
    val offerPurchaseAmount: String,
    
    @field:SerializedName("offer_purchase_number")
    val offerPurchaseNumber: String,
    
    @field:SerializedName("offer_purchase_type")
    val offerPurchaseType: String,
    
    @field:SerializedName("offer_seller")
    val offerSeller: String,
    
    @field:SerializedName("offer_shop")
    val offerShop: String,
    
    @field:SerializedName("offer_status")
    val offerStatus: String,
    
    @field:SerializedName("offer_title")
    val offerTitle: String,
    
    @field:SerializedName("offer_to_date")
    val offerToDate: String,
    
    @field:SerializedName("offer_type")
    val offerType: String,
    
    @field:SerializedName("offer_value")
    val offerValue: String,
    
    @field:SerializedName("offer_value_type")
    val offerValueType: String,
    
    @field:SerializedName("updated_date")
    val updatedDate: String
)