package com.example.gharsetu.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gharsetu.R
import com.example.gharsetu.databinding.ActivityLoginScreenBinding
import com.example.gharsetu.databinding.ActivityUserStatusBinding

class UserStatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {

        setupClickListners()

    }

    private fun setupClickListners(){

        binding.ivBackArrow.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }

        binding.clNextStep.setOnClickListener(){
            startActivity(Intent(this,OtpVerificationScreenActivity::class.java))
        }
    }



}