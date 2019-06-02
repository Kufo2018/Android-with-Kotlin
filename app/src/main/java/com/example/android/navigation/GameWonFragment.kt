/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        // Return next match button to game won fragment
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameWonFragmentDirections.actionGameWonFragmentToGameFragment()
            )
        }

//        // GameWonFragmentArgs extracts the args class from the Bundle, and displays in a Toast.
//        val args = GameWonFragmentArgs.fromBundle(arguments!!)
//        Toast.makeText(context,
//                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
//                Toast.LENGTH_LONG).show()

        /**
         * Tell Android that GameWonFragment has a menu
         */
        setHasOptionsMenu(true)

        return binding.root
    }

    /**
     * Create a getShareIntent method
     *  Move GameWonFragmentArgs.fromBundle there.
     *  We use shareCompat to create our share Implicit intent by
     *  using ShareCompat.IntentBuilder.from(activity), set our text, and then set the MIME type.
     *  Then we finish up by building our intent
     */
    private fun getShareIntent() : Intent {

    // GameWonFragmentArgs extracts the args class from the Bundle, and displays in a Toast.
        val args = GameWonFragmentArgs.fromBundle(arguments!!)

        // Create a new share implicit intent
        val shareIntent = Intent(Intent.ACTION_SEND)
        // Type of data to be shared
        shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        return shareIntent

        //OR
//        return ShareCompat.IntentBuilder.from(activity)
//                .setText(getString(R.string.share_success_text), args.numCorrect, args.numQuestions)//TODO(Correct argument error here)
//                .setType("text/plain")
//                .intent
    }

    /**
     * Create a share success method that
     * gets the Intent from getShareIntent
     * and calls startActivity to begin sharing.
     */
    // Starting an Activity with our new Intent
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    /**
     * Override onCreateOptionsMenu and begin by inflating the winner_menu.
     * Then we’ll get the shareIntent using getShareIntent()
     * and call resolveActivity using the packageManger to make sure our shareIntent resolves to an activity.
     */

    // Showing the Share Menu Item Dynamically
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    /**
     * Override onOptionsItemSelected.
     * When the menuitem id matches R.id.share, call the shareSuccess method.
     */
    // Sharing from the Menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
