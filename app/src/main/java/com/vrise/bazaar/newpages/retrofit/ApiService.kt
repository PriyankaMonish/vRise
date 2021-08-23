package com.vrise.bazaar.newpages.retrofit

import com.vrise.bazaar.newpages.utilities.Constants.AGENTMODULE
import com.vrise.bazaar.newpages.utilities.Constants.CARTSMODULE
import com.vrise.bazaar.newpages.utilities.Constants.CUSTMERMODULE
import com.vrise.bazaar.newpages.utilities.Constants.PAYMENTMODULE
import com.vrise.bazaar.newpages.utilities.Constants.RETSIGERMODULE
import com.vrise.bazaar.newpages.utilities.Constants.SALESMODULE
import com.vrise.bazaar.newpages.utilities.Constants.SHAPPINMODULE
import com.vrise.bazaar.newpages.utilities.Constants.SUBSCRIBERMODULE
import com.vrise.bazaar.newpages.utilities.Constants.USERMODULE
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Api Service Class For Bill Payment
 */
interface ApiService {

    @POST("$RETSIGERMODULE/version")
    fun getVersion(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/sellerlist")
    fun getSellerList(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/resendotp")
    fun mobileValidate(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/mobile")
    fun registerMobileNumber(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/mobileotp")
    fun redirectHim(@Body request: Request): Call<Response>

    @POST("$USERMODULE/agentdata")
    fun getAgentDetails(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/signup")
    fun signUp(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/news")
    fun news(@Body request: Request): Call<Response>

    @POST("$USERMODULE/marketingcode")
    fun postMarketingCode(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/newsusertype")
    fun newsUserTypes(@Body request: Request): Call<Response>

    @POST("$USERMODULE/states")
    fun getStates(): Call<Response>

    @POST("$USERMODULE/mobileupdate")
    fun mobileupdate(@Body request: Request): Call<Response>

    @POST("$USERMODULE/cities")
    fun getCities(@Body request: Request): Call<Response>

    @POST("$RETSIGERMODULE/districts")
    fun getDistricts(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/dashbord")
    fun getShoppingDashboard(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/preferprocess")
    fun preFerProcess(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/prefertlist")
    fun preFerlist(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/addaddress")
    fun addAddress(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/dashborddata")
    fun getDashboardData(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/subcategorylist")
    fun getSubCats(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/preproductlist")
    fun preProducts(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/categorylist")
    fun getCat(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/productlist")
    fun getListProduct(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/favprocess")
    fun favouriteIt(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/productview")
    fun getProductDetail(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/favlist")
    fun getfavourites(@Body request: Request): Call<Response>

    @POST("$SHAPPINMODULE/productdata")
    fun product(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/clearnotification")
    fun clearnotification(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/language")
    fun setLanguag(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/addresslist")
    fun addresslist(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/updateaddress")
    fun updateaddress(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/defaultaddress")
    fun defaultaddress(@Body request: Request): Call<Response>

    @POST("$SALESMODULE/sale")
    fun sale(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/notification")
    fun getShopNotifications(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/viewnotification")
    fun viewnotification(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/profileupdate")
    fun shopProfileUpdate(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/addressdelete")
    fun addressdelete(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/wallet")
    fun walletget(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/profile")
    fun profileGet(@Body request: Request): Call<Response>

    @POST("$CARTSMODULE/deletecart")
    fun cartel(@Body request: Request): Call<Response>

    @POST("$CARTSMODULE/updatecart")
    fun updatecart(@Body request: Request): Call<Response>

    @POST("$CARTSMODULE/addcart")
    fun addToCart(@Body request: Request): Call<Response>

    @POST("$CARTSMODULE/newcartlist")
    fun getCartData(@Body request: Request): Call<Response>

    @POST("$CARTSMODULE/addbooking")
    fun add_booking(@Body request: Request): Call<Response>

    @POST("$CARTSMODULE/bookinglist")
    fun bookinglist(@Body request: Request): Call<Response>

    @POST("$PAYMENTMODULE/getHashes")
    fun getHashes(@Body request: HashMap<String, String>): Call<Response>

    @POST("$SUBSCRIBERMODULE/dashboard")
    fun getSubscriberDash(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/category")
    fun getSubscriberCategory(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/languages")
    fun getSubscriberLanguage(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/profile")
    fun getSubscriberProfile(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/profileupdate")
    fun updateSubscriberProfile(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/product")
    fun getSubscriberProducts(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/productadd")
    fun subsproductadd(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/billlist")
    fun subsBillList(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/receipts")
    fun receipts(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/mailreceipts")
    fun mailreceipts(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/notification")
    fun notification(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/productremove")
    fun productremove(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/viewnotification")
    fun read(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/billpayment")
    fun billPayment(@Body request: Request): Call<Response>

    @POST("$SUBSCRIBERMODULE/clearnotification")
    fun clearSubscriberNotification(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/dashboard")
    fun getAgentDashBoard(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/routeadd")
    fun addRoute(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/routelist")
    fun getRoute(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberadd")
    fun addSubscriber(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberlist")
    fun getSubscriberList(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberdata")
    fun getSubscriberDetails(@Body request: Request): Call<Response>

    @POST("$CUSTMERMODULE/refercode")
    fun referrelcode(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/category")
    fun getCategory(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/languages")
    fun getLanguage(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/productadd")
    fun addAgentProductPrice(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/product")
    fun getProducts(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subproductadd")
    fun SubProductAdd(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/profile")
    fun getAgentProfile(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/profileupdate")
    fun profileupdate(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/acceptproductreq")
    fun acceptProductReqs(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberprocess")
    fun subscriberProcess(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberrequest")
    fun subscriberRequestList(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/routechange")
    fun changeRoute(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/bankadd")
    fun addBankDetails(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/bank")
    fun getBankDetail(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/autobillstatus")
    fun setAutoBill(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/billsettingnew")
    fun getBillSett(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/billlist")
    fun getBillList(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/billedit")
    fun editBills(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/duelist")
    fun duelist(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberduelist")
    fun subscriberduelist(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/newlyadded")
    fun newlyadded(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberaddlist")
    fun subscriberaddlist(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/settleupview")
    fun settleupview(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/processproductreq")
    fun processproductreq(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/settleup")
    fun settleup(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/dueremind")
    fun dueremind(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/notification")
    fun agentnotification(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/viewnotification")
    fun agentread(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberdelete")
    fun subscriberdelete(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberremove")
    fun subscriberremove(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subscriberupdate")
    fun subscriberupdate(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/clearnotification")
    fun clearNotificationAgent(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/smspacks")
    fun smsPacks(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/billproductdelete")
    fun deletebillproduct(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/autosmsstatus")
    fun autosmsstatus(@Body request: Request): Call<Response>

    @POST("$AGENTMODULE/subdueremind")
    fun subdueremind(@Body request: Request): Call<Response>

    @POST("$SALESMODULE/couponcheck")
    fun checkcoupon(@Body request: Request): Call<Response>

    @POST("$SALESMODULE/rating")
    fun rate(@Body request: Request): Call<Response>

    @POST("$SALESMODULE/myorders")
    fun mydata(@Body request: Request): Call<Response>

}