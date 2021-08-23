package com.vrise.bazaar.ex.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.vrise.R
import com.vrise.bazaar.ex.util.Values.ACTIVITY
import com.vrise.bazaar.ex.util.Values.FRAGMENT

object DirectIt {

    fun navigateTo(
        contextOne: FragmentActivity?,
        type: String,
        backNeeded: Boolean,
        ifFragment: Fragment?,
        ifActivity: Class<*>?,
        bundle: Bundle?,
        flagsForActivity: Array<Int>?,
        allowStateLoss: String
    ) {
        contextOne?.let { context ->
            when (type) {
                ACTIVITY -> {
                    if (ifActivity != null) {
                        val intent = Intent(context, ifActivity)
                        if (bundle != null) {
                            intent.putExtras(bundle)
                        }
                        if (flagsForActivity != null) {
                            for (i in 0 until flagsForActivity.size) {
                                intent.flags = flagsForActivity[i]
                            }
                        }
                        context.startActivity(intent)
                        if (!backNeeded) {
                            context.finish()
                        }
                    }
                }
                FRAGMENT -> {
                    if (ifFragment != null) {
                        if (allowStateLoss.isNotBlank()) {
                            fragmentRedirection(
                                context,
                                ifFragment,
                                backNeeded,
                                bundle,
                                true
                            )
                        } else {
                            fragmentRedirection(
                                context,
                                ifFragment,
                                backNeeded,
                                bundle
                            )
                        }
                    }
                }
            }
        }
    }

    fun fragmentRedirection(contextTwo: FragmentActivity?, ifFragment: Fragment, backNeeded: Boolean, bundle: Bundle?) {
        contextTwo?.let { activity ->
            fragmentRedirection(activity, ifFragment, backNeeded, bundle, false)
        }
    }

    fun navigateToReturn(activity: Activity, REQUEST_CODE: String, Activity: Class<*>?, bundle: Bundle?) {
        if (Activity != null) {
            val intent = Intent(activity, Activity)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            activity.startActivityForResult(intent, REQUEST_CODE.toInt())
        }
    }
    private fun fragmentRedirection(
        activity: FragmentActivity?,
        fragment: Fragment,
        backStatus: Boolean,
        bundle: Bundle?,
        allowStateLoss: Boolean
    ) {
        if (bundle != null) {
            fragment.arguments = bundle
        }
        activity?.let {
            when {
                backStatus -> {
                    if (allowStateLoss) {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).addToBackStack(null).commitAllowingStateLoss()
                    } else {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).addToBackStack(null).commit()
                    }
                }
                else -> {
                    if (allowStateLoss) {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).commitAllowingStateLoss()
                    } else {
                        it.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .replace(R.id.main_container, fragment).commit()
                    }
                }
            }
        }
    }
}
