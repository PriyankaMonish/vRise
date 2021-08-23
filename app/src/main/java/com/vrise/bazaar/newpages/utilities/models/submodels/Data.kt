package com.vrise.bazaar.newpages.utilities.models.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.temp.Wallet

@JsonObject
data class Data(

	@field:SerializedName("coprocess")
    @field:JsonField(name = arrayOf("coprocess"))
    val coprocess: Coprocess? = null,

	@field:SerializedName("coupon_data")
    @field:JsonField(name = arrayOf("coupon_data"))
    val couponData: CouponData? = null,

	@field:SerializedName("reff_msg")
    @field:JsonField(name = arrayOf("reff_msg"))
    val reff_msg: String? = null,

	@field:SerializedName("reff_code")
    @field:JsonField(name = arrayOf("reff_code"))
    val reff_code: String? = null,

	@field:SerializedName("invite_msg")
    @field:JsonField(name = arrayOf("invite_msg"))
    val invite_msg: String? = null,

	@field:SerializedName("cart")
    @field:JsonField(name = arrayOf("cart"))
    val cart: Cart? = null,

	@field:SerializedName("prefer_language")
    @field:JsonField(name = arrayOf("prefer_language"))
    val pre_lan: String? = "1",

	@field:SerializedName("device_id")
    @field:JsonField(name = arrayOf("device_id"))
    val deviceId: String? = null,

	@field:SerializedName("action")
    @field:JsonField(name = arrayOf("action"))
    val action: String? = null,

	@field:SerializedName("due_amount")
    @field:JsonField(name = arrayOf("due_amount"))
    val dueAmount: String? = null,

	@field:SerializedName("due")
    @field:JsonField(name = arrayOf("due"))
    val due: Due? = null,

	@field:SerializedName("mobileno")
    @field:JsonField(name = arrayOf("mobileno"))
    val mobileno: String? = null,

	@field:SerializedName("receipt_list")
    @field:JsonField(name = arrayOf("receipt_list"))
    val receiptList: ArrayList<ReceiptListItem>? = null,

	@field:SerializedName("otp")
    @field:JsonField(name = arrayOf("otp"))
    val otp: String? = null,

	@field:SerializedName("notifications")
    @field:JsonField(name = arrayOf("notifications"))
    val notifications: ArrayList<NotificationsItem?>? = null,

	@field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val img_link: String? = null,

	@field:SerializedName("agentdata")
    @field:JsonField(name = arrayOf("agentdata"))
    val agentdata: AgentData? = null,

	@field:SerializedName("user_token")
    @field:JsonField(name = arrayOf("user_token"))
    val userToken: String? = null,

	@field:SerializedName("userdata")
    @field:JsonField(name = arrayOf("userdata"))
    val userdata: Userdata? = null,

	@field:SerializedName("cart_count")
    @field:JsonField(name = arrayOf("cart_count"))
    val cartCount: Int? = 0,

	@field:SerializedName("delivery_address")
    @field:JsonField(name = arrayOf("delivery_address"))
    val deliveryAddress: DeliveryAddress? = null,

	@field:SerializedName("fav_count")
    @field:JsonField(name = arrayOf("fav_count"))
    val favCount: Int? = 0,

	@field:SerializedName("product")
    @field:JsonField(name = arrayOf("product"))
    val product: String? = null,

	@field:SerializedName("notifi_count")
    @field:JsonField(name = arrayOf("notifi_count"))
    val notifiCount: String? = null,

	@field:SerializedName("next_bill")
    @field:JsonField(name = arrayOf("next_bill"))
    val nextBill: String? = null,

	@field:SerializedName("duelist")
    @field:JsonField(name = arrayOf("duelist"))
    val duelist: ArrayList<DuelistItem>? = null,

	@field:SerializedName("bank")
    @field:JsonField(name = arrayOf("bank"))
    val banks: Bank? = null,

	@field:SerializedName("subduelist")
    @field:JsonField(name = arrayOf("subduelist"))
    val subduelist: ArrayList<SubduelistItem>? = null,

	@field:SerializedName("reminder_routes")
    @field:JsonField(name = arrayOf("reminder_routes"))
    val reminderRoutes: ArrayList<ReminderRoutesItem>? = null,

	@field:SerializedName("billlist")
    @field:JsonField(name = arrayOf("billlist"))
    val billlist: List<BilllistItem?>? = null,

	@field:SerializedName("reminder_subscribers")
    @field:JsonField(name = arrayOf("reminder_subscribers"))
    val reminderSubscribers: ArrayList<ReminderSubscribersItem>? = null,

	@field:SerializedName("total_dues")
    @field:JsonField(name = arrayOf("total_dues"))
    val totalDues: String? = null,

	@field:SerializedName("banner")
    @field:JsonField(name = arrayOf("banner"))
    val banner: ArrayList<BannerItem>? = null,

	@field:SerializedName("product_list")
    @field:JsonField(name = arrayOf("product_list"))
    val productList: ArrayList<ProductListItem?>? = null,

	@field:SerializedName("pro_data")
    @field:JsonField(name = arrayOf("pro_data"))
    val proData: ProductItemsList? = null,

	@field:SerializedName("productlist")
    @field:JsonField(name = arrayOf("productlist"))
    val productlist: List<ProductItemsList?>? = null,

	@field:SerializedName("rootlist")
    @field:JsonField(name = arrayOf("rootlist"))
    val rootlist: ArrayList<RootlistItem>? = null,

	@field:SerializedName("languagelist")
    @field:JsonField(name = arrayOf("languagelist"))
    val languagelist: ArrayList<LanguagelistItem>? = null,

	@field:SerializedName("categories")
    @field:JsonField(name = arrayOf("categories"))
    val categories: List<CategoriesItem?>? = null,

	@field:SerializedName("notfi_count")
    @field:JsonField(name = arrayOf("notfi_count"))
    val notfiCount: Int? = 0,

	@field:SerializedName("categorylist")
    @field:JsonField(name = arrayOf("categorylist"))
    val categorylist: ArrayList<CategorylistItem>? = null,

	@field:SerializedName("subdata")
    @field:JsonField(name = arrayOf("subdata"))
    val subdata: Subdata? = null,

	@field:SerializedName("autobill_data")
    @field:JsonField(name = arrayOf("autobill_data"))
    val autobillData: AutobillData? = null,

	@field:SerializedName("autosms_status")
    @field:JsonField(name = arrayOf("autosms_status"))
    val autoSmsStatus: String? = null, @field:SerializedName("autobill_status")
    @field:JsonField(name = arrayOf("autobill_status"))
    val autobillStatus: String? = null,

	@field:SerializedName("all_user")
    @field:JsonField(name = arrayOf("all_user"))
    val allUser: String? = null,

	@field:SerializedName("napp_user")
    @field:JsonField(name = arrayOf("napp_user"))
    val nAppUser: String? = null,

	@field:SerializedName("pending_sms")
    @field:JsonField(name = arrayOf("pending_sms"))
    val pendingSms: String = "0",

	@field:SerializedName("prdlist")
    @field:JsonField(name = arrayOf("prdlist"))
    val prdlist: ArrayList<PrdlistItem?>? = null,

	@field:SerializedName("profile_data")
    @field:JsonField(name = arrayOf("profile_data"))
    val profile_data: Profiledata? = null,

	@field:SerializedName("profiledata")
    @field:JsonField(name = arrayOf("profiledata"))
    val profiledata: Profiledata? = null,

	@field:SerializedName("sublist")
    @field:JsonField(name = arrayOf("sublist"))
    val sublist: ArrayList<SublistItem>? = null,

	@field:SerializedName("states")
    @field:JsonField(name = arrayOf("states"))
    val states: List<StatesItem?>? = null,

	@field:SerializedName("cities")
    @field:JsonField(name = arrayOf("cities"))
    val cities: List<CitiesItem?>? = null,

	@field:SerializedName("districts")
    @field:JsonField(name = arrayOf("districts"))
    val districts: List<DistrictsItem?>? = null,

	@field:SerializedName("subscriber_id")
    @field:JsonField(name = arrayOf("subscriber_id"))
    val subscriberId: String? = null,

	@field:SerializedName("pending_list")
    @field:JsonField(name = arrayOf("pending_list"))
    val pendingList: List<PendingListItem?>? = null,

	@field:SerializedName("subscriber_count")
    @field:JsonField(name = arrayOf("subscriber_count"))
    val subscriberCount: String? = null,

	@field:SerializedName("payment_hash")
    @field:JsonField(name = arrayOf("payment_hash"))
    val paymentHash: String? = null,

	@field:SerializedName("vas_for_mobile_sdk_hash")
    @field:JsonField(name = arrayOf("vas_for_mobile_sdk_hash"))
    val vasForMobileSdkHash: String? = null,

	@field:SerializedName("payment_related_details_for_mobile_sdk_hash")
    @field:JsonField(name = arrayOf("payment_related_details_for_mobile_sdk_hash"))
    val paymentRelatedDetailsForMobileSdkHash: String? = null,

	@field:SerializedName("delete_user_card_hash")
    @field:JsonField(name = arrayOf("delete_user_card_hash"))
    val deleteUserCardHash: String? = null,

	@field:SerializedName("get_user_cards_hash")
    @field:JsonField(name = arrayOf("get_user_cards_hash"))
    val getUserCardsHash: String? = null,

	@field:SerializedName("edit_user_card_hash")
    @field:JsonField(name = arrayOf("edit_user_card_hash"))
    val editUserCardHash: String? = null,

	@field:SerializedName("save_user_card_hash")
    @field:JsonField(name = arrayOf("save_user_card_hash"))
    val saveUserCardHash: String? = null,

	@field:SerializedName("check_offer_status_hash")
    @field:JsonField(name = arrayOf("check_offer_status_hash"))
    val checkOfferStatusHash: String? = null,

	@field:SerializedName("verify_payment_hash")
    @field:JsonField(name = arrayOf("verify_payment_hash"))
    val verifyPaymentHash: String? = null,

	@field:SerializedName("sms_credit")
    @field:JsonField(name = arrayOf("sms_credit"))
    val smsCredit: String = "0",

	@field:SerializedName("sms_packs")
    @field:JsonField(name = arrayOf("sms_packs"))
    val smsPacks: ArrayList<SmsPacksItem?>? = null,

	@field:SerializedName("subcategorylist")
    @field:JsonField(name = arrayOf("subcategorylist"))
    val subcategorylist: List<SubcategorylistItem?>? = null,

	@field:SerializedName("fav_list")
    @field:JsonField(name = arrayOf("fav_list"))
    val favList: List<FavListItem?>? = null,

	@field:SerializedName("productdata")
    @field:JsonField(name = arrayOf("productdata"))
    val productdata: Productdatas? = null,

	@field:SerializedName("pre_prod_list")
    @field:JsonField(name = arrayOf("pre_prod_list"))
    val preProdList: List<PreProdListItem?>? = null,

	@field:SerializedName("address")
    @field:JsonField(name = arrayOf("address"))
    val address: List<AddressItem?>? = null,

	@field:SerializedName("related_products")
    @field:JsonField(name = arrayOf("related_products"))
    val relatedProducts: List<ProductItemsList?>? = null,

	@field:SerializedName("wallet")
    @field:JsonField(name = arrayOf("wallet"))
    val wallet: Wallet? = null,

	@field:SerializedName("booklist")
    @field:JsonField(name = arrayOf("booklist"))
    val booklist: List<BooklistItem?>? = null,

	@field:SerializedName("pending_ords")
    @field:JsonField(name = arrayOf("pending_ords"))
    val pendingOrds: List<PendingOrdsItem?>? = null,

	@field:SerializedName("past_ords")
    @field:JsonField(name = arrayOf("past_ords"))
    val pastOrds: List<PastOrdsItem?>? = null,

	@field:SerializedName("book_count")
    @field:JsonField(name = arrayOf("book_count"))
    val book_count: Int = 0,

	@field:SerializedName("sellers")
    @field:JsonField(name = arrayOf("sellers"))
    val sellerList: List<SellersItem?>? = null

)