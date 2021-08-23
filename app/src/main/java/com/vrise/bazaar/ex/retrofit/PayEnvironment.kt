package com.vrise.bazaar.ex.retrofit

import com.vrise.BuildConfig
import com.payu.india.Payu.PayuConstants
import kotlin.String as String1

/**
 * App Flavours
 * STAGING   - App in test environment
 * DEMO 	 - App in test environment
 * MIXY 	 - App in test environment with live payment gateway
 * LIVE	 	 - App in live environment
 */
enum class PayEnvironment {
	STAGING {
		override fun surl(): String1 {
			return "https://payuresponse.firebaseapp.com/success"
		}

		override fun furl(): String1 {
			return "https://payuresponse.firebaseapp.com/failure"
		}

		override fun gatewayEnviornment(): Int {
			return PayuConstants.STAGING_ENV
		}

		override fun merchantPay(): String1 {
		return "tXjTgO"
//			return "gtKFFx" ///"CWVmYL"  salt "CXDUTqMT"
		}

		override fun basUrl(): String1 {
			return "https://estrradoweb.com/vrise/api/ "

//			return "https://estrradodemo.com/needtoday/api/"
		}

		override fun imageUrl(): String1 {
			return "https://ibrbazaar.com/test/uploads"
		}
	},
	DEMO {
		override fun surl(): String1 {
			return "https://payuresponse.firebaseapp.com/success"
		}

		override fun furl(): String1 {
			return "https://payuresponse.firebaseapp.com/failure"
		}

		override fun gatewayEnviornment(): Int {
			return PayuConstants.STAGING_ENV
		}

		override fun merchantPay(): String1 {
			return "gtKFFx"
		}

		override fun basUrl(): String1 {
			return "https://estrradoweb.com/vrise/test/api/"
		}

		override fun imageUrl(): String1 {
			return "http://ariesestrrado.com/ibrdemo/uploads/"
		}
	},
	LIVE {
		override fun surl(): String1 {
			return basUrl() + "payumoney/payResponse/success"
		}

		override fun furl(): String1 {
			return basUrl() + "payumoney/payResponse/failure"
		}

		override fun gatewayEnviornment(): Int {
			return PayuConstants.PRODUCTION_ENV
		}

		override fun merchantPay(): String1 {
			return BuildConfig.MerchantKey
		}

		override fun basUrl(): String1 {
			return "http://viaronline.com/api/"
		}

		override fun imageUrl(): String1 {
			return "https://ibrbazaar.com/uploads/"
		}
	},
	MIXY {
		override fun surl(): String1 {
			return basUrl() + "payumoney/payResponse/success"
		}

		override fun furl(): String1 {
			return basUrl() + "payumoney/payResponse/failure"
		}

		override fun gatewayEnviornment(): Int {
			return PayuConstants.PRODUCTION_ENV
		}

		override fun merchantPay(): String1 {
			return BuildConfig.MerchantKey
		}

		override fun basUrl(): String1 {
			return "https://ibrbazaar.com/test/api/"
		}

		override fun imageUrl(): String1 {
			return "https://ibrbazaar.com/test/uploads"
		}
	};

	abstract fun basUrl(): String1
	abstract fun imageUrl(): String1
	abstract fun merchantPay(): String1
	abstract fun gatewayEnviornment(): Int
	abstract fun surl(): String1
	abstract fun furl(): String1
}