package com.vrise.bazaar.ex.util
import com.vrise.bazaar.ex.model.mainmodels.Response

import com.vrise.bazaar.ex.model.submodels.Data

class Interfaces{

    interface Callback {
        fun additionalData(display : String?, logout: Boolean,response: Response?)
        fun failed(t: Throwable)
        fun success(response: Response?, data: Data?, state: Boolean)
    }
    interface ReturnData {
        fun getValue(key : String, value: String)
    }
    interface ReturnPos {
        fun getValue(position : String)
    }
    interface ReturnAnyWithKey {
        fun getValue(key : String, value: Any)
    }
    interface ReturnText {
        fun getValue(textValue: String)
    }
    interface ReturnAny {
        fun getValue(values: Any?)
    }

}