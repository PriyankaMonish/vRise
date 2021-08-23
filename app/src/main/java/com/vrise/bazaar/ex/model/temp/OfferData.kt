package com.vrise.bazaar.ex.model.temp

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class OfferData(

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("offer_title")
	@field:JsonField(name = arrayOf("offer_title"))
	val offerTitle: String? = null,

	@field:SerializedName("offer_description")
	@field:JsonField(name = arrayOf("offer_description"))
	val offerDescription: String? = null,

	@field:SerializedName("offer_caegory")
	@field:JsonField(name = arrayOf("offer_caegory"))
	val offerCaegory: String? = null,

	@field:SerializedName("offer_seller")
	@field:JsonField(name = arrayOf("offer_seller"))
	val offerSeller: String? = null,

	@field:SerializedName("offer_purchase_type")
	@field:JsonField(name = arrayOf("offer_purchase_type"))
	val offerPurchaseType: String? = null,

	@field:SerializedName("offer_purchase_number")
	@field:JsonField(name = arrayOf("offer_purchase_number"))
	val offerPurchaseNumber: String? = null,

	@field:SerializedName("offer_purchase_amount")
	@field:JsonField(name = arrayOf("offer_purchase_amount"))
	val offerPurchaseAmount: String? = null,

	@field:SerializedName("offer_value_type")
	@field:JsonField(name = arrayOf("offer_value_type"))
	val offerValueType: String? = null,

	@field:SerializedName("offer_value")
	@field:JsonField(name = arrayOf("offer_value"))
	val offerValue: String? = null,

	@field:SerializedName("offer_type")
	@field:JsonField(name = arrayOf("offer_type"))
	val offerType: String? = null,

	@field:SerializedName("offer_code")
	@field:JsonField(name = arrayOf("offer_code"))
	val offerCode: String? = null,

	@field:SerializedName("offer_min_amount")
	@field:JsonField(name = arrayOf("offer_min_amount"))
	val offerMinAmount: String? = null,

	@field:SerializedName("offer_from_date")
	@field:JsonField(name = arrayOf("offer_from_date"))
	val offerFromDate: String? = null,

	@field:SerializedName("offer_to_date")
	@field:JsonField(name = arrayOf("offer_to_date"))
	val offerToDate: String? = null,

	@field:SerializedName("offer_delivery")
	@field:JsonField(name = arrayOf("offer_delivery"))
	val offerDelivery: String? = null,

	@field:SerializedName("offer_status")
	@field:JsonField(name = arrayOf("offer_status"))
	val offerStatus: String? = null,

	@field:SerializedName("updated_date")
	@field:JsonField(name = arrayOf("updated_date"))
	val updatedDate: String? = null
)