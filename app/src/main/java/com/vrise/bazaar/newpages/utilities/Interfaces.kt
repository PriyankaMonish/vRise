package com.vrise.bazaar.newpages.utilities

import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.*

class Interfaces {
    interface FailedInvoke{
        fun invoke()
    }
    interface Invoke {
        fun invokeMe()
    }

    interface ReturnId {
        fun returnId(string: String)
    }

    interface returnBundle {
        fun returnBundle(any : Any)
    }

    interface ReturnNamenId {
        fun returnIdName(name: String, id: String)
    }

    interface ClickedItem {
        fun returnIdValue(clickPosPosition: String, clickPosvalue: String)
    }

    interface ReturnPrdlistItem {
        fun returnData(clickPosvalue: PrdlistItem?)
        fun removeData(clickPosvalue: PrdlistItem?)
    }

    interface ReturnSubscriptionsItem {
        fun removeData(clickPosvalue: SubscriptionsItem)
    }

    interface ReturnBillProductsItem {
        fun addData(clickPosvalue: BillProductsItem)
        fun removeData(clickPosvalue: BillProductsItem)
    }

    interface Callback {
        fun additionalData(display : String?, logout: Boolean)
        fun failed(t: Throwable)
        fun success(response: Response?, data: Data?, state: Boolean)
    }

}