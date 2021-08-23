package com.vrise.bazaar.ex.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class TrackData(

	@field:SerializedName("seller")
	@field:JsonField(name = arrayOf("seller"))
	val seller: String? = null,

	@field:SerializedName("seller_data")
	@field:JsonField(name = arrayOf("seller_data"))
	val sellerData: SellerData? = null,

	@field:SerializedName("eta")
	@field:JsonField(name = arrayOf("eta"))
	val eta: String? = null,

	@field:SerializedName("distance_sc")
	@field:JsonField(name = arrayOf("distance_sc"))
	val distance_sc: String? = null,

	@field:SerializedName("distance_ds")
	@field:JsonField(name = arrayOf("distance_ds"))
	val distance_ds: String? = null,

	@field:SerializedName("user_address")
	@field:JsonField(name = arrayOf("user_address"))
	val userAddress: UserAddress? = null,

	@field:SerializedName("deliveryboy")
	@field:JsonField(name = arrayOf("deliveryboy"))
	val deliveryboy: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("user_data")
	@field:JsonField(name = arrayOf("user_data"))
	val userData: UserData? = null,

	@field:SerializedName("delivery_status")
	@field:JsonField(name = arrayOf("delivery_status"))
	val deliveryStatus: String? = null,

	@field:SerializedName("seller_status")
	@field:JsonField(name = arrayOf("seller_status"))
	val sellerStatus: String? = null,

	@field:SerializedName("deliveryboy_status")
	@field:JsonField(name = arrayOf("deliveryboy_status"))
	val deliveryboy_status: String? = null,

	@field:SerializedName("deliveryboy_data")
	@field:JsonField(name = arrayOf("deliveryboy_data"))
	val deliveryboyData: DeliveryboyData? = null
)