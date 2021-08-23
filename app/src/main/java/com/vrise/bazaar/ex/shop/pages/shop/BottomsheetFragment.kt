package com.vrise.bazaar.ex.shop.pages.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vrise.R
import kotlinx.android.synthetic.main.bootomsheet.*
import kotlinx.android.synthetic.main.shop_fragment.*


class BottomsheetFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bootomsheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initcontroll()
    }

    private fun initcontroll() {
        button28.setOnClickListener {

            requireActivity().onBackPressed()
            bottomSheet.visibility=View.GONE
        }

        textView310.setOnClickListener {
            bottomSheet.visibility=View.GONE

                requireActivity().onBackPressed()
            }

        imageView67.setOnClickListener {
            bottomSheet.visibility=View.GONE

                requireActivity().onBackPressed()
            }

        }
    }

