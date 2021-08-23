package com.vrise.bazaar.ex.shop.dataprovider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vrise.bazaar.ex.shop.ShopDetailBViewModel
import com.vrise.bazaar.ex.shop.interfaces.*
import com.vrise.bazaar.ex.shop.pages.TrackPageViewModel
//import com.vrise.bazaar.ex.shop.pages.chat.ChatViewModel
import com.vrise.bazaar.ex.shop.pages.main.OrderShowViewModel

class ViewModelFactory(val repository: RepoLive?) : ViewModelProvider.NewInstanceFactory() {
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
		when {
			isAssignableFrom(ItemViewModel::class.java) -> ItemViewModel(repository)
			isAssignableFrom(OfferListViewModel::class.java) -> OfferListViewModel(repository)
			isAssignableFrom(PayWalletViewModel::class.java) -> PayWalletViewModel(repository)
			isAssignableFrom(PageViewModel::class.java) -> PageViewModel(repository)
			isAssignableFrom(FinderViewModel::class.java) -> FinderViewModel(repository)
			isAssignableFrom(ReferralViewModel::class.java) -> ReferralViewModel(repository)
			isAssignableFrom(AddressListViewModel::class.java) -> AddressListViewModel(repository)
			isAssignableFrom(OutViewModel::class.java) -> OutViewModel(repository)
			isAssignableFrom(CartViewModel::class.java) -> CartViewModel(repository)
			isAssignableFrom(StoreViewModel::class.java) -> StoreViewModel(repository)
			isAssignableFrom(OrdersViewModel::class.java) -> OrdersViewModel(repository)
			isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository)
			isAssignableFrom(WalletViewModel::class.java) -> WalletViewModel(repository)
			isAssignableFrom(ShopDetailViewModel::class.java) -> ShopDetailViewModel(repository)
			isAssignableFrom(ShopsViewModel::class.java) -> ShopsViewModel(repository)
			isAssignableFrom(ItemDetailViewModel::class.java) -> ItemDetailViewModel(repository)
			isAssignableFrom(SplashPageViewModel::class.java) -> SplashPageViewModel(repository)
			isAssignableFrom(OtpViewModel::class.java) -> OtpViewModel(repository)
			isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(repository)
			isAssignableFrom(MobileViewModel::class.java) -> MobileViewModel(repository)
			isAssignableFrom(ShopDetailBViewModel::class.java) -> ShopDetailBViewModel(repository)
			isAssignableFrom(TrackPageViewModel::class.java) -> TrackPageViewModel(repository)
			isAssignableFrom(DashBoardViewModel::class.java) -> DashBoardViewModel(repository)
			isAssignableFrom(OrderShowViewModel::class.java) -> OrderShowViewModel(repository)
//			isAssignableFrom(ChatViewModel::class.java) -> ChatViewModel(repository)
			else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
		}
	} as T
}