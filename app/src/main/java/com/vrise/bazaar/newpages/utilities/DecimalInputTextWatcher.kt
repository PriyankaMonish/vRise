package com.vrise.bazaar.newpages.utilities

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.regex.Pattern

class DecimalInputTextWatcher(private val mEditText: EditText, private val mDigitsAfterZero: Int) : TextWatcher {

    private var mPreviousValue: String? = null
    private var mCursorPosition: Int = 0
    private var mRestoringPreviousValueFlag: Boolean = false

    init {
        mPreviousValue = ""
        mRestoringPreviousValueFlag = false
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (!mRestoringPreviousValueFlag) {
            mPreviousValue = s.toString()
            mCursorPosition = mEditText.selectionStart
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (!mRestoringPreviousValueFlag) {

            if (!isValid(s.toString())) {
                mRestoringPreviousValueFlag = true
                restorePreviousValue()
            }

        } else {
            mRestoringPreviousValueFlag = false
        }
    }

    private fun restorePreviousValue() {
        mEditText.setText(mPreviousValue)
        mEditText.setSelection(mCursorPosition)
    }

    private fun isValid(s: String): Boolean {
      val patternWithDot = Pattern.compile("[0-9]*((\\.[0-9]{0,$mDigitsAfterZero})?)||(\\.)?")
      val patternWithComma = Pattern.compile("[0-9]*((,[0-9]{0,$mDigitsAfterZero})?)||(,)?")
        val matcherDot = patternWithDot.matcher(s)
        val matcherComa = patternWithComma.matcher(s)

        return matcherDot.matches() || matcherComa.matches()
    }
}