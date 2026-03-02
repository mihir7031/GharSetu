package com.example.gharsetu.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gharsetu.R
import com.example.gharsetu.databinding.ActivityLoginScreenBinding
import com.example.gharsetu.databinding.ActivityOtpVerificationScreenBinding

class OtpVerificationScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpVerificationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOtpVerificationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }

    private fun init(){

        setonClickListeners()

    }

    private fun setonClickListeners() {

        binding.ivBackArrow.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }
    }
}


