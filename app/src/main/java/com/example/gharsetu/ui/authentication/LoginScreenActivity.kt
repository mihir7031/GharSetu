package com.example.gharsetu.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gharsetu.R
import com.example.gharsetu.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }

    private fun init() {

        togglePasswordVisibility()
        setupClickListeners()

    }

    private fun setupClickListeners(){
        binding.btnSignIn.setOnClickListener(){
            if(validateInput()){
                Toast.makeText(this, "Will Redirect to the Dashboard", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSingUp.setOnClickListener(){
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }

    }


    private fun validateInput(): Boolean {

        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            return false
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
        }

        else if(password.isEmpty() ==true){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    private fun togglePasswordVisibility() {

        binding.ivTogglePassword.setOnClickListener {

            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {

                binding.edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ivTogglePassword.setImageResource(R.drawable.visible_eye)
            } else {

                binding.edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivTogglePassword.setImageResource(R.drawable.close_eye)
            }

            binding.edtPassword.setSelection(binding.edtPassword.text?.length ?: 0)
        }

    }
}