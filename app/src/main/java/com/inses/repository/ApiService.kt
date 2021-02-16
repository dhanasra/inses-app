package com.inses.repository

import com.inses.data.BookingData
import com.inses.data.Service
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private  val okHttpClient= OkHttpClient.Builder().connectTimeout(30, TimeUnit.MINUTES).readTimeout(30,
    TimeUnit.MINUTES).writeTimeout(30, TimeUnit.MINUTES).build()

private const val BaseURL="https://inses-app-api.herokuapp.com/"
private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit= Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient).baseUrl(BaseURL).build()


interface ApiService {


    @Headers("Content-Type:application/json")
    @POST("customer/add")
    fun addCustomer(
            @Body requestBody: RequestBody
    ): Deferred<String>

    @Headers("Content-Type:application/json")
    @POST("booking/add")
    fun addBooking(
            @Body requestBody: RequestBody
    ): Deferred<String>

    @Headers("Content-Type:application/json")
    @POST("booking/get")
    fun getBooking(
            @Body requestBody: RequestBody
    ): Deferred<List<BookingData>>

    @Headers("Content-Type:application/json")
    @POST("booking/get/history")
    fun getBookingHistory(
        @Body requestBody: RequestBody
    ): Deferred<List<BookingData>>

    @Headers("Content-Type:application/json")
    @POST("service/get")
    fun getAllService(
            @Body requestBody: RequestBody
    ): Deferred<List<Service>>

    @Headers("Content-Type:application/json")
    @POST("booking/update/payment")
    fun updatePayment(
        @Body requestBody: RequestBody
    ): Deferred<String>

    @Headers("Content-Type:application/json")
    @POST("booking/update/review")
    fun updateReview(
        @Body requestBody: RequestBody
    ): Deferred<String>

    @Headers("Content-Type:application/json")
    @POST("service/get")
    fun getService(
            @Body requestBody: RequestBody
    ): Deferred<Service>

    @Headers("Content-Type:application/json")
    @POST("support/add")
    fun addSupport(
            @Body requestBody: RequestBody
    ): Deferred<String>

    @Headers("Content-Type:application/json")
    @POST("token/add")
    fun addToken(
        @Body requestBody: RequestBody
    ): Deferred<String>


    @Headers("Content-Type:application/json")
    @POST("token/update")
    fun updateToken(
        @Body requestBody: RequestBody
    ): Deferred<String>

    @Headers("Content-Type:application/json")
    @POST("feedback/add")
    fun addFeedback(
            @Body requestBody: RequestBody
    ): Deferred<String>

    object AppApi{
        val retrofitService:ApiService by lazy {
            retrofit.create(ApiService::class.java)

        }
    }
}