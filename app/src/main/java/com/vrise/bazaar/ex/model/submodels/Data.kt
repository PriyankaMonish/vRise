package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.*
import com.vrise.bazaar.ex.model.DeliveryAddress

import com.vrise.bazaar.ex.model.newmodels.*
import com.vrise.bazaar.ex.model.temp.*

@JsonObject
data class Data(

    @field:SerializedName("track_data")
    @field:JsonField(name = arrayOf("track_data"))
    val trackData: TrackData? = null,

    @field:SerializedName("product_quantity")
    @field:JsonField(name = arrayOf("product_quantity"))
    val product_quantity: String? = null,

    @field:SerializedName("taglist_change")
    @field:JsonField(name = arrayOf("taglist_change"))
    val taglist_change: String? = null,

 @field:SerializedName("total_amount")
    @field:JsonField(name = arrayOf("total_amount"))
    val total_amount: Float? = null,

    @field:SerializedName("deliveryboy_data")
    @field:JsonField(name = arrayOf("deliveryboy_data"))
    val deliveryboyData: DeliveryboyData? = null,

    @field:SerializedName("category_tree")
    @field:JsonField(name = arrayOf("category_tree"))
    val category_tree: List<CategoryTree?>? = null,

    @field:SerializedName("status__data")
    @field:JsonField(name = arrayOf("status__data"))
    var statusData: StatusData? = null,

    @field:SerializedName("deliveryboy_status")
    @field:JsonField(name = arrayOf("deliveryboy_status"))
    var delivery_boy_status: String? = null,

    @field:SerializedName("coprocess")
    @field:JsonField(name = arrayOf("coprocess"))
    val coprocess: Coprocess? = null,

    @field:SerializedName("address_status")
    @field:JsonField(name = arrayOf("address_status"))
    val addressStatus: String? = null,

    @field:SerializedName("coupon_data")
    @field:JsonField(name = arrayOf("coupon_data"))
    val couponData: CouponData? = null,

    @field:SerializedName("default_address")
    @field:JsonField(name = arrayOf("default_address"))
    val defaultAddress: DefaultAddress? = null,

    @field:SerializedName("reff_msg")
    @field:JsonField(name = arrayOf("reff_msg"))
    val reff_msg: String? = null,

    @field:SerializedName("display")
    @field:JsonField(name = arrayOf("display"))
    val display: String? = null,

    @field:SerializedName("offer_data")
    @field:JsonField(name = arrayOf("offer_data"))
    val offerData: OfferData? = null,

    @field:SerializedName("shops")
    @field:JsonField(name = arrayOf("shops"))
    val shops: List<ShopsItem?>? = null,

    @field:SerializedName("recomm_product")
    @field:JsonField(name = arrayOf("recomm_product"))
    val recommProduct: List<RecommProductItem?>? = null,

    @field:SerializedName("recomm_shop")
    @field:JsonField(name = arrayOf("recomm_shop"))
    val recommShop: List<RecommShopItem?>? = null,

    @field:SerializedName("shop_products")
    @field:JsonField(name = arrayOf("shop_products"))
    val shopProducts: List<ShopProductsItem?>? = null,


    @field:SerializedName("shop_main_subcategories")
    @field:JsonField(name = arrayOf("shop_main_subcategories"))
    val shopSunCategories: List<ShopSubCategoriesItem?>? = null,

    @field:SerializedName("store_radius")
    @field:JsonField(name = arrayOf("store_radius"))
    val storeRadius: String?,

    @field:SerializedName("reff_code")
    @field:JsonField(name = arrayOf("reff_code"))
    val reff_code: String? = null,

    @field:SerializedName("shop_recomm_product")
    @field:JsonField(name = arrayOf("shop_recomm_product"))
    val shopRecommProduct: List<ShopRecommProductItem?>? = null,


    @field:SerializedName("recom_products")
    @field:JsonField(name = arrayOf("recom_products"))
    val recom_products: List<ShopRecommProductItem?>? = null,


    @field:SerializedName("recom_products_full")
    @field:JsonField(name = arrayOf("recom_products_full"))
    val recom_products_full: List<RecommProductItemFull?>? = null,

    @field:SerializedName("shop_data")
    @field:JsonField(name = arrayOf("shop_data"))
    val shopData: ShopData? = null,


    @field:SerializedName("order_model_text")
    @field:JsonField(name = arrayOf("order_model_text"))
    val order_model_text: String? = null,


    @field:SerializedName("invite_msg")
    @field:JsonField(name = arrayOf("invite_msg"))
    val invite_msg: String? = null,



    @field:SerializedName("cart")
    @field:JsonField(name = arrayOf("cart"))
    val cart: Cart? = null,

    @field:SerializedName("order_model")
    @field:JsonField(name = arrayOf("order_model"))
    val order_model: Int? = null,

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


    @field:SerializedName("seller_id")
    @field:JsonField(name = arrayOf("seller_id"))
    val seller_id: String? = null,

    @field:SerializedName("userdata")
    @field:JsonField(name = arrayOf("userdata"))
    val userdata: Userdata? = null,

    @field:SerializedName("cart_count")
    @field:JsonField(name = arrayOf("cart_count"))
    val cartCount: Int? = 0,

    @field:SerializedName("delivery_address")
    @field:JsonField(name = arrayOf("delivery_address"))
    val deliveryAddress: DeliveryAddress? = null,

    @field:SerializedName("shop_change")
    @field:JsonField(name = arrayOf("shop_change"))
    val shopChange: Int = 0,

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

    @field:SerializedName("ord_pending")
    @field:JsonField(name = arrayOf("ord_pending"))
    val ordPending: List<OrdPendingItem?>? = null,

    @field:SerializedName("requested_order")
    @field:JsonField(name = arrayOf("requested_order"))
    val requestedorder: List<RequestedOrder?>? = null,



    @field:SerializedName("order_shoplist")
    @field:JsonField(name = arrayOf("order_shoplist"))
    val order_shoplist: List<OrdShopList?>? = null,

    @field:SerializedName("ord_past")
    @field:JsonField(name = arrayOf("ord_past"))
    val ordPast: List<OrdPastItem?>? = null,


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

    @field:SerializedName("seller_response")
    @field:JsonField(name = arrayOf("seller_response"))
    val seller_response: String? = null,

    @field:SerializedName("prdlist")
    @field:JsonField(name = arrayOf("prdlist"))
    val prdlist: ArrayList<PrdlistItem?>? = null,

    @field:SerializedName("profile_data")
    @field:JsonField(name = arrayOf("profile_data"))
    val profile_data: Profiledata? = null,

    @field:SerializedName("filter_data")
    @field:JsonField(name = arrayOf("filter_data"))
    var filter_data: List<Filterdata>? = null,

    @field:SerializedName("profiledata")
    @field:JsonField(name = arrayOf("profiledata"))
    val profiledata: Profiledata? = null,

    @field:SerializedName("sublist")
    @field:JsonField(name = arrayOf("sublist"))
    val sublist: ArrayList<SublistItem>? = null,

    @field:SerializedName("states")
    @field:JsonField(name = arrayOf("states"))
    val states: ArrayList<StatesItem?>? = null,

    @field:SerializedName("cities")
    @field:JsonField(name = arrayOf("cities"))
    val cities: List<CitiesItem?>? = null,

    @field:SerializedName("districts")
    @field:JsonField(name = arrayOf("districts"))
    val districts: ArrayList<DistrictsItem?>? = null,

    @field:SerializedName("bill_categories")
    @field:JsonField(name = arrayOf("bill_categories"))
    val billCategories: List<BillCategory?>?,

    @field:SerializedName("shop_categories")
    @field:JsonField(name = arrayOf("shop_categories"))
    val shopCategories: List<ShopCategory?>?,

    @field:SerializedName("shop_brands")
    @field:JsonField(name = arrayOf("shop_brands"))
    val shopBrands: ArrayList<ShopBrandsItem?>? = null,

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
    val productdata: Productdata? = null,

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

    @field:SerializedName("tag_list")
    @field:JsonField(name = arrayOf("tag_list"))
    val taglist: Taglist? = null,

    @field:SerializedName("booklist")
    @field:JsonField(name = arrayOf("booklist"))
    val booklist: List<BooklistItem?>? = null,

    @field:SerializedName("customer_care")
    @field:JsonField(name = arrayOf("customer_care"))
    val customerCare: List<String?>? = null,

    @field:SerializedName("pending_ords")
    @field:JsonField(name = arrayOf("pending_ords"))
    val pendingOrds: List<PendingOrdsItem?>? = null,

    @field:SerializedName("past_ords")
    @field:JsonField(name = arrayOf("past_ords"))
    val pastOrds: List<PastOrdsItem?>? = null,

    @field:SerializedName("max_radius")
    @field:JsonField(name = arrayOf("max_radius"))
    val maxRadius: String = "0",

    @field:SerializedName("book_count")
    @field:JsonField(name = arrayOf("book_count"))
    val book_count: Int = 0,

    @field:SerializedName("search_result")
    @field:JsonField(name = arrayOf("search_result"))
    val searchResult: List<SearchResultItem?>? = null,

    @field:SerializedName("offers")
    @field:JsonField(name = arrayOf("offers"))
    val offers: List<OfferItem>? = null,

    @field:SerializedName("peak_time")
    @field:JsonField(name = arrayOf("peak_time"))
    val peakTime: String? = null,

    @field:SerializedName("order_place")
    @field:JsonField(name = arrayOf("order_place"))
    val orderPlace: String? = null,


    @field:SerializedName("online_payment")
    @field:JsonField(name = arrayOf("online_payment"))
    val online_payment: String? = null,

    @field:SerializedName("cod_payment")
    @field:JsonField(name = arrayOf("cod_payment"))
    val cod_payment: String? = null,

    @field:SerializedName("peak_time_charge")
    @field:JsonField(name = arrayOf("peak_time_charge"))
    val peakTimeCharge: String? = null,

    @field:SerializedName("message")
    @field:JsonField(name = arrayOf("message"))
    val message: String? = null,

    @field:SerializedName("shipping_methods")
    @field:JsonField(name = arrayOf("shipping_methods"))
    val shipping_methods: ArrayList<shippingmethods>? = null,

//    @field:SerializedName("chat")
//    @field:JsonField(name = arrayOf("chat"))
//    val chat: Chat? = null,


    @field:SerializedName("tagstatus")
    @field:JsonField(name = arrayOf("tagstatus"))
    var tagstatus: String? = null,

    @field:SerializedName("tagicon")
    @field:JsonField(name = arrayOf("tagicon"))
    val tagicon: String? = null


)
{
    @SerializedName("product_list")
    @Expose
    private var productList: List<ProductList?>? = null

    @SerializedName("category_list_data")
    @Expose
    private var categoryListData: CategoryListData? = null

    fun getProductList(): List<ProductList?>? {
        return productList
    }

    fun setProductList(productList: List<ProductList?>?) {
        this.productList = productList
    }

    fun getCategoryListData(): CategoryListData? {
        return categoryListData
    }

    fun setCategoryListData(categoryListData: CategoryListData?) {
        this.categoryListData = categoryListData
    }
}

