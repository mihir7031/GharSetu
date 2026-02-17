package com.example.gharsetu.ui.authentication

import android.os.Bundle
import android.text.InputType
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
        preformLogin()

    }


    private fun preformLogin(){
        binding.btnSignIn.setOnClickListener(){

            if(validateInput()){
                Toast.makeText(this, "Clicked on the SignIn Button", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validateInput(): Boolean {

        if(binding.edtEmail.text?.isEmpty()==true){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(binding.edtPassword.text?.isEmpty()==true){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }
        else{
            return true
        }
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