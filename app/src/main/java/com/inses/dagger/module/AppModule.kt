package com.inses.dagger.module

import android.app.Application
import android.content.Context
import com.inses.dagger.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}