package com.example.android.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel(){

    /** The current word **/

    // Internal variable
    private val _word = MutableLiveData<String>()

    // External variable
    val word: LiveData<String>

    // Makes a backing property for the external version
    //that returns the internal MutableLiveData as a LiveData
    get() = _word

    /** The current score  **/

    // Internal variable
    private val _score = MutableLiveData<Int>()

    // External variable
    val score: LiveData<Int>

    // Makes a backing property for the external version
    //that returns the internal MutableLiveData as a LiveData
    get() = _score

    /** The list of words - the front of the list is the next word to guess **/

    private lateinit var wordList: MutableList<String>

    /** Executes when the GameViewModel is created  **/

    init {

        // Initializes score.value to zero
        _score.value = 0

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
            _word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }
}

