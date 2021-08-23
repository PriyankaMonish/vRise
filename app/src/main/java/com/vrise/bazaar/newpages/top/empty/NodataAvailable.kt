package com.vrise.bazaar.newpages.top.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vrise.R
import com.vrise.bazaar.newpages.top.temps.Misc.showBlur
import com.vrise.bazaar.newpages.utilities.Constants
import kotlinx.android.synthetic.main.data_there.*

class NodataAvailable : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.data_there, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let {
            showBlur(activity, bundle.getInt(Constants.ID), imageView48)
        }
    }
}