package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

/*@JsonObject
data class SubcategorylistItem(

	@field:SerializedName("name_local")
	@field:JsonField(name = arrayOf("name_local"))
	val nameLocal: String? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("description")
	@field:JsonField(name = arrayOf("description"))
	val description: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("category")
	@field:JsonField(name = arrayOf("category"))
	val category: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)*/

@JsonObject
data class SubcategorylistItem(

	@field:SerializedName("name_local")
	@field:JsonField(name = arrayOf("name_local"))
	val nameLocal: String? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("prefer_list")
	@field:JsonField(name = arrayOf("prefer_list"))
	val preferList: String? = "0",

	@field:SerializedName("description")
	@field:JsonField(name = arrayOf("description"))
	val description: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("category")
	@field:JsonField(name = arrayOf("category"))
	val category: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null,

	@field:SerializedName("fav_status")
	@field:JsonField(name = arrayOf("fav_status"))
	val favStatus: String = "0"
)