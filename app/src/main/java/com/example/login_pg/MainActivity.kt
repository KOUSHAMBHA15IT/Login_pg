package com.example.login_pg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import app.rive.runtime.kotlin.core.Rive
import com.example.login_pg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val stateMachineName = "Login Machine"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Rive.init(this)

        binding.email.setOnFocusChangeListener { view, b ->
            if (b) {
                binding.logChar.controller.setBooleanState(stateMachineName, "isChecking", true)
            } else {
                binding.logChar.controller.setBooleanState(stateMachineName, "isChecking", false)

            }
        }

        binding.pass.setOnFocusChangeListener { view, b ->
            if (b) {
                binding.logChar.controller.setBooleanState(stateMachineName, "isHandsUp", true)
            } else {
                binding.logChar.controller.setBooleanState(stateMachineName, "isHandsUp", false)

            }
        }

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                try {
                    binding.logChar.controller.setNumberState(
                        stateMachineName,
                        "numLook",
                        p0!!.length.toFloat()
                    )
                } catch (e: Exception) {
                }
            }

        })

        binding.button.setOnClickListener {

            binding.pass.clearFocus()

            Handler(mainLooper).postDelayed({

                if (binding.email.text!!.isNotEmpty() && binding.pass.text!!.isNotEmpty() &&
                    (binding.email.text.toString() == "admin@gmail.com" && binding.pass.text.toString() == "123456")
                ) {
                    binding.logChar.controller.fireState(stateMachineName, "trigSuccess");

                } else {
                    binding.logChar.controller.fireState(stateMachineName, "trigFail");
                }

            },20000)



        }
    }
}

