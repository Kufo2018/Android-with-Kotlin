package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing button view
        findViewById<Button>(R.id.done_button).setOnClickListener {
            addNickname(it)
        }
    }

    // Add nickname function
    private fun addNickname(view: View) {

        // Find edit text view and get text
        val nickname: EditText = findViewById(R.id.nickname)
        nickname.text

        // Find nickname text view and put nickname in it
        val userNickname: TextView = findViewById(R.id.what_is_your_nickname)
        userNickname.text = nickname.text

        // Show nickname text view
        userNickname.visibility = View.VISIBLE

        // Hide edit text field
        nickname.visibility = View.GONE

        // Hide done button
        done_button.visibility = View.GONE

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
