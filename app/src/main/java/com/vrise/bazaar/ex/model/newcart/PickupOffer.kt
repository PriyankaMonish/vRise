package com.vrise.bazaar.ex.model.newcart

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class PickupOffer (
    @field:SerializedName("seller_discount")
    @field:JsonField(name = arrayOf("seller_discount"))
    var seller_discount: Int? = null

        )