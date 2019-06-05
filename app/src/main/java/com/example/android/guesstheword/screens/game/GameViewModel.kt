package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel(){

    /** Adds the countdown timer **/
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    /** eventGameFinish **/
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get() = _eventGameFinish

    // Creating an instance of the CountDownTimer class
    private val timer: CountDownTimer

    // LiveData encapsulation for currentTime
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
    get() = _currentTime

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

        // Countrols the countDownTimer
        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true            }
        }

        timer.start()

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
            resetList()
        }
        _word.value = wordList.removeAt(0)
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

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}

