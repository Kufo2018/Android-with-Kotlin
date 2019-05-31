package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: com.example.aboutme.databinding.ActivityMainBinding

    // We need an instance of the `myname` data class, so we create it and set the name
    var myName: MyName = MyName("Lord Stark")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set the value of myName used in layout file to firstName
        binding.myName = myName

        // Binding button view and setting on-click listener
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    // Add nickname function
    private fun addNickname(view: View) {

        binding.apply {

            // Nickname text view equal to  edit text input
            myName?.nickname = nickname.text.toString()

            // Invalidates all binding expressions and requests a new rebind to refresh UI.
            invalidateAll()

            // Show nickname text view
            whatIsYourNickname.visibility = View.VISIBLE

            // Hide edit text field
            nickname.visibility = View.GONE

            // Hide done button
            doneButton.visibility = View.GONE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


// CODE SNIPPETS
//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            binding.whatIsYourNickname.text = binding.nickname.text


