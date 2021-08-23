package com.vrise.bazaar.ex.retrofit

import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.mainmodels.Response
import com.vrise.bazaar.ex.util.Values.CARTSMODULE
import com.vrise.bazaar.ex.util.Values.CUSTMERMODULE
import com.vrise.bazaar.ex.util.Values.RETSIGERMODULE
import com.vrise.bazaar.ex.util.Values.SALESMODULE
import com.vrise.bazaar.ex.util.Values.SHOPPINGMODULE
import com.vrise.bazaar.ex.util.Values.SHOPPINGMODULEOLD
import com.vrise.bazaar.ex.util.Values.USERMODULE
import com.vrise.bazaar.newpages.utilities.Constants.PAYMENTMODULE
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit Service class for Shopping
 */
interface RetroService {

	@POST("$RETSIGERMODULE/version")
	fun getVersion(@Body request: Request): Call<Response>

	@POST("$RETSIGERMODULE/mobile")
	fun registerApp(@Body request: Request): Call<Response>

	@POST("$RETSIGERMODULE/mobileotp")
	fun redirectTo(@Body request: Request): Call<Response>

	@POST("$USERMODULE/mobileupdate")
	fun updateNumber(@Body request: Request): Call<Response>

	@POST("$PAYMENTMODULE/getHashes")
	fun getHashes(@Body request: HashMap<String, String>): Call<Response>

	@POST("$RETSIGERMODULE/signup")
	fun signUp(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/addtag")
	fun addTag(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/profile")
	fun getProfile(@Body request: Request): Call<Response>

	@POST("$USERMODULE/states")
	fun getStates(): Call<Response>

	@POST("$RETSIGERMODULE/districts")
	fun getDistricts(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/profileupdate")
	fun shopProfileUpdate(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/dashbord")
	fun getDashItems(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/categorysidebar")
	fun getCategorySideBar(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/addresslist")
	fun getAddressList(@Body request: Request): Call<Response>

	@POST("$RETSIGERMODULE/news")
	fun news(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/wallet")
	fun getWallet(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/defaultaddress")
	fun setDeliveryAddress(@Body request: Request): Call<Response>

	@POST("$CARTSMODULE/newcartlist")
	fun getCartItem(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/addressdelete")
	fun deleteDeliveryAddress(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/shoplist")
	fun getShops(@Body request: Request): Call<Response>

	@POST("$CARTSMODULE/updatecart")
	fun cartUpdate(@Body request: Request): Call<Response>

	@POST("$CARTSMODULE/deletecart")
	fun deleteCart(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/shop")
	fun getShopsDetail(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULEOLD/productview")
	fun getProductDetail(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/productlist")
	fun getProductList(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULEOLD/productdata")
	fun getSlot(@Body request: Request): Call<Response>

	@POST("$CARTSMODULE/addcart")
	fun addItem(@Body request: Request): Call<Response>

	@POST("$CARTSMODULE/shippingmethod")
	fun shipItem(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/reorder")
	fun reorder(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/custcare")
	fun numberCare(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/orderlist")
	fun mydata(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/customeredit")
	fun customeredit(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/returnproducts")
	fun returnproducts(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/cancelorderitems")
	fun cancelorderitems(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/customerconfirm")
	fun customerconfirm(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/request_order")
	fun requestOrder(@Body request: Request): Call<Response>

	@POST("$SALESMODULE/model_sale")
	fun modelsale(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/addrating")
	fun addRating(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/search")
	fun sSearch(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/searchshopview")
	fun shopSearch(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/searchproduct")
	fun pSearch(@Body request: Request): Call<Response>

	@POST("$SALESMODULE/sale")
	fun sale(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/modelorderpage")
	fun myorder(@Body request: Request): Call<Response>

	@POST("$SALESMODULE/couponcheck")
	fun checkcoupon(@Body request: Request): Call<Response>

	@POST("$SALESMODULE/cancelorder")
	fun cancelorder(@Body request: Request): Call<Response>

	@POST("$SALESMODULE/placeorder")
	fun canplaceorder(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULEOLD/addaddress")
	fun addAddress(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULEOLD/favprocess")
	fun favs(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/topupwallet")
	fun addWale(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/offers")
	fun offe(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/trackorder")
	fun track(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/dboylocation")
	fun dboylocation(@Body request: Request): Call<Response>

	@POST("$CUSTMERMODULE/refercode")
	fun referrels(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/addmessage")
	fun addNewMessage(@Body request: Request): Call<Response>

	@POST("$PAYMENTMODULE/getHashes")
	fun getHas(@Body request: HashMap<String, String>): Call<Response>

	@POST("$SHOPPINGMODULE/orderstatus")
	fun oStatus(@Body request: Request): Call<Response>

	@POST("$SHOPPINGMODULE/dbchat")
	fun dbchats(@Body request: Request): Call<Response>

}