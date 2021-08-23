package com.vrise.bazaar.ex.shop.dataprovider

import android.content.Context
import android.location.Address
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.vrise.R
import com.vrise.bazaar.ex.model.OfferItem
import com.vrise.bazaar.ex.model.StatusData
import com.vrise.bazaar.ex.model.TrackData
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.mainmodels.Response
import com.vrise.bazaar.ex.model.newmodels.SearchResultItem
import com.vrise.bazaar.ex.model.route.DirectionsResult
import com.vrise.bazaar.ex.model.submodels.*
import com.vrise.bazaar.ex.model.temp.AddressItem
import com.vrise.bazaar.ex.model.temp.Cart
import com.vrise.bazaar.ex.model.temp.Coprocess
import com.vrise.bazaar.ex.model.temp.Wallet
import com.vrise.bazaar.ex.retrofit.RetroCallback
import com.vrise.bazaar.ex.retrofit.RetroService
import com.vrise.bazaar.ex.shop.pages.TrackPageFragment
import com.vrise.bazaar.ex.util.Instances.logoutFromApp
import com.vrise.bazaar.ex.util.Instances.printAny
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.newpages.retrofit.NewGoogleService
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

class RepoLive(val apiService: RetroService?, val context: FragmentActivity?) {

    fun getVersion(request: Request): LiveData<Response> = versionApi(request)

    private fun versionApi(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.getVersion(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = response
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun sendOtp(request: Request): LiveData<Response> = sendOtpApi(request)

    private fun sendOtpApi(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.registerApp(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = response
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun redirect(request: Request): LiveData<Response> = redirectTo(request)

    private fun redirectTo(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.redirectTo(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = response
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun updateNum(request: Request): LiveData<Response> = updateNumber(request)

    private fun updateNumber(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.updateNumber(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = response
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun signmeUp(request: Request): LiveData<Response> = signUp(request)

    private fun signUp(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.signUp(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = response
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }


    fun AddTag(request: Request): LiveData<Response> = addTag(request)

    private fun addTag(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.addTag(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                Toast.makeText(context, display, Toast.LENGTH_SHORT).show()
                logoutFromApp(display, logout, context)

            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    if (data != null) {
                        if (!data.message.isNullOrEmpty()) {
                            resp.value = response
                        } else {
                            resp.value = null
                        }
                    } else {
                        resp.value = null
                    }
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun getProfiles(request: Request): LiveData<Profiledata> = getProfile(request)

    private fun getProfile(request: Request): MutableLiveData<Profiledata> {
        printAny(request)
        val resp = MutableLiveData<Profiledata>()
        apiService?.getProfile(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            if (data != null) {
                                if (data.profile_data != null) {
                                    resp.value = data.profile_data
                                } else {
                                    resp.value = null
                                }
                            } else {
                                resp.value = null
                            }
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun getAllState(): LiveData<ArrayList<StatesItem?>> = getStates()

    private fun getStates(): MutableLiveData<ArrayList<StatesItem?>> {
        val resp = MutableLiveData<ArrayList<StatesItem?>>()
        apiService?.getStates()?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    if (data != null) {
                        if (!data.states.isNullOrEmpty()) {
                            resp.value = data.states
                        } else {
                            resp.value = null
                        }
                    } else {
                        resp.value = null
                    }
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun getDistricts(request: Request): LiveData<ArrayList<DistrictsItem?>> = getDistrictsOfState(
        request
    )

    private fun getDistrictsOfState(request: Request): MutableLiveData<ArrayList<DistrictsItem?>> {
        printAny(request)
        val resp = MutableLiveData<ArrayList<DistrictsItem?>>()
        apiService?.getDistricts(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            if (data != null) {
                                if (!data.districts.isNullOrEmpty()) {
                                    resp.value = data.districts
                                } else {
                                    resp.value = null
                                }
                            } else {
                                resp.value = null
                            }
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun updateProfile(request: Request): LiveData<Response> = updateShopProfile(request)

    private fun updateShopProfile(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.shopProfileUpdate(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = response
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun getDashBoardItems(request: Request): LiveData<Data?> = getDashBoard(request)

    private fun getDashBoard(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.getDashItems(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = data
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }
  fun getSideBar(request: Request): LiveData<Data?> = getSideCategory(request)

    private fun getSideCategory(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.getCategorySideBar(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = data
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun getWallet(request: Request): LiveData<Wallet?> = walletGet(request)

    private fun walletGet(request: Request): MutableLiveData<Wallet?> {
        printAny(request)
        val resp = MutableLiveData<Wallet?>()
        apiService?.getWallet(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = data?.wallet
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun getAddress(request: Request): LiveData<List<AddressItem?>> = getDeliveryAddList(request)

    private fun getDeliveryAddList(request: Request): MutableLiveData<List<AddressItem?>> {
        printAny(request)
        val resp = MutableLiveData<List<AddressItem?>>()
        apiService?.getAddressList(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (state) {
                            resp.value = data?.address
                        } else {
                            resp.value = null
                        }
                    }
                }) {})
        return resp
    }

    fun setAddressDelivery(request: Request): LiveData<Boolean> = setDeliveryAddress(request)

    private fun setDeliveryAddress(request: Request): MutableLiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.setDeliveryAddress(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        if (response?.data?.taglist_change!="" && response?.data?.taglist_change!=null){
                            Toast.makeText(context, response?.data?.taglist_change, Toast.LENGTH_SHORT).show()
                        }
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = false
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        if (response?.data?.taglist_change!="" && response?.data?.taglist_change!=null){
                            Toast.makeText(context, response?.data?.taglist_change, Toast.LENGTH_SHORT).show()
                        }
                        resp.value = state
                    }
                }) {})
        return resp
    }

    fun deleteDeliveryAddress(request: Request): LiveData<Boolean> = deleteDeliver(request)

    private fun deleteDeliver(request: Request): MutableLiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.deleteDeliveryAddress(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = false
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        resp.value = state
                    }
                }) {})
        return resp
    }

    fun getCartData(request: Request): LiveData<Cart> = getCartDataItems(request)

    private fun getCartDataItems(request: Request): MutableLiveData<Cart> {
        printAny(request)
        val resp = MutableLiveData<Cart>()
        apiService?.getCartItem(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {

                        if (display != ""){
                            Toast.makeText(context, display.toString(), Toast.LENGTH_SHORT).show()

                        }
                        logoutFromApp(display, logout, context)


                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                       t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                            printAny(response)

                        if (state) {
                            resp.value = data?.cart
                        } else {
                            resp.value = null

                        }
                    }
                }) {})
        return resp
    }

    fun privateNews(request: Request): LiveData<Data?> = news(request)

    private fun news(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.news(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun getS(request: Request): LiveData<Data?> = getShops(request)

    private fun getShops(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.getShops(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun cartUpdate(request: Request): LiveData<Response> = updatecart(request)

    private fun updatecart(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.cartUpdate(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = null
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        resp.value = response
                    }
                }) {})
        return resp
    }

    fun removeFromCart(request: Request): LiveData<Boolean> = deleteFromCart(request)

    private fun deleteFromCart(request: Request): MutableLiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.deleteCart(request)
                ?.enqueue(object : RetroCallback(object : Interfaces.Callback {
                    override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                        logoutFromApp(display, logout, context)
                    }

                    override fun failed(t: Throwable) {
                        resp.value = false
                        t.printStackTrace()
                    }

                    override fun success(response: Response?, data: Data?, state: Boolean) {
                        printAny(response)
                        resp.value = state
                    }
                }) {})
        return resp
    }

    fun getShopDetails(request: Request, function: (response: Data) -> Unit): LiveData<Data?> = setShopsDetail(
        request,
        function
    )

    private fun setShopsDetail(request: Request, onResult: (response: Data) -> Unit): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.getShopsDetail(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {

                resp.value = null

                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)

                if (state) {
                    resp.value = data
                    onResult(data!!)
                }
            }
        }) {})
        return resp
    }

    fun getItemDetail(request: Request): LiveData<Productdata?> = getDetail(request)

    private fun getDetail(request: Request): MutableLiveData<Productdata?> {
        printAny(request)
        val resp = MutableLiveData<Productdata?>()
        apiService?.getProductDetail(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.productdata
                }
            }
        }) {})
        return resp
    }

    fun getItemList(request: Request): MutableLiveData<Data?> {
        val resp = MutableLiveData<Data?>()
        apiService?.getProductList(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                }
            }
        }) {})
        return resp
    }

    fun getSlotsData(request: Request): LiveData<ProductItemsList?> = getSlots(request)

    private fun getSlots(request: Request): MutableLiveData<ProductItemsList?> {
        printAny(request)
        val resp = MutableLiveData<ProductItemsList?>()
        apiService?.getSlot(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.proData
                }
            }
        }) {})
        return resp
    }

    fun addItem(request: Request): LiveData<Data?> = addToCart(request)

    private fun addToCart(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.addItem(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                /*if (state) {*/
                resp.value = data
                /*} else {
                    resp.value = null
                }*/
            }
        }) {})
        return resp
    }


    fun ShipsItem(request: Request): LiveData<Data?> = Shipping(request)

    private fun Shipping(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.shipItem(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                /*if (state) {*/
                resp.value = data
                /*} else {
                    resp.value = null
                }*/
            }
        }) {})
        return resp
    }










    fun getMyOrder(request: Request): LiveData<Data?> = getMyOrders(request)

    private fun getMyOrders(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.mydata(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }


    fun edit(request: Request): LiveData<Data?> = customerEdit(request)

    private fun customerEdit(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.customeredit(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }


    fun returnEditedProduct(request: Request): LiveData<Data?> = returnProducts(request)

    private fun returnProducts(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.returnproducts(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                if (display!="" && display!=null) {
                    Toast.makeText(context, display, Toast.LENGTH_SHORT).show()
                }
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }


    fun cancel(request: Request): LiveData<Response?> = cancelorderitems(request)

    private fun cancelorderitems(request: Request): MutableLiveData<Response?> {
        printAny(request)
        val resp = MutableLiveData<Response?>()
        apiService?.cancelorderitems(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                if (display!="" && display!=null) {
                    Toast.makeText(context, display, Toast.LENGTH_SHORT).show()
                }

                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {

                    resp.value = response
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun confirm(request: Request): LiveData<Response?> = customerconfirm(request)

    private fun customerconfirm(request: Request): MutableLiveData<Response?> {
        printAny(request)
        val resp = MutableLiveData<Response?>()
        apiService?.customerconfirm(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {

                if (display!="" && display!=null) {
                    Toast.makeText(context, display, Toast.LENGTH_SHORT).show()
                }
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = response
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun reqstOrder(request: Request): LiveData<Data?> = reqstOrd(request)

    private fun reqstOrd(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.requestOrder(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }



    fun saleModel(request: Request): LiveData<Response?> = salemodelorder(request)

    private fun salemodelorder(request: Request): MutableLiveData<Response?> {
        printAny(request)
        val resp = MutableLiveData<Response?>()
        apiService?.modelsale(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = response
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }


    fun getSearchShops(request: Request): LiveData<Data?> = getMySearchShops(request)

    private fun getMySearchShops(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.shopSearch(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }



    fun getCustNum(request: Request): LiveData<List<String?>?> = areyNum(request)

    private fun areyNum(request: Request): MutableLiveData<List<String?>?> {
        printAny(request)
        val resp = MutableLiveData<List<String?>?>()
        apiService?.numberCare(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.customerCare
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun rateApp(request: Request): LiveData<Boolean> = stars(request)

    private fun stars(request: Request): MutableLiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.addRating(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = false
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = state
            }
        }) {})
        return resp
    }

    fun searchShops(request: Request): LiveData<List<SearchResultItem?>?> = shops(request)

    private fun shops(request: Request): MutableLiveData<List<SearchResultItem?>?> {
        printAny(request)
        val resp = MutableLiveData<List<SearchResultItem?>?>()
        apiService?.sSearch(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.searchResult
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun searchItems(request: Request): LiveData<List<SearchResultItem?>?> = item(request)

    private fun item(request: Request): MutableLiveData<List<SearchResultItem?>?> {
        printAny(request)
        val resp = MutableLiveData<List<SearchResultItem?>?>()
        apiService?.pSearch(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.searchResult
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun addAddress(request: Request): LiveData<Boolean> = add(request)

    private fun add(request: Request): MutableLiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.addAddress(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                Toast.makeText(context, display, Toast.LENGTH_SHORT).show()
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = false
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = state
            }
        }) {})
        return resp
    }

    fun sales(request: Request): LiveData<Response> = sale(request)

    private fun sale(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.sale(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = response
            }
        }) {})
        return resp
    }

    fun myOrder(request: Request): LiveData<Response> = myNewOrder(request)
    private fun myNewOrder(request: Request): MutableLiveData<Response> {
        printAny(request)
        val resp = MutableLiveData<Response>()
        apiService?.myorder(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = response
            }
        }) {})
        return resp
    }


//    fun sales(request: Request): LiveData<Boolean> = sale(request)
//
//    private fun sale(request: Request): MutableLiveData<Boolean> {
//        printAny(request)
//        val resp = MutableLiveData<Boolean>()
//        apiService?.sale(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
//            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
//                logoutFromApp(display, logout, context)
//            }
//
//            override fun failed(t: Throwable) {
//                resp.value = false
//                t.printStackTrace()
//            }
//
//            override fun success(response: Response?, data: Data?, state: Boolean) {
//                printAny(response)
//                resp.value = state
//            }
//        }) {})
//        return resp
//    }

    fun checkCoupon(request: Request): LiveData<Coprocess?> = checkCoupons(request)

    private fun checkCoupons(request: Request): MutableLiveData<Coprocess?> {
        printAny(request)
        val resp = MutableLiveData<Coprocess?>()
        apiService?.checkcoupon(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.coprocess
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun getHa(request: HashMap<String, String>): LiveData<Data?> = getHas(request)

    private fun getHas(request: HashMap<String, String>): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.getHas(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun addWallet(request: Request): LiveData<Boolean> = upWal(request)

    private fun upWal(request: Request): LiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.addWale(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = false
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = state
            }
        }) {})
        return resp
    }

    fun setFavs(request: Request): LiveData<Boolean> = fav(request)

    private fun fav(request: Request): MutableLiveData<Boolean> {
        printAny(request)
        val resp = MutableLiveData<Boolean>()
        apiService?.favs(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = false
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = state
            }
        }) {})
        return resp
    }

    fun getReferral(request: Request): LiveData<Data?> = getReferCodes(request)

    private fun getReferCodes(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.referrels(request)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun getOffa(request: Request): LiveData<List<OfferItem?>?> = getasfu(request)

    private fun getasfu(request: Request): MutableLiveData<List<OfferItem?>?> {
        printAny(request)
        val resp = MutableLiveData<List<OfferItem?>?>()
        apiService?.offe(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.offers
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun getFrom(lat: Double, lng: Double, maxResult: Int): LiveData<List<Address>?> = getFrom1(
        lat,
        lng,
        maxResult
    )

    private fun getFrom1(lat: Double, lng: Double, maxResult: Int): MutableLiveData<List<Address>?> {
        val ret = MutableLiveData<List<Address>?>()
        var retList: ArrayList<Address>?
        Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/geocode/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build().create(NewGoogleService::class.java).getAddressList(
                "${lat.toFloat()},${lng.toFloat()}",
                Locale.getDefault().country,
                "true",
//                context?.resources?.getString(R.string.google_api_key) ?: ""
                "AIzaSyAZkH6MkpdJLjEiNXkhLZgmIhXVXl_TCes"
            ).enqueue(object : Callback<com.vrise.bazaar.ex.model.google.Response> {
                override fun onFailure(
                    call: Call<com.vrise.bazaar.ex.model.google.Response>, t: Throwable
                ) {
                    ret.value = null
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<com.vrise.bazaar.ex.model.google.Response>,
                    response: retrofit2.Response<com.vrise.bazaar.ex.model.google.Response>
                ) {
                    try {
                        if (response.isSuccessful) {
                            retList = ArrayList()
                            if ("OK".equals(response.body()?.status, ignoreCase = true)) {
                                val results = response.body()?.results
                                for (i in 0 until (results?.size ?: 0)) {
                                    val indiStr = results?.get(i)?.formattedAddress
                                    retList?.add(Address(Locale.ENGLISH).apply {
                                        setAddressLine(0, indiStr)
                                    })
                                }
                            } else {
                                logoutFromApp(
                                    response.body()?.error_message
                                        ?: "No location", false, context
                                )
                            }
                            ret.value = if (retList.isNullOrEmpty()) {
                                null
                            } else {
                                retList
                            }
                        } else {
                            ret.value = null
                        }

                    } catch (e: IOException) {
                        ret.value = null
                    } catch (e: JSONException) {
                        ret.value = null
                    } catch (e: Exception) {
                        ret.value = null
                    }
                }
            })
        /*
                val address = String.format(
                    Locale.ENGLISH,
                    "http://maps.googleapis.com/maps/api/geocode/json?latlng=%1\$f,%2\$f&sensor=true&language=" + Locale.getDefault().country,
                    lat,
                    lng
                )


                val stringBuilder = StringBuilder()
        */
        /*
                val url = URL(address)
                val urlConnection = url.openConnection() as HttpURLConnection

                    val responses = BufferedInputStream(urlConnection.inputStream)
                    var line = 0
                    while ({ line = responses.read(); line }() != -1) {
                        stringBuilder.append(line)
                    }

                 finally {
                    urlConnection.disconnect()
                }
        */
        return ret
    }

    fun trackorder(nothing: Request): LiveData<TrackData?> {
        printAny(nothing)
        val resp = MutableLiveData<TrackData?>()
        apiService?.track(nothing)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data?.trackData
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun oStatus(nothing: Request): LiveData<StatusData?>? {
        printAny(nothing)
        val respVal = MutableLiveData<StatusData?>()
        apiService?.oStatus(nothing)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                t.printStackTrace()
                respVal.value = null
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) respVal.value = data?.statusData else respVal.value = null
            }
        }) {})
        return respVal
    }

    fun dboylocation(nothing: Request): LiveData<Data?>? {
        printAny(nothing)
        val resp = MutableLiveData<Data?>()
        apiService?.dboylocation(nothing)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun canplaceorder(nothing: Request): LiveData<Data?> = canplaceorder2(nothing)

    private fun canplaceorder2(nothing: Request): MutableLiveData<Data?> {
        printAny(nothing)
        val resp = MutableLiveData<Data?>()
        apiService?.canplaceorder(nothing)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data ?: Data(
                        billCategories = null,
                        shopCategories = null,
                        storeRadius = null
                    )
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun cancelorder(nothing: Request): LiveData<Data?> = cancelorders1(nothing)

    private fun cancelorders1(nothing: Request): MutableLiveData<Data?> {
        printAny(nothing)
        val resp = MutableLiveData<Data?>()
        apiService?.cancelorder(nothing)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data ?: Data(
                        billCategories = null,
                        shopCategories = null,
                        storeRadius = null
                    )
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun reorder(nothing: Request): LiveData<Response?> = reorder1(nothing)

    private fun reorder1(nothing: Request): MutableLiveData<Response?> {
        printAny(nothing)
        val resp = MutableLiveData<Response?>()
        apiService?.reorder(nothing)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                if (display!= "" && display!= null && response?.data?.shopChange!=1){
                    Toast.makeText(context, response?.display.toString(), Toast.LENGTH_SHORT).show()

                }
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
//                if (response?.display!= "" && response?.display!= null){
//                    Toast.makeText(context, response.display.toString(), Toast.LENGTH_SHORT).show()
//
//                }
                printAny(response)
                resp.value = response
            }
        }) {})
        return resp
    }

    fun dbchat(request: Request): LiveData<Data?> = dbchat1(request)

    private fun dbchat1(request: Request): MutableLiveData<Data?> {
        printAny(request)
        val resp = MutableLiveData<Data?>()
        apiService?.dbchats(request)?.enqueue(object : RetroCallback(object : Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                if (state) {
                    resp.value = data
                } else {
                    resp.value = null
                }
            }
        }) {})
        return resp
    }

    fun addNewMessage(nothing: Request): LiveData<Boolean?>? = addAMess(nothing)

    private fun addAMess(nothing: Request): MutableLiveData<Boolean?>? {
        printAny(nothing)
        val resp = MutableLiveData<Boolean?>()
        apiService?.addNewMessage(nothing)?.enqueue(object : RetroCallback(object :
            Interfaces.Callback {
            override fun additionalData(display: String?, logout: Boolean,response: Response?) {
                logoutFromApp(display, logout, context)
            }

            override fun failed(t: Throwable) {
                resp.value = null
                t.printStackTrace()
            }

            override fun success(response: Response?, data: Data?, state: Boolean) {
                printAny(response)
                resp.value = state

            }
        }) {})
        return resp
    }

    fun refreshTitle(
        origin: String,
        destination: String,
        key: String,
        directions: TrackPageFragment.GoogleService?
    ): MutableLiveData<DirectionsResult?>? {
        printAny(origin)
        val resp = MutableLiveData<DirectionsResult?>()
        directions?.getDirections(origin, destination, key)?.enqueue(object :
            Callback<DirectionsResult> {
            override fun onFailure(call: Call<DirectionsResult>, t: Throwable) {
                resp.postValue(null)
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<DirectionsResult>,
                response: retrofit2.Response<DirectionsResult>
            ) {
                resp.postValue(response.body())
            }
        })
        return resp
    }

    companion object {
        @Volatile
        private var instanceOf: RepoLive? = null

        fun getInstance(apiService: RetroService?, activity: FragmentActivity?) =
                instanceOf ?: synchronized(this) {
                    instanceOf ?: RepoLive(apiService, activity).also {
                        instanceOf = it
                    }
                }
    }
}
