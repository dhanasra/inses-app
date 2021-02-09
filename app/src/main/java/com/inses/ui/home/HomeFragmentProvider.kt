package com.inses.ui.home

import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.inses.ui.bookings.MyBookingsFragment
import com.inses.ui.bookings.fragments.HistoryFragment
import com.inses.ui.bookings.fragments.OngoingFragment
import com.inses.ui.dashboard.DashboardFragment
import com.inses.ui.dashboard.makeService.steps.*
import com.inses.ui.profile.ProfileFragment
import com.inses.ui.support.SupportFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class HomeFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideDashboardFragmentFactory(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun provideStep1FragmentFactory(): Step1Fragment

    @ContributesAndroidInjector
    abstract fun provideStep2FragmentFactory(): Step2Fragment

    @ContributesAndroidInjector
    abstract fun provideStep3FragmentFactory(): Step3Fragment

    @ContributesAndroidInjector
    abstract fun provideStep4FragmentFactory(): Step4Fragment

    @ContributesAndroidInjector
    abstract fun providePriceFragmentFactory(): PriceFragment

    @ContributesAndroidInjector
    abstract fun provideMyBookingsFragmentFactory(): MyBookingsFragment

    @ContributesAndroidInjector
    abstract fun provideHistoryFragmentFactory(): HistoryFragment

    @ContributesAndroidInjector
    abstract fun provideOnGoingFragmentFactory(): OngoingFragment

    @ContributesAndroidInjector
    abstract fun provideSupportFragmentFactory(): SupportFragment

    @ContributesAndroidInjector
    abstract fun provideProfileFragmentFactory(): ProfileFragment

}