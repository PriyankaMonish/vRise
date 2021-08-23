package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class CategoryDeliveryItem(

	@field:SerializedName("category_data")
	@field:JsonField(name = arrayOf("category_data"))
	val categoryData: CategoryData? = null,

	@field:SerializedName("total_itemprize")
	@field:JsonField(name = arrayOf("total_itemprize"))
	val total_itemprize: String? = null,

	@field:SerializedName("total_deliverycharge")
	@field:JsonField(name = arrayOf("total_deliverycharge"))
	val total_deliverycharge: String? = null,

	@field:SerializedName("total_product_commesion")
	@field:JsonField(name = arrayOf("total_product_commesion"))
	val totalProductCommesion: String? = null,

	@field:SerializedName("total_saleamount")
	@field:JsonField(name = arrayOf("total_saleamount"))
	val totalSaleamount: String? = null,

	@field:SerializedName("crt_list")
	@field:JsonField(name = arrayOf("crt_list"))
	val crtList: List<CrtListItem?>? = null,

	@field:SerializedName("category_commesion")
	@field:JsonField(name = arrayOf("category_commesion"))
	val categoryCommesion: String? = null,

	@field:SerializedName("categrory_grandtotal")
	@field:JsonField(name = arrayOf("categrory_grandtotal"))
	val categroryGrandtotal: String? = null,

	@field:SerializedName("balance_category_commesion")
	@field:JsonField(name = arrayOf("balance_category_commesion"))
	val balanceCategoryCommesion: String? = null
)