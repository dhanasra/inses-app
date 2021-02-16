package com.inses.ui.profile

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.inses.*
import com.inses.databinding.FragmentProfileBinding
import com.inses.ui.base.BaseFragment
import com.inses.ui.profile.about.AboutUsActivity
import com.inses.ui.profile.about.AboutUsViewModel
import com.inses.ui.profile.myProfile.MyProfileActivity
import com.inses.ui.splash.SplashActivity
import com.inses.ui.splash.SplashViewModel
import com.inses.utils.onClick
import com.inses.utils.withModels
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding,ProfileViewModel>() {
    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: ProfileViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)
    private lateinit var mBinding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        setUp()
        subscribeToLiveData()
    }

    private fun setUp(){
        mBinding.profileEpoxyRecyclerView.withModels {
            viewHolderSubtitle {
                id("profile")
                text("Profile")
                txtSize("medium")
                txtColor("b")
            }
            viewHolderViewBold {
                id("bold")
            }
            viewHolderProfileOptions {
                id("pro")
                option("My Profile")
                ivVisibility(true)
                vVisibility(true)
                imageType("pu")
                onclick(View.OnClickListener {
                    startActivity(Intent(requireContext(),MyProfileActivity::class.java))
                })
            }
            viewHolderProfileOptions {
                id("feedback")
                option("Feedback")
                ivVisibility(true)
                vVisibility(true)
                imageType("pfb")
                onclick(View.OnClickListener {
                    feedbackDialogueShow()
                })
            }
            viewHolderProfileOptions {
                id("invite")
                option("Invite friends")
                ivVisibility(true)
                vVisibility(true)
                imageType("psh")
            }
            viewHolderProfileOptions {
                id("about")
                option("About us")
                ivVisibility(true)
                vVisibility(true)
                imageType("pi")
                onclick(View.OnClickListener {
                    startActivity(Intent(requireContext(),AboutUsActivity::class.java))
                })
            }
            viewHolderProfileOptions {
                id("contact")
                option("Contact us")
                ivVisibility(true)
                vVisibility(true)
                imageType("pcu")
                onclick(View.OnClickListener {
                    val i = Intent(Intent.ACTION_SENDTO)
                    i.data = Uri.parse("mailto:test@gmail.com")
                    startActivity(i)
                })
            }
            viewHolderProfileOptions {
                id("call")
                option("Call Now")
                ivVisibility(true)
                vVisibility(true)
                imageType("pc")
                onclick(View.OnClickListener {
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"8056384773")))
                })
            }
            viewHolderProfileOptions {
                id("Sign out")
                option("Sign out")
                ivVisibility(true)
                imageType("pso")
                onclick(View.OnClickListener {
                    signoutDialogueShow()
                })
            }
        }
    }

    private fun feedbackDialogueShow(){
        val fbDialogue = Dialog(requireContext())
        fbDialogue.setContentView(R.layout.dialogue_feed_back)
        val submit = fbDialogue.findViewById(R.id.submitButton) as TextView
        val cancel = fbDialogue.findViewById(R.id.cancelButton) as TextView
        val ed = fbDialogue.findViewById(R.id.ed) as EditText
        submit.onClick {
            if(ed.text.toString().isNotEmpty()) {
                viewModel.message.set(ed.text.toString())
                viewModel.sendFeedback()
                fbDialogue.cancel()
            }else{
                showSnackBar("Invalid","Please Type a Feedback to send","")
            }
        }
        cancel.onClick {
            fbDialogue.cancel()
        }
        fbDialogue.show()
    }

    private fun signoutDialogueShow(){
        val soDialogue = Dialog(requireContext())
        soDialogue.setContentView(R.layout.dialogue_signout)
        val submit = soDialogue.findViewById(R.id.submitButton) as TextView
        val cancel = soDialogue.findViewById(R.id.cancelButton) as TextView
        cancel.onClick {
            soDialogue.cancel()
        }
        submit.onClick {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireContext(),SplashActivity::class.java))
            requireActivity().finishAffinity()
        }
        soDialogue.show()
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(requireActivity(),{
            if(it=="success"){
                showSnackBar("Success","Successfully reported your feedback","")
            }
        })
    }
}