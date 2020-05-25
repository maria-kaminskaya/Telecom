package com.telecom.authentication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.telecom.R

@SuppressLint("Registered")
class StartupActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context?) = Intent(context, StartupActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
    }

    fun onClick(v: View) {
        startActivity(ActivityPhone.newIntent(this))
    }

    fun onSignup(v: View) {
        startActivity(SingnupActivity.newIntent(this))
    }


}