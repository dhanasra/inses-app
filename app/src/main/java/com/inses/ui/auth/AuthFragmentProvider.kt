package com.inses.ui.auth

import com.inses.ui.auth.create.CreateLoadingFragment
import com.inses.ui.auth.create.cookie.NameFragment
import com.inses.ui.auth.create.createAuth.CreateAccountFragment
import com.inses.ui.auth.create.createPassword.CreatePasswordFragment
import com.inses.ui.auth.login.loginAuth.LoginAccountFragment
import com.inses.ui.auth.login.loginPassword.LoginPasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class AuthFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideCreateAccountFragmentFactory(): CreateAccountFragment

    @ContributesAndroidInjector
    abstract fun provideLoginAccountFragmentFactory(): LoginAccountFragment

    @ContributesAndroidInjector
    abstract fun provideLoginPasswordFragmentFactory(): LoginPasswordFragment

    @ContributesAndroidInjector
    abstract fun provideCreatePasswordFragmentFactory(): CreatePasswordFragment

    @ContributesAndroidInjector
    abstract fun provideCreateLoadingFragmentFactory(): CreateLoadingFragment

    @ContributesAndroidInjector
    abstract fun provideNameFragmentFactory(): NameFragment
}