package com.inses.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.inses.Constants
import com.inses.ui.base.BaseViewModel
import com.inses.utils.resources.AppPreferenceHelper
import java.util.*
import javax.inject.Inject

class AuthVIewModel @Inject constructor(context: Context):BaseViewModel<AuthNavigator>() {


    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    private var firebaseAuth = Firebase.auth
    private lateinit var user: FirebaseUser

    var email = ObservableField("")
    var password = ObservableField("")
    var firstName = ObservableField("")
    var secondName = ObservableField("")

    enum class Screen{
        LOGIN,
        CREATE,
        C_PASSWORD,
        L_PASSWORD,
        NAME,
        L_HOME,
        HOME,
        GOOGLE,
        C_LOADING
    }

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    @SuppressLint("LogNotTimber")
    fun createUserWithEmailAndPassword(){
        firebaseAuth.createUserWithEmailAndPassword(email.get()!!.trim().toLowerCase(Locale.ROOT),password.get()!!.trim())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    user = firebaseAuth.currentUser!!

                    editor.putString(AppPreferenceHelper.PREF_KEY_FIRST_NAME,firstName.get()!!.trim())
                    editor.putString(AppPreferenceHelper.PREF_KEY_LAST_NAME,secondName.get()!!.trim())
                    editor.putString(AppPreferenceHelper.PREF_KEY_IMAGE,"")
                    editor.putString(AppPreferenceHelper.PREF_KEY_EMAIL,email.get()!!.trim())
                    editor.putString(AppPreferenceHelper.PREF_KEY_FIREBASE_USER_ID,FirebaseAuth.getInstance().uid!!)
                    editor.putString(AppPreferenceHelper.PREF_KEY_PHONE_NUMBER,"")
                    editor.putString(AppPreferenceHelper.PREF_KEY_CUSTOMER_ID,"${FirebaseAuth.getInstance().uid!!}-${Calendar.getInstance().timeInMillis}")
                    editor.commit()
                    editor.apply()

                    mResponse.value = "successfully created"
                }else{
                    mResponse.value = "failure"
                }
            }
            .addOnFailureListener {
                Log.d("failed",it.message.toString())
            }
    }

    fun signInWithEmailAndPassword():String{
        var result=""
        firebaseAuth.signInWithEmailAndPassword(email.get()!!.trim().toLowerCase(Locale.ROOT),
            password.get()!!.trim())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    user = firebaseAuth.currentUser!!

                    result = "successfully logged"
                }else{
                    result = "failed"
                }
            }
            .addOnFailureListener {
                Log.d("login",it.message.toString())
            }
        return result
    }


    fun handleGSinResult(task: Task<GoogleSignInAccount>){
        try {
            val acc=task.result
            firebaseGoogleAuth(acc!!)
        }catch (e: ApiException){

        }
    }

    private fun firebaseGoogleAuth(acct: GoogleSignInAccount){
        try {
            val auth = FirebaseAuth.getInstance()
            val authCredentials = GoogleAuthProvider.getCredential(acct.idToken, null)
            auth.signInWithCredential(authCredentials).addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
//                    var user = auth.currentUser
                    createProfileWithGoogle(acct)
                }
            }
        }catch (e:Exception){

        }
    }

    private fun createProfileWithGoogle(acct: GoogleSignInAccount){
        try {
            firstName.set(acct.displayName.toString())
            email.set(acct.email.toString())
            mResponse.value = "gsiSuccess"
        }catch (e:Exception){
            mResponse.value="gsiFailure"
        }
    }

}