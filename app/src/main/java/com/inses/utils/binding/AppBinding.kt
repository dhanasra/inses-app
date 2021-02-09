package com.inses.utils.binding

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.inses.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("txtSize")
fun setTextSize(view: TextView, txtSize: String?){
    when (txtSize) {
        "mini" -> {
            view.textSize = 14F
        }
        "smallest" -> {
            view.textSize = 15F
        }
        "smaller" -> {
            view.textSize = 16F
        }
        "smallC" -> {
            view.textSize = 17F
        }
        "smallB" -> {
            view.textSize = 18F
        }
        "smallA" -> {
            view.textSize = 19F
        }
        "small" -> {
            view.textSize = 20F
        }
        "medium" -> {
            view.textSize = 21F
        }
        "big" -> {
            view.textSize = 22F
        }
        "bigger" -> {
            view.textSize = 24F
        }
        "biggest" -> {
            view.textSize = 26F
        }
    }
}

@BindingAdapter("txtStyle")
fun setStyle(view: TextView, txtStyle: String?){
    when(txtStyle){
        "b" -> {
            view.setTypeface(null, Typeface.BOLD)
        }
        "i" -> {
            view.setTypeface(null, Typeface.ITALIC)
        }
        "bi" -> {
            view.setTypeface(null, Typeface.BOLD_ITALIC)
        }
    }
}

@BindingAdapter("txtColor")
fun setTextColor(view: TextView, txtColor: String?){
    val con = view.context
    when(txtColor){
        "tc" -> view.setTextColor(ContextCompat.getColor(con, R.color.text_color))
        "b" -> view.setTextColor(ContextCompat.getColor(con, R.color.black))
        "ab" -> view.setTextColor(ContextCompat.getColor(con, R.color.app_button))
        "w" -> view.setTextColor(ContextCompat.getColor(con, R.color.white))
    }
}

@BindingAdapter("imageType")
fun setImageType(view: ImageView, type: String?){
    if(!type.isNullOrEmpty()) {
        when (type) {
            //main
            "bw" -> view.setImageResource(R.drawable.borewell)
            "e" -> view.setImageResource(R.drawable.electrician)
            "i" -> view.setImageResource(R.drawable.install)
            "led" -> view.setImageResource(R.drawable.led)
            "m" -> view.setImageResource(R.drawable.maintanence)
            "pa" -> view.setImageResource(R.drawable.painter)
            "pl" -> view.setImageResource(R.drawable.plumber)
            //plumber
            "icbw" -> view.setImageResource(R.drawable.ic_borewell_line)
            "icl" -> view.setImageResource(R.drawable.ic_leaking)
            "icm" -> view.setImageResource(R.drawable.ic_motor)
            "icnp" -> view.setImageResource(R.drawable.ic_new_pipeline)
            "icop" -> view.setImageResource(R.drawable.ic_other_pipe)
            "icwt" -> view.setImageResource(R.drawable.ic_water_tank)
            "icwp" -> view.setImageResource(R.drawable.ic_water_tap)
            "ics" -> view.setImageResource(R.drawable.ic_sanitary)
            //profile
            "pu" -> view.setImageResource(R.drawable.ic_user)
            "psh" -> view.setImageResource(R.drawable.ic_share)
            "pfb" -> view.setImageResource(R.drawable.ic_feedback)
            "pcu" -> view.setImageResource(R.drawable.ic_mail)
            "pso" -> view.setImageResource(R.drawable.ic_logout)
            "pi" -> view.setImageResource(R.drawable.ic_info)
            "pc" -> view.setImageResource(R.drawable.ic_call)
        }
    }
}

@BindingAdapter("setBg")
fun setBg(view: View, setBg: Boolean){
    if(setBg){
        when((0..10).random()) {
            0 -> view.setBackgroundResource(R.color.cancellation_yellow)
            1 -> view.setBackgroundResource(R.color.teal_200)
            2 -> view.setBackgroundResource(R.color.teal_700)
            3 -> view.setBackgroundResource(R.color.example_4_green)
            4 -> view.setBackgroundResource(R.color.cancellation_green)
            5 -> view.setBackgroundResource(R.color.purple_200)
            6 -> view.setBackgroundResource(R.color.cancel_text_color)
            7 -> view.setBackgroundResource(R.color.colorAccent)
            8 -> view.setBackgroundResource(R.color.c_rausch_dark)
            9 -> view.setBackgroundResource(R.color.colorBottomNavigationAccent)
            10 -> view.setBackgroundResource(R.color.colorBottomNavigationNotification)
        }
    }
}

@BindingAdapter("selectedback")
fun setSelectedBack(view:View,selected:Boolean){
    if(!selected){
        view.setBackgroundResource(R.drawable.grey_back)
    }else{
        view.setBackgroundResource(R.drawable.selected_back)
    }
}

@BindingAdapter("setHeight")
fun setHeight(view: View, height: String){
    when(height){
        "small" -> {
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20)
        }
        "medium" -> {
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30)
        }
        "big" -> {
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40)
        }
        "bigger" -> {
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60)
        }
        "biggest" -> {
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80)
        }
    }
}

@BindingAdapter("cImage")
fun setImage(view:CircleImageView,url:String?){
    if(url!=null && url!="empty"){
        Glide.with(view).load(url).into(view)
    }
}

@BindingAdapter("image")
fun setImage(view:ImageView,url:String?){
    if(url!=null && url!="none"){
        Glide.with(view).load(url).into(view)
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("setBack")
fun setBack(view:View,boolean: Boolean){
    if(boolean){
        var i = (0..10).random()
        when(i) {
            0 -> view.setBackgroundResource(R.color.cancellation_yellow)
            1 -> view.setBackgroundResource(R.color.teal_200)
            2 -> view.setBackgroundResource(R.color.teal_700)
            3 -> view.setBackgroundResource(R.color.example_4_green)
            4 -> view.setBackgroundResource(R.color.cancellation_green)
            5 -> view.setBackgroundResource(R.color.purple_200)
            6 -> view.setBackgroundResource(R.color.cancel_text_color)
            7 -> view.setBackgroundResource(R.color.colorAccent)
            8 -> view.setBackgroundResource(R.color.c_rausch_dark)
            9 -> view.setBackgroundResource(R.color.colorBottomNavigationAccent)
            10 -> view.setBackgroundResource(R.color.colorBottomNavigationNotification)
        }
    }
}