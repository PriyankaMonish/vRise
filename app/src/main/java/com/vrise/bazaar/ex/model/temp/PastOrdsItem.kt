package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.newmodels.SellerDataItem

@JsonObject
data class PastOrdsItem(

	@field:SerializedName("sale_id")
    @field:JsonField(name = arrayOf("sale_id"))
    val saleId: String? = null,

	@field:SerializedName("address_latitude")
    @field:JsonField(name = arrayOf("address_latitude"))
    val addressLatitude: String? = null,

	@field:SerializedName("delivery_address")
    @field:JsonField(name = arrayOf("delivery_address"))
    val deliveryAddress: DeliveryAddress? = null,

	@field:SerializedName("product_data")
    @field:JsonField(name = arrayOf("product_data"))
    val productData: ProductData? = null,

	@field:SerializedName("delivery_remark")
    @field:JsonField(name = arrayOf("delivery_remark"))
    val deliveryRemark: String? = null,

	@field:SerializedName("cod_amount")
    @field:JsonField(name = arrayOf("cod_amount"))
    val codAmount: String? = null,

	@field:SerializedName("cod_due")
    @field:JsonField(name = arrayOf("cod_due"))
    val codDue: String? = null,

	@field:SerializedName("seller_product_id")
    @field:JsonField(name = arrayOf("seller_product_id"))
    val sellerProductId: String? = null,

	@field:SerializedName("product_quantity")
    @field:JsonField(name = arrayOf("product_quantity"))
    val productQuantity: String? = null,

	@field:SerializedName("delivery_start_time")
    @field:JsonField(name = arrayOf("delivery_start_time"))
    val deliveryStartTime: String? = null,

	@field:SerializedName("coupon_id")
    @field:JsonField(name = arrayOf("coupon_id"))
    val couponId: String? = null,

	@field:SerializedName("sale_date")
    @field:JsonField(name = arrayOf("sale_date"))
    val saleDate: String? = null,

	@field:SerializedName("delivery_type")
    @field:JsonField(name = arrayOf("delivery_type"))
    val deliveryType: String? = null,

	@field:SerializedName("sale_code")
    @field:JsonField(name = arrayOf("sale_code"))
    val saleCode: String? = null,

	@field:SerializedName("delivery_slot_data")
    @field:JsonField(name = arrayOf("delivery_slot_data"))
    val deliverySlotData: DeliverySlotData? = null,

	@field:SerializedName("address_longitude")
    @field:JsonField(name = arrayOf("address_longitude"))
    val addressLongitude: String? = null,

	@field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

	@field:SerializedName("payment_status")
    @field:JsonField(name = arrayOf("payment_status"))
    val paymentStatus: String? = null,

	@field:SerializedName("address_id")
    @field:JsonField(name = arrayOf("address_id"))
    val addressId: String? = null,

	@field:SerializedName("ecom_product_id")
    @field:JsonField(name = arrayOf("ecom_product_id"))
    val ecomProductId: String? = null,

	@field:SerializedName("iteam_total")
    @field:JsonField(name = arrayOf("iteam_total"))
    val iteamTotal: String? = null,

	@field:SerializedName("delivery_boy")
    @field:JsonField(name = arrayOf("delivery_boy"))
    val deliveryBoy: String? = null,

	@field:SerializedName("delivery_slot_id")
    @field:JsonField(name = arrayOf("delivery_slot_id"))
    val deliverySlotId: String? = null,

	@field:SerializedName("online_amount")
    @field:JsonField(name = arrayOf("online_amount"))
    val onlineAmount: String? = null,

	@field:SerializedName("spot_delivery")
    @field:JsonField(name = arrayOf("spot_delivery"))
    val spotDelivery: String? = null,

	@field:SerializedName("myreview")
    @field:JsonField(name = arrayOf("myreview"))
    val myreview: Myreview? = null,

	@field:SerializedName("update_date")
    @field:JsonField(name = arrayOf("update_date"))
    val updateDate: String? = null,

	@field:SerializedName("order_code")
    @field:JsonField(name = arrayOf("order_code"))
    val orderCode: String? = null,

	@field:SerializedName("order_date")
    @field:JsonField(name = arrayOf("order_date"))
    val orderDate: String? = null,

	@field:SerializedName("delivery_date")
    @field:JsonField(name = arrayOf("delivery_date"))
    val deliveryDate: String? = null,

	@field:SerializedName("delivery_charge")
    @field:JsonField(name = arrayOf("delivery_charge"))
    val deliveryCharge: Float? = null,

	@field:SerializedName("user_id")
    @field:JsonField(name = arrayOf("user_id"))
    val userId: String? = null,

	@field:SerializedName("product_size_id")
    @field:JsonField(name = arrayOf("product_size_id"))
    val productSizeId: String? = null,

	@field:SerializedName("coupon_discount")
    @field:JsonField(name = arrayOf("coupon_discount"))
    val couponDiscount: String? = null,

	@field:SerializedName("delivery_status")
    @field:JsonField(name = arrayOf("delivery_status"))
    val deliveryStatus: String? = null,

	@field:SerializedName("delivery_data")
    @field:JsonField(name = arrayOf("delivery_data"))
    val deliveryData: String? = null,

	@field:SerializedName("wallet_amount")
    @field:JsonField(name = arrayOf("wallet_amount"))
    val walletAmount: String? = null,

	@field:SerializedName("seller_data")
    @field:JsonField(name = arrayOf("seller_data"))
    val sellerData: SellerDataItem? = null,

	@field:SerializedName("store_distance")
    @field:JsonField(name = arrayOf("store_distance"))
    val storeDistance: String? = null

    )