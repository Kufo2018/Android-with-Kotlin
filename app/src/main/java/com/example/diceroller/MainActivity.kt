package com.example.diceroller

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dynamically changing button text
//        roll_button.text = getString(R.string.let_us_roll)

        //Setting on On-click listener
        roll_button.setOnClickListener { view ->
            //            Toast.makeText(this,"Warris happening here", Toast.LENGTH_SHORT).show()
            rollDice()
        }


    }

    // Generating random numbers and assigning to respective dice images
    private fun rollDice() {
        val randomInt = Random.nextInt(6) + 1

        // Extracting Image resource variable
        lateinit var diceImage: ImageView
        diceImage = findViewById(R.id.dice_image)
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }
}
