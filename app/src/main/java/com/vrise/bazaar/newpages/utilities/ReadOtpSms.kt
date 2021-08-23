package com.vrise.bazaar.newpages.utilities


class ReadOtpSms /*: BroadcastReceiver() */{

    /*override fun onReceive(context: Context?, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                    Log.d("TAG", message)
                    smsReceiver?.smsReceived(message)
                }
                CommonStatusCodes.TIMEOUT -> {
                    Log.d("TAG", "Otp timed out")
                    smsReceiver?.smsNo()
                }
            }
        }
    }

    private var smsReceiver: SmsListener? = null

    fun init(smsReceiver: SmsListener?) {
        this.smsReceiver = smsReceiver
    }

    interface SmsListener {
        fun smsReceived(sms: String)
        fun smsNo()
    }*/

}