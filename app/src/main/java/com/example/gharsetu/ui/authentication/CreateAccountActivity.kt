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
import com.example.gharsetu.databinding.ActivityCreateAccountBinding

 class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateAccountBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

    }

    private fun init() {

        binding.ivBackArrow.setOnClickListener {
            startActivity(Intent(this,LoginScreenActivity::class.java))
        }

        binding.tvSigIn.setOnClickListener {
            startActivity(Intent(this,LoginScreenActivity::class.java))
        }

        binding.clContinue.setOnClickListener {
            if (validateInput()) {

                Toast.makeText(this, "Validation successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,UserStatusActivity::class.java))
            }


        }

        togglePasswordVisibility()
    }

    private fun validateInput(): Boolean {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        val hasMinLength = password.length >= 8
        val hasUppercase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val hasNoSpace = !password.any { it.isWhitespace() }


        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
            return false
        }

        else if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            return false
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
            return false
        }

        else if ( !hasMinLength ||
            !hasUppercase ||
            !hasDigit ||
            !hasSpecialChar ||
            !hasNoSpace )  {
            Toast.makeText(this, "Invalid password format", Toast.LENGTH_SHORT).show()
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