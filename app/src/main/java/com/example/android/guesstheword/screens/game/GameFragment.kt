/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import timber.log.Timber

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    // Instantiating GameViewModel
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Plants debug log
        Timber.plant(Timber.DebugTree())
        Timber.i("onCreateView")

        // Request the current GameViewModel using the ViewModelProviders class
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        // Sets up observer relationship for timer
        viewModel.currentTime.observe(this, Observer { newTime ->

            // Convert the Long for the timer into a String
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
        })

        // Sets up observer relationship for the score LiveData
        viewModel.score.observe(this, Observer { newScore ->

            // Updates the score TextView
            binding.scoreText.text = newScore.toString()
        })

        // Sets up the observable relationship for the word LiveData
        viewModel.word.observe(this, Observer { newWord ->

            // Updates the word TextView
            binding.wordText.text = newWord.toString()

        })

        // Observes the eventGameFinish
        viewModel.eventGameFinish.observe(this, Observer { isFinished ->
            if (isFinished) {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
                viewModel.onGameFinishComplete()
            }
        })

        //onClick listeners
        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
             }

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()

        }

        // Returns rootView
        return binding.root

    }

}
