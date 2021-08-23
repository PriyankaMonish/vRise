package com.vrise.bazaar.newpages.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Orderdata {

    @SerializedName("upi_transactionid")
    @Expose
    var upi_transactionid: String? = null

    @SerializedName("payment_method")
    @Expose
    var payment_method: String? = null

}