package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rsrafprojekat2stefan_budimac_rn0618.MainActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent: Intent = if (checkLoginInfo()) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

    private fun checkLoginInfo(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val ime: String? = sharedPreferences.getString(LoginActivity.PREF_KORISNIK_IME, null)
        val pin: String? = sharedPreferences.getString(LoginActivity.PREF_KORISNIK_PIN, null)
        return !(ime == null || pin == null)
    }
}