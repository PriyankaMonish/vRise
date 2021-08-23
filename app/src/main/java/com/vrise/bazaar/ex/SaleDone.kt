package com.vrise.bazaar.ex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.vrise.R
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.pages.main.DashBoardFragment
import com.vrise.bazaar.ex.shop.pages.shop.ItemFragment
import com.vrise.bazaar.ex.util.Constants.SECOND
import kotlinx.android.synthetic.main.fragment_done.*

class SaleDone : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_done, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		if(arguments?.getString(SECOND).toString() == "Your Payment was successfully completed"){
			textView216?.text = "Sale Done"
		}
		textView218.text = arguments?.getString(SECOND).toString()

		button18.setOnClickListener {
			if (activity is BazaarContainer) {
				it.findNavController().navigate(R.id.action_saleDone_to_homeFragment)
				requireFragmentManager().popBackStackImmediate()

			} else if (activity is DashBoardContainer) {
			it.findNavController().navigate(R.id.action_saleDone_to_homeFragment2)
				requireFragmentManager().popBackStackImmediate()
			}
		}
	}
}
