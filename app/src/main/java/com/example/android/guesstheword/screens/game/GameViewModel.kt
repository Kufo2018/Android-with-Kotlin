package com.example.android.guesstheword.screens.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel(){

    /** The current word **/

    val word = MutableLiveData<String>()

    /** The current score  **/

    val score = MutableLiveData<Int>()

    /** The list of words - the front of the list is the next word to guess **/

    private lateinit var wordList: MutableList<String>

    /** Executes when the GameViewModel is created  **/

    init {

        // Initializes score.value to zero
        score.value = 0

        Timber.i("GameViewModel created")

        // Calls the resetList and nextWord methods
        resetList()
        nextWord()
    }

    /** Resets the list of words and randomizes the order **/

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Moves to the next word in the list **/

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }
}

