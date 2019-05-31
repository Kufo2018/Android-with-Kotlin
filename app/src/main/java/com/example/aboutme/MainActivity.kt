package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: com.example.aboutme.databinding.ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Binding button view and setting on-click listener
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    // Add nickname function
    private fun addNickname(view: View) {

        // Bind edit text view and get text
        binding.nickname.text

        // Nickname text view equal to  edit text input
        binding.whatIsYourNickname.text = binding.nickname.text

        // Show nickname text view
        binding.whatIsYourNickname.visibility = View.VISIBLE

        // Hide edit text field
        binding.nickname.visibility = View.GONE

        // Hide done button
        binding.doneButton.visibility = View.GONE

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


// CODE SNIPPETS
//        findViewById<Button>(R.id.done_button).setOnClickListener {

