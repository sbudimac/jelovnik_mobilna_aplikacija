package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    companion object {
        const val PREF_KORISNIK_IME: String = "korisnikIme"
        const val PREF_KORISNIK_PIN: String = "korisnikPin"
    }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        loginInfo = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        initListeners()
    }

    private fun initListeners() {
        binding.prijava.setOnClickListener {
            if (binding.loginIme.text.isEmpty()) {
                Toast.makeText(this, "Username is required.", Toast.LENGTH_SHORT).show()
            } else if (binding.loginPin.text.isEmpty()) {
                Toast.makeText(this, "Pin is required.", Toast.LENGTH_SHORT).show()
            } else if (binding.loginPin.text.toString().length < 4) {
                Toast.makeText(this, "Pin needs to be at least four characters.", Toast.LENGTH_SHORT).show()
            } else {
                loginInfo.edit().putString(PREF_KORISNIK_IME, binding.loginIme.text.toString()).apply()
                loginInfo.edit().putString(PREF_KORISNIK_PIN, binding.loginPin.text.toString()).apply()
                val intent: Intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}