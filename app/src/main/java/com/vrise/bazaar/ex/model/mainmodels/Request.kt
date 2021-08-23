package com.vrise.bazaar.ex.model.mainmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.ProductsRateItem
import com.vrise.bazaar.ex.model.newcart.ShopDeliveryItem
import com.vrise.bazaar.ex.model.submodels.BillProductsItem
import com.vrise.bazaar.ex.model.submodels.ReceiptidsItem
import com.vrise.bazaar.ex.model.submodels.RouteidsItem
import com.vrise.bazaar.ex.model.submodels.SubscriptionsItem
import java.io.File
import java.io.Serializable

@JsonObject
data class Request(

	@field:SerializedName("coupon_id")
    @field:JsonField(name = arrayOf("coupon_id"))
    val coupon_id: String? = null,

	@field:SerializedName("grand_total")
    @field:JsonField(name = arrayOf("grand_total"))
    val grand_total: String? = null,

	@field:SerializedName("otp")
    @field:JsonField(name = arrayOf("otp"))
    val otp: String? = null,

    @field:SerializedName("shipping_method")
    @field:JsonField(name = arrayOf("shipping_method"))
    var shipping_method: String? = null,

	@field:SerializedName("change_seller")
    @field:JsonField(name = arrayOf("change_seller"))
    val changeSeller : String? = null,

	@field:SerializedName("wallet_status")
    @field:JsonField(name = arrayOf("wallet_status"))
    val wallet_status: String? = null,

	@field:SerializedName("cod_status")
    @field:JsonField(name = arrayOf("cod_status"))
    val cod_status: String? = null,

	@field:SerializedName("online_status")
    @field:JsonField(name = arrayOf("online_status"))
    val online_status: String? = null,

	@field:SerializedName("payment_status")
    @field:JsonField(name = arrayOf("payment_status"))
    var payment_status: String? = null,

	@field:SerializedName("online_amount")
    @field:JsonField(name = arrayOf("online_amount"))
    val online_amount: String? = null,

	@field:SerializedName("wallet_amount")
    @field:JsonField(name = arrayOf("wallet_amount"))
    val wallet_amount: String? = null,

	@field:SerializedName("cod_amount")
    @field:JsonField(name = arrayOf("cod_amount"))
    val cod_amount: String? = null,

	@field:SerializedName("online_data")
    @field:JsonField(name = arrayOf("online_data"))
    var online_data: String? = null,

	@field:SerializedName("peak_time_charge")
	@field:JsonField(name = arrayOf("peak_time_charge"))
	val peak_time_charge: String? = null,

	@field:SerializedName("online_txnid")
    @field:JsonField(name = arrayOf("online_txnid"))
    var txnid: String? = null,

	@field:SerializedName("delivery_address_id")
    @field:JsonField(name = arrayOf("delivery_address_id"))
    val delivery_address_id: String? = null,

	@field:SerializedName("address_id")
    @field:JsonField(name = arrayOf("address_id"))
    val address_id: String? = null,

	@field:SerializedName("address_type")
    @field:JsonField(name = arrayOf("address_type"))
    val addr: String? = null,

	@field:SerializedName("total_amount")
    @field:JsonField(name = arrayOf("total_amount"))
    val total_amount: Float? = null,

	@field:SerializedName("payment")
    @field:JsonField(name = arrayOf("payment"))
    val payment: String? = null,

	@field:SerializedName("payment_data")
    @field:JsonField(name = arrayOf("payment_data"))
    val paymentData: String? = null,

	@field:SerializedName("ifsc_code")
    @field:JsonField(name = arrayOf("ifsc_code"))
    val ifscCode: String? = null,

	@field:SerializedName("account_number")
    @field:JsonField(name = arrayOf("account_number"))
    val accountNumber: String? = null,

	@field:SerializedName("account_holder")
    @field:JsonField(name = arrayOf("account_holder"))
    val accountHolder: String? = null,

	@field:SerializedName("bank_id")
    @field:JsonField(name = arrayOf("bank_id"))
    val bankId: String? = null,

	@field:SerializedName("branch_name")
    @field:JsonField(name = arrayOf("branch_name"))
    val branchName: String? = null,

	@field:SerializedName("bank_name")
    @field:JsonField(name = arrayOf("bank_name"))
    val bankName: String? = null,

	@field:SerializedName("autobill_status")
    @field:JsonField(name = arrayOf("autobill_status"))
    val autobillStatus: String? = null,

	@field:SerializedName("app")
    @field:JsonField(name = arrayOf("app"))
    val app: String? = null,

    @field:SerializedName("item_count")
    @field:JsonField(name = arrayOf("item_count"))
    val item_count: String? = null,

	@field:SerializedName("product_id")
    @field:JsonField(name = arrayOf("product_id"))
    val product_id: String? = null,

    @field:SerializedName("productid")
    @field:JsonField(name = arrayOf("productid"))
    val productid: String? = null,

    @field:SerializedName("productsizeid")
    @field:JsonField(name = arrayOf("productsizeid"))
    val productsizeid: String? = null,

	@field:SerializedName("order_id")
    @field:JsonField(name = arrayOf("order_id"))
    val order_id: String? = null,

    @field:SerializedName("confirm_status")
    @field:JsonField(name = arrayOf("confirm_status"))
    val confirm_status: String? = null,

	@field:SerializedName("amount")
    @field:JsonField(name = arrayOf("amount"))
    val amount: String? = null,

	@field:SerializedName("utoken")
    @field:JsonField(name = arrayOf("utoken"))
    val utoken: String? = null,
	@field:SerializedName("sale_id")
    @field:JsonField(name = arrayOf("sale_id"))
    val sale_id: String? = null,

	@field:SerializedName("voice")
    @field:JsonField(name = arrayOf("voice"))
    val voice: File? = null,

    @field:SerializedName("message")
    @field:JsonField(name = arrayOf("message"))
    val message: String? = null,

    @field:SerializedName("chat_id")
    @field:JsonField(name = arrayOf("chat_id"))
    val chatId: String? = null,

	@field:SerializedName("couponcode")
    @field:JsonField(name = arrayOf("couponcode"))
    val codecoupon: String? = null,

	@field:SerializedName("total")
    @field:JsonField(name = arrayOf("total"))
    val total: String? = null,

	@field:SerializedName("shop_id")
    @field:JsonField(name = arrayOf("shop_id"))
    val shop_id: String? = null,


    @field:SerializedName("start_date")
    @field:JsonField(name = arrayOf("start_date"))
    val start_date: String? = null,

    @field:SerializedName("product_count")
    @field:JsonField(name = arrayOf("product_count"))
    val product_count: Int? = null,

    @field:SerializedName("editing")
    @field:JsonField(name = arrayOf("editing"))
    val editing: String? = null,

    @field:SerializedName("end_date")
    @field:JsonField(name = arrayOf("end_date"))
    val end_date: String? = null,

	@field:SerializedName("cart_id")
    @field:JsonField(name = arrayOf("cart_id"))
    val cartId: String? = null,

	@field:SerializedName("cart_ids")
    @field:JsonField(name = arrayOf("cart_id"))
    val cart_ids: List<CartIDList>? = null,

    @field:SerializedName("product_ids")
    @field:JsonField(name = arrayOf("product_ids"))
    val product_ids: List<ProductIDs>? = null,

	@field:SerializedName("day")
    @field:JsonField(name = arrayOf("day"))
    val day: String? = null,

	@field:SerializedName("bill_id")
    @field:JsonField(name = arrayOf("bill_id"))
    val billId: String? = null,

	@field:SerializedName("additional_amount")
    @field:JsonField(name = arrayOf("additional_amount"))
    val additionalAmount: String? = null,

	@field:SerializedName("autosms_status")
    @field:JsonField(name = arrayOf("autosms_status"))
    val autosms_status: String? = null,

	@field:SerializedName("billproducts_id")
    @field:JsonField(name = arrayOf("billproducts_id"))
    val billproductsd: String? = null,

	@field:SerializedName("bill_products")
    @field:JsonField(name = arrayOf("bill_products"))
    var billProducts: List<BillProductsItem?>? = null,

	@field:SerializedName("remove_product")
    @field:JsonField(name = arrayOf("remove_product"))
    val removeProduct: List<SubscriptionsItem>? = null,

	@field:SerializedName("cashback_amount")
    @field:JsonField(name = arrayOf("cashback_amount"))
    val cashbackAmount: String? = null,

	@field:SerializedName("discount_amount")
    @field:JsonField(name = arrayOf("discount_amount"))
    val discountAmount: String? = null,

	@field:SerializedName("language")
    @field:JsonField(name = arrayOf("language"))
    val language: String? = null,

	@field:SerializedName("mobile")
    @field:JsonField(name = arrayOf("mobile"))
    val mobile: String? = null,

	@field:SerializedName("agent_price")
    @field:JsonField(name = arrayOf("agent_price"))
    val agent_price: String? = null,

	@field:SerializedName("limit")
    @field:JsonField(name = arrayOf("limit"))
    val limit: String? = null,

	@field:SerializedName("route_id")
    @field:JsonField(name = arrayOf("route_id"))
    val routeId: String? = null,

	@field:SerializedName("offset")
    @field:JsonField(name = arrayOf("offset"))
    val offset: String? = null,

	@field:SerializedName("ref_code")
    @field:JsonField(name = arrayOf("ref_code"))
    val ref_code: String? = null,




	@field:SerializedName("device_id")
    @field:JsonField(name = arrayOf("device_id"))
    val device_id: String? = null,

    @field:SerializedName("description")
    @field:JsonField(name = arrayOf("description"))
    val description: String? = null,

    /*@field:SerializedName("post_office")
    @field:JsonField(name = arrayOf("post_office"))
    val post_office: String? = null,*/

    @field:SerializedName("pincode")
    @field:JsonField(name = arrayOf("pincode"))
    val pincode: String? = null,

	@field:SerializedName("user_type")
    @field:JsonField(name = arrayOf("user_type"))
    val user_type: String? = null,

	@field:SerializedName("route_name")
    @field:JsonField(name = arrayOf("route_name"))
    val routeName: String? = null,

	@field:SerializedName("route_area")
    @field:JsonField(name = arrayOf("route_area"))
    val routeArea: String? = null,

	@field:SerializedName("profile_pic")
    @field:JsonField(name = arrayOf("profile_pic"))
    val profile_pic: String? = null,

	@field:SerializedName("code")
    @field:JsonField(name = arrayOf("code"))
    val code: String? = null,

	@field:SerializedName("address")
    @field:JsonField(name = arrayOf("address"))
    val address: String? = null,

	@field:SerializedName("address2")
    @field:JsonField(name = arrayOf("address2"))
    val address2: String? = null,

	@field:SerializedName("agent_id")
    @field:JsonField(name = arrayOf("agent_id"))
    val agent_id: String? = null,

	@field:SerializedName("city")
    @field:JsonField(name = arrayOf("city"))
    val city: String? = null,

	@field:SerializedName("state")
    @field:JsonField(name = arrayOf("state"))
    val state: String? = null,

	@field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

	@field:SerializedName("month")
    @field:JsonField(name = arrayOf("month"))
    val month: String? = null,

	@field:SerializedName("routeids")
    @field:JsonField(name = arrayOf("routeids"))
    val routeids: ArrayList<RouteidsItem>? = null,

	@field:SerializedName("not_id")
    @field:JsonField(name = arrayOf("not_id"))
    val notId: String? = null,

	@field:SerializedName("receiptids")
    @field:JsonField(name = arrayOf("receiptids"))
    val receiptids: List<ReceiptidsItem?>? = null,

	@field:SerializedName("market_code")
    @field:JsonField(name = arrayOf("market_code"))
    val market_code: String? = null,

	@field:SerializedName("email")
    @field:JsonField(name = arrayOf("email"))
    val email: String? = null,

	@field:SerializedName("subscriber_id")
    @field:JsonField(name = arrayOf("subscriber_id"))
    val subscriberId: String? = null,

	@field:SerializedName("category")
    @field:JsonField(name = arrayOf("category"))
    val category: String? = null,

	@field:SerializedName("searchkey")
    @field:JsonField(name = arrayOf("searchkey"))
    val searchkey: String? = null,

	@field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val string: String? = null,

	@field:SerializedName("agent_product")
    @field:JsonField(name = arrayOf("agent_product"))
    val agent_product: String? = null,

	@field:SerializedName("subscriber_product")
    @field:JsonField(name = arrayOf("subscriber_product"))
    val subscriber_product: String? = null,
	@field:SerializedName("bill_date_id")
    @field:JsonField(name = arrayOf("bill_date_id"))
    val billDateId: String? = null,

	@field:SerializedName("bill_type")
    @field:JsonField(name = arrayOf("bill_type"))
    val billType: String? = null,

	@field:SerializedName("sms_users")
    @field:JsonField(name = arrayOf("sms_users"))
    val smsUsers: String? = null,

	@field:SerializedName("settle_amount")
    @field:JsonField(name = arrayOf("settle_amount"))
    val settleAmount: String? = null,

	@field:SerializedName("sub_product_status")
    @field:JsonField(name = arrayOf("sub_product_status"))
    val sub_product_status: String? = null,

	@field:SerializedName("product_list")
    @field:JsonField(name = arrayOf("product_list"))
    val productList: ArrayList<SubscriptionsItem>? = null,

	@field:SerializedName("date")
    @field:JsonField(name = arrayOf("date"))
    val date: String? = null,

	@field:SerializedName("remark")
    @field:JsonField(name = arrayOf("remark"))
    val remark: String? = null,

	@field:SerializedName("agent_code")
    @field:JsonField(name = arrayOf("agent_code"))
    val agent_code: String? = null,

	@field:SerializedName("request_type")
    @field:JsonField(name = arrayOf("request_type"))
    val requestType: String? = null,

	@field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

	@field:SerializedName("post_office")
    @field:JsonField(name = arrayOf("post_office"))
    val postOffice: String? = null,

    @field:SerializedName("seller_ref")
    @field:JsonField(name = arrayOf("seller_ref"))
    val sellerref: String? = null,

	@field:SerializedName("district")
    @field:JsonField(name = arrayOf("district"))
    val district: String? = null,

	@field:SerializedName("district_name")
    @field:JsonField(name = arrayOf("district_name"))
    val districtNames: String? = null,

	@field:SerializedName("landmark")
    @field:JsonField(name = arrayOf("landmark"))
    val landmark: String? = null,

	@field:SerializedName("latitude")
    @field:JsonField(name = arrayOf("latitude"))
    val latitude: String? = null,

	@field:SerializedName("longitude")
    @field:JsonField(name = arrayOf("longitude"))
    val longitude: String? = null,

	@field:SerializedName("default_address")
    @field:JsonField(name = arrayOf("default_address"))
    val defaultaddress: String? = null,

	@field:SerializedName("banner")
    @field:JsonField(name = arrayOf("banner"))
    val banner: String? = null,

	@field:SerializedName("dealof_day")
    @field:JsonField(name = arrayOf("dealof_day"))
    val dealof_day: String? = null,

	@field:SerializedName("sub_category")
    @field:JsonField(name = arrayOf("sub_category"))
    val sub_category: String? = null,

	@field:SerializedName("categorylist")
    @field:JsonField(name = arrayOf("categorylist"))
    val categorylist: String? = null,

	@field:SerializedName("category_id")
    @field:JsonField(name = arrayOf("category_id"))
    val category_id: String? = null,

    @field:SerializedName("main_subcategory_id")
    @field:JsonField(name = arrayOf("main_subcategory_id"))
    val main_subcategory_id: String? = null,

	@field:SerializedName("veg_non")
    @field:JsonField(name = arrayOf("veg_non"))
    val vegOrNon: String? = null,

    @field:SerializedName("filter")
    @field:JsonField(name = arrayOf("filter"))
    val filter: Filters? = null,

	@field:SerializedName("store_radius")
    @field:JsonField(name = arrayOf("store_radius"))
    val store_radius: String? = null,

	@field:SerializedName("subcategory_id")
    @field:JsonField(name = arrayOf("subcategory_id"))
    val subcategory_id: String? = null,

	@field:SerializedName("search_Key")
    @field:JsonField(name = arrayOf("search_Key"))
    val search_Key: String? = null,

	@field:SerializedName("fav_process")
    @field:JsonField(name = arrayOf("fav_process"))
    val fav_process: String? = null,

	@field:SerializedName("selprdt_id")
    @field:JsonField(name = arrayOf("selprdt_id"))
    val selprdt_id: String? = null,

	@field:SerializedName("delivery_date")
    @field:JsonField(name = arrayOf("delivery_date"))
    val delivery_date: String? = null,

	@field:SerializedName("del_date")
    @field:JsonField(name = arrayOf("del_date"))
    val deldate: String? = null,

	@field:SerializedName("delivery_slot_id")
    @field:JsonField(name = arrayOf("delivery_slot_id"))
    val delivery_slot_id: String? = null,

	@field:SerializedName("delivery_type")
    @field:JsonField(name = arrayOf("delivery_type"))
    val delivery_type: String? = null,

	@field:SerializedName("product_quantity")
    @field:JsonField(name = arrayOf("product_quantity"))
    val product_quantity: String? = null,

	@field:SerializedName("product_size_id")
    @field:JsonField(name = arrayOf("product_size_id"))
    val product_size_id: String? = null,

	@field:SerializedName("seller_product_id")
    @field:JsonField(name = arrayOf("seller_product_id"))
    val seller_product_id: String? = null,

	@field:SerializedName("ecom_product_id")
    @field:JsonField(name = arrayOf("ecom_product_id"))
    val ecom_product_id: String? = null,

	@field:SerializedName("sprdtid")
    @field:JsonField(name = arrayOf("sprdtid"))
    val sprdtid: String? = null,

	@field:SerializedName("prefer")
    @field:JsonField(name = arrayOf("prefer"))
    val prefer: String? = null,

	@field:SerializedName("booking_date")
    @field:JsonField(name = arrayOf("booking_date"))
    val booking_date: String? = null,

	@field:SerializedName("rating")
    @field:JsonField(name = arrayOf("rating"))
    val rating: String? = null,

	@field:SerializedName("review")
    @field:JsonField(name = arrayOf("review"))
    val review: String? = null,

	@field:SerializedName("ecom_productid")
    @field:JsonField(name = arrayOf("ecom_productid"))
    val ecom_productid: String? = null,

	@field:SerializedName("product_review")
    @field:JsonField(name = arrayOf("product_review"))
    val product_review: String? = null,

	@field:SerializedName("seller_id")
    @field:JsonField(name = arrayOf("seller_id"))
    val seller_id: String? = null,

	@field:SerializedName("product_rating")
    @field:JsonField(name = arrayOf("product_rating"))
    val product_rating: String? = null,

	@field:SerializedName("seller_review")
    @field:JsonField(name = arrayOf("seller_review"))
    val seller_review: String? = null,

	@field:SerializedName("delivery_boy_id")
    @field:JsonField(name = arrayOf("delivery_boy_id"))
    val delivery_boy_id: String? = null,

	@field:SerializedName("delivery_boy_rating")
    @field:JsonField(name = arrayOf("delivery_boy_rating"))
    val delivery_boy_rating: String? = null,

	@field:SerializedName("delivery_boy_review")
    @field:JsonField(name = arrayOf("delivery_boy_review"))
    val delivery_boy_review: String? = null,

	@field:SerializedName("products_rate")
    @field:JsonField(name = arrayOf("products_rate"))
    val products_rate: List<ProductsRateItem?>? = null,

	@field:SerializedName("seller_rating")
    @field:JsonField(name = arrayOf("seller_rating"))
    val seller_rating: String? = null,

    @field:SerializedName("sorting")
    @field:JsonField(name = arrayOf("sorting"))
    val sorting: String? = null,

    @field:SerializedName("myorder")
    @field:JsonField(name = arrayOf("myorder"))
    val myorder : Int? = null,


	@field:SerializedName("shop")
    @field:JsonField(name = arrayOf("shop"))
    var shops :ArrayList<ShopDeliveryItem?>? = null

) : Serializable

