package com.inses.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.inses.BR
import com.inses.R
import com.inses.databinding.ActivityAuthBinding
import com.inses.ui.auth.create.CreateLoadingFragment
import com.inses.ui.auth.create.cookie.NameFragment
import com.inses.ui.auth.create.createAuth.CreateAccountFragment
import com.inses.ui.auth.create.createPassword.CreatePasswordFragment
import com.inses.ui.auth.login.loginAuth.LoginAccountFragment
import com.inses.ui.auth.login.loginPassword.LoginPasswordFragment
import com.inses.ui.base.BaseActivity
import com.inses.ui.home.HomeActivity
import com.inses.utils.AuthUiEvent
import com.inses.utils.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthVIewModel>(), AuthNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_auth
    override val viewModel: AuthVIewModel
        get() = ViewModelProvider(this,viewModelFactory).get(AuthVIewModel::class.java)
    private lateinit var mBinding: ActivityAuthBinding
    private val activity = this
    private var eventCompositeDisposal = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        entryPoint(savedInstanceState)
        initRxBusListener()
        subscribeToLiveData()
    }
    private fun subscribeToLiveData(){
        viewModel.response.observe(this, {
            when (it) {
                "successfully logged" -> {
                    startActivity(Intent(this,HomeActivity::class.java))
                }
                "gsiSuccess" -> {
                    startActivity(Intent(this,HomeActivity::class.java))
                }
                "successfully created" -> {
                    val fragment = CreateLoadingFragment()
                    val fragmentManager = activity.supportFragmentManager
                    fragmentManager.beginTransaction().replace(mBinding.flAuth.id,fragment).addToBackStack("loading").commitAllowingStateLoss()
                }
                "failure" -> {
                    showSnackBar("Failed","Failed to Login now!","")
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this,AuthActivity::class.java))
                    },2000)
                }
                "failed" -> {
                    showSnackBar("Failed","Failed to create new user!","")
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this,AuthActivity::class.java))
                    },2000)            }
            }
        })
    }

    private fun initRxBusListener() {
        eventCompositeDisposal.add(
            RxBus.listen(AuthUiEvent.Navigate::class.java)
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .doOnNext { hideSnackBar() }
                .subscribe { navigateToScreen(it.screen, *it.params) })
    }

    private fun entryPoint(savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            val fragment = CreateAccountFragment()
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction().add(mBinding.flAuth.id,fragment).commitAllowingStateLoss()
        }
    }

    override fun navigateToScreen(screen: AuthVIewModel.Screen, vararg params: String?) {
        when(screen){
            AuthVIewModel.Screen.LOGIN->{ moveToLoginFragment() }
            AuthVIewModel.Screen.CREATE->{ moveToCreateFragment() }
            AuthVIewModel.Screen.C_PASSWORD->{ moveToCreatePassword(params[0]!!) }
            AuthVIewModel.Screen.L_PASSWORD->{ moveToLoginPassword(params[0]!!) }
            AuthVIewModel.Screen.NAME->{ moveToName(params[0]!!) }
            AuthVIewModel.Screen.L_HOME->{ moveToLoginHome(params[0]!!) }
            AuthVIewModel.Screen.GOOGLE->{ gsi() }
            AuthVIewModel.Screen.HOME->{
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
            AuthVIewModel.Screen.C_LOADING->{ moveToCreateLoading(params[0]!!,params[1]!!) }
        }
    }

    private fun moveToLoginFragment(){
        val fragment = LoginAccountFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flAuth.id,fragment).commitAllowingStateLoss()
    }

    private fun moveToCreateFragment(){
        val fragment = CreateAccountFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flAuth.id,fragment).commitAllowingStateLoss()
    }

    private fun moveToCreatePassword(email:String){
        viewModel.email.set(email)
        val fragment = CreatePasswordFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flAuth.id,fragment).addToBackStack("cPassword").commitAllowingStateLoss()
    }

    private fun moveToLoginPassword(email: String){
        viewModel.email.set(email)
        val fragment = LoginPasswordFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flAuth.id,fragment).addToBackStack("lPassword").commitAllowingStateLoss()
    }

    private fun moveToName(password:String){
        viewModel.password.set(password)
        val fragment = NameFragment()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction().replace(mBinding.flAuth.id,fragment).addToBackStack("Name").commitAllowingStateLoss()
    }


    private fun moveToLoginHome(password:String){
        viewModel.password.set(password)
        viewModel.signInWithEmailAndPassword()
    }


    private fun gsi(){
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        val gsc= GoogleSignIn.getClient(this,gso)
        val i=gsc.signInIntent
        startActivityForResult(i,101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode== Activity.RESULT_OK && data!=null){
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleGSinResult(task)
        }
    }

    private fun moveToCreateLoading(fName:String,lName:String){
        viewModel.firstName.set(fName)
        viewModel.secondName.set(lName)
        viewModel.createUserWithEmailAndPassword()
    }
}