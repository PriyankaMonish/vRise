package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class OrdProductsItem(

	@field:SerializedName("name_local")
	@field:JsonField(name = arrayOf("name_local"))
	val nameLocal: String? = null,

	@field:SerializedName("size")
	@field:JsonField(name = arrayOf("size"))
	val size: String? = null,

	@field:SerializedName("product_size_id")
	@field:JsonField(name = arrayOf("product_size_id"))
	val product_size_id: String? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("price")
	@field:JsonField(name = arrayOf("price"))
	val price: Float? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("ecom_product_id")
	@field:JsonField(name = arrayOf("ecom_product_id"))
	val ecomProductId: String? = null,

	@field:SerializedName("category")
	@field:JsonField(name = arrayOf("category"))
	val category: String? = null,

	@field:SerializedName("product_total")
	@field:JsonField(name = arrayOf("product_total"))
	val productTotal: String? = null,

	@field:SerializedName("seller_product_id")
	@field:JsonField(name = arrayOf("seller_product_id"))
	val sellerProductId: String? = null,

	@field:SerializedName("sale_id")
	@field:JsonField(name = arrayOf("sale_id"))
	val sale_id: String? = null,

	@field:SerializedName("enterdQuantity")
	@field:JsonField(name = arrayOf("enterdQuantity"))
	var enterdQuantity: Float? = null,

	@field:SerializedName("product_quantity")
	@field:JsonField(name = arrayOf("product_quantity"))
	var productQuantity: String? = null,

	@field:SerializedName("returnable")
	@field:JsonField(name = arrayOf("returnable"))
	val returnable: String? = null,

	@field:SerializedName("returned")
	@field:JsonField(name = arrayOf("returned"))
	val returned: String? = null,

	@field:SerializedName("return_status")
	@field:JsonField(name = arrayOf("return_status"))
	val return_status: String? = null,

	var isSelected:Boolean=false


)