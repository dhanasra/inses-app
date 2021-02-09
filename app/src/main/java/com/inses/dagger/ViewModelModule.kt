package com.inses.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inses.AppViewModelFactory
import com.inses.ui.auth.AuthVIewModel
import com.inses.ui.auth.create.CreateLoadingViewModel
import com.inses.ui.auth.create.cookie.NameViewModel
import com.inses.ui.auth.create.createAuth.CreateAccountViewModel
import com.inses.ui.auth.create.createPassword.CreatePasswordViewModel
import com.inses.ui.auth.login.loginAuth.LoginAccountFragment
import com.inses.ui.auth.login.loginAuth.LoginAccountViewModel
import com.inses.ui.auth.login.loginPassword.LoginPasswordViewModel
import com.inses.ui.bookings.MyBookingsViewModel
import com.inses.ui.bookings.fragments.HistoryViewModel
import com.inses.ui.bookings.fragments.OnGoingViewModel
import com.inses.ui.dashboard.DashboardViewModel
import com.inses.ui.dashboard.makeService.MakeServiceViewModel
import com.inses.ui.dashboard.makeService.steps.*
import com.inses.ui.home.HomeViewModel
import com.inses.ui.profile.ProfileViewModel
import com.inses.ui.profile.about.AboutUsViewModel
import com.inses.ui.profile.myProfile.MyProfileViewModel
import com.inses.ui.splash.SplashViewModel
import com.inses.ui.support.SupportViewModel
import com.inses.ui.support.detail.CustomerMessageViewModel
import com.inses.ui.support.detail.CustomerSupportViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthVIewModel::class)
    abstract fun bindAuthViewModel(authVIewModel: AuthVIewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateAccountViewModel::class)
    abstract fun bindCreateAccountViewModel(createAccountViewModel: CreateAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginAccountViewModel::class)
    abstract fun bindLoginAccountViewModel(loginAccountViewModel: LoginAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginPasswordViewModel::class)
    abstract fun bindLoginPasswordViewModel(loginPasswordViewModel: LoginPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePasswordViewModel::class)
    abstract fun bindCreatePasswordViewModel(createPasswordViewModel: CreatePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NameViewModel::class)
    abstract fun bindNameViewModel(nameViewModel: NameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateLoadingViewModel::class)
    abstract fun bindCreateLoadingViewModel(createLoadingViewModel: CreateLoadingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MakeServiceViewModel::class)
    abstract fun bindMakeServiceViewModel(makeServiceViewModel: MakeServiceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Step1ViewModel::class)
    abstract fun bindStep1ViewModel(step1ViewModel: Step1ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Step2ViewModel::class)
    abstract fun bindStep2ViewModel(step2ViewModel: Step2ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Step3ViewModel::class)
    abstract fun bindStep3ViewModel(step3ViewModel: Step3ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Step4ViewModel::class)
    abstract fun bindStep4ViewModel(step4ViewModel: Step4ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PriceViewModel::class)
    abstract fun bindPriceViewModel(priceViewModel: PriceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyBookingsViewModel::class)
    abstract fun bindMyBookingsViewModel(myBookingsViewModel: MyBookingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnGoingViewModel::class)
    abstract fun bindOnGoingViewModel(onGoingViewModel: OnGoingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SupportViewModel::class)
    abstract fun bindSupportViewModel(supportViewModel: SupportViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CustomerMessageViewModel::class)
    abstract fun bindCustomerMessageViewModel(customerMessageViewModel: CustomerMessageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CustomerSupportViewModel::class)
    abstract fun bindCustomerSupportViewModel(customerSupportViewModel: CustomerSupportViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel::class)
    abstract fun bindAboutUsViewModel(aboutUsViewModel: AboutUsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyProfileViewModel::class)
    abstract fun bindMyProfileViewModel(myProfileViewModel: MyProfileViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}