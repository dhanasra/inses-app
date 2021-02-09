package com.inses.dagger.builder

import com.inses.ui.auth.AuthActivity
import com.inses.ui.auth.AuthFragmentProvider
import com.inses.ui.dashboard.makeService.MakeServiceActivity
import com.inses.ui.dashboard.makeService.steps.BookingSuccessActivity
import com.inses.ui.home.HomeActivity
import com.inses.ui.home.HomeFragmentProvider
import com.inses.ui.profile.about.AboutUsActivity
import com.inses.ui.profile.myProfile.MyProfileActivity
import com.inses.ui.splash.SplashActivity
import com.inses.ui.support.detail.CustomerMessageActivity
import com.inses.ui.support.detail.CustomerSupportActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(includes = [AuthFragmentProvider::class,HomeFragmentProvider::class])
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity() : SplashActivity

    @ContributesAndroidInjector
    abstract fun bindAuthActivity() : AuthActivity

    @ContributesAndroidInjector
    abstract fun bindHomeActivity() : HomeActivity

    @ContributesAndroidInjector
    abstract fun bindMakeServiceActivity() : MakeServiceActivity

    @ContributesAndroidInjector
    abstract fun bindBookingSuccessActivity() : BookingSuccessActivity

    @ContributesAndroidInjector
    abstract fun bindCustomerMessageActivity() : CustomerMessageActivity

    @ContributesAndroidInjector
    abstract fun bindCustomerSupportActivity() : CustomerSupportActivity

    @ContributesAndroidInjector
    abstract fun bindMyProfileActivity() : MyProfileActivity

    @ContributesAndroidInjector
    abstract fun bindAboutUsActivity() : AboutUsActivity
}