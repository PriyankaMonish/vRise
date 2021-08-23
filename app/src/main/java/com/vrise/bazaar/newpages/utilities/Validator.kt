package com.vrise.bazaar.newpages.utilities

import android.widget.EditText
import android.widget.TextView
import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.annotations.SerializedName
import java.util.regex.Pattern


object Validator {

//    ^[+]?[0-9]{10,13}$


    fun hasText(editText: EditText): Boolean {
        return hasText(editText, "")
    }

    fun hasText(editText: TextView): Boolean {
        return hasText(editText, "")
    }

    fun hasText(editText: Array<TextInputEditText?>, commonerror: String): Boolean {
        val boolean: ArrayList<Boolean> = ArrayList()
        for (i in 0 until editText.size) {
            boolean.add(hasText(editText[i], commonerror))
        }
        return !boolean.contains(false)
    }

    fun hasText(editText: Array<TextInputEditText?>): Boolean {
        return hasText(editText, "Required")
    }

    fun hasTexT(editText: Array<TextInputEditText>): Boolean {
        return hasTexT(editText, "Required")
    }


    fun hasTexT(editText: Array<TextInputEditText>, commonerror: String): Boolean {
        val boolean: ArrayList<Boolean> = ArrayList()
        for (i in 0 until editText.size) {
            boolean.add(hasText(editText[i], commonerror))
        }
        return !boolean.contains(false)
    }

    fun hasText(editText: Array<Main>): Boolean {
        return hasText(editText, "Required")
    }

    fun hasText(editText: Array<Main>, commonerror: String): Boolean {
        val boolean: ArrayList<Boolean> = ArrayList()
        for (i in 0 until editText.size) {
            when (isText) {
                /*editText[i].paymentData == isEmail -> boolean.add(isEmail(editText[i].payment))
                    editText[i].paymentData == isPhone -> boolean.add(isPhone(editText[i].payment))*/
                editText[i].paymentData -> boolean.add(hasText(editText[i].payment, commonerror))
                else -> boolean.add(hasText(editText[i].payment, commonerror))
            }
        }
        return !boolean.contains(false)
    }

    data class Main(

        @field:SerializedName("payment")
        @field:JsonField(name = arrayOf("payment"))
        val payment: TextInputEditText? = null,

        @field:SerializedName("payment_data")
        @field:JsonField(name = arrayOf("payment_data"))
        val paymentData: String? = null
    )

    val isPhone = "isPhone"
    val isEmail = "isPhone"
    val isText = "isText"
    val isEmpty = "isEmpty"

    fun hasText(editText: TextInputEditText?, error: String): Boolean {
        if (editText != null) {
            val text = editText.text.toString().trim { it <= ' ' }
            editText.error = null
            if (text.isEmpty()) {
                if (error.isBlank()) {
                    editText.error = "Required"
                }
                editText.error = error
                return false
            }
        } else {
            return false
        }
        return true
    }

/*fun TextInputEditText.hasText(): Boolean {
    return hasText(this, "Required")
}*/

    fun EditText.hasText(error: String): Boolean {
        return hasText(this, error)
    }

    fun isEmail(editText: TextInputEditText?): Boolean {
        return if (editText != null) {
            if (editText.isEmail()) {
                true
            } else {
                editText.error = "invalid"
                false
            }
        } else {
            false
        }
    }

    fun EditText.isEmail(): Boolean {
        return if (hasText(this, "Required")) {
            isEmail(this.text.toString())
        } else {
            false
        }
    }

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private fun isEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        /*return EMAIL_ADDRESS_PATTERN.matcher(email).matches()*/
    }

    fun isValidLength(editText: TextInputEditText, length: Int): Boolean {
        return if (hasText(editText)) {
            lengthValidation(editText, length)
        } else {
            false
        }
    }

    private fun lengthValidation(editText: TextInputEditText?, length1: Int): Boolean {
        val validation: Boolean
        if (editText != null) {
            val text = editText.text.toString().trim { it <= ' ' }
            editText.error = null
            if (text.length != length1) {
                editText.error = "invalid"
                validation = false
            } else {
                validation = true
            }
        } else {
            validation = false
        }
        return validation
    }

    fun hasText(editText: TextView?, error: String): Boolean {
        if (editText != null) {
            val text = editText.text.toString().trim { it <= ' ' }
            editText.error = null
            if (text.isEmpty()) {
                editText.error = error
                return false
            }
            return true
        } else {
            return false
        }
    }

    fun isSame(editTextA: EditText, editTextB: EditText): Boolean {
        val text1 = editTextA.text.toString().trim { it <= ' ' }
        val text3 = editTextB.text.toString().trim { it <= ' ' }
        editTextB.error = null
        if (text1 != text3) {
            editTextB.error = "mismatched data"
            return false
        }
        return true
    }

    fun isPhone(phone: TextInputEditText?): Boolean {
        return if (phone != null) {
            if (phone.isPhone()) {
                phone.error = null
                true
            } else {
                phone.error = "invalid"
                false
            }
        } else {
            false
        }
    }

//getString(R.string.phone_number_needed)

    fun EditText.isPhone(): Boolean {
        return if (hasText(this, "Phone Number Required")) {
            this.text.toString().trim().isPhoneNumber()
        } else {
            false
        }
    }

    private fun String.isPhoneNumber() = length in 4..10 && all { it.isDigit() }
}