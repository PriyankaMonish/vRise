package com.vrise.bazaar.ex.model.temp

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class BooklistItem(

	@field:SerializedName("name_local")
	@field:JsonField(name = arrayOf("name_local"))
	val nameLocal: String? = null,

	@field:SerializedName("catname")
	@field:JsonField(name = arrayOf("catname"))
	val catname: String? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("booking_date")
	@field:JsonField(name = arrayOf("booking_date"))
	val bookingDate: String? = null,

	@field:SerializedName("ecom_product_id")
	@field:JsonField(name = arrayOf("ecom_product_id"))
	val ecomProductId: String? = null,

	@field:SerializedName("sale_price")
	@field:JsonField(name = arrayOf("sale_price"))
	val salePrice: Double? = null,

	@field:SerializedName("seller_product_id")
	@field:JsonField(name = arrayOf("seller_product_id"))
	val sellerProductId: String? = null,

	@field:SerializedName("product_quantity")
	@field:JsonField(name = arrayOf("product_quantity"))
	val productQuantity: String? = null,

	@field:SerializedName("subcategory_id")
	@field:JsonField(name = arrayOf("subcategory_id"))
	val subcategoryId: String? = null,

	@field:SerializedName("category_id")
	@field:JsonField(name = arrayOf("category_id"))
	val categoryId: String? = null,

	@field:SerializedName("user_id")
	@field:JsonField(name = arrayOf("user_id"))
	val userId: String? = null,

	@field:SerializedName("price")
	@field:JsonField(name = arrayOf("price"))
	val price: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("store_name")
	@field:JsonField(name = arrayOf("store_name"))
	val storeName: String? = null,

	@field:SerializedName("commission")
	@field:JsonField(name = arrayOf("commission"))
	val commission: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("updated_date")
	@field:JsonField(name = arrayOf("updated_date"))
	val updatedDate: String? = null,

	@field:SerializedName("product_size_id")
	@field:JsonField(name = arrayOf("product_size_id"))
	val productSizeId: String? = null,

	@field:SerializedName("catname_local")
	@field:JsonField(name = arrayOf("catname_local"))
	val catnameLocal: String? = null,

	@field:SerializedName("commission_type")
	@field:JsonField(name = arrayOf("commission_type"))
	val commissionType: String? = null,

	@field:SerializedName("seller_id")
	@field:JsonField(name = arrayOf("seller_id"))
	val sellerId: String? = null,

	@field:SerializedName("size_name")
	@field:JsonField(name = arrayOf("size_name"))
	val sizeName: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)