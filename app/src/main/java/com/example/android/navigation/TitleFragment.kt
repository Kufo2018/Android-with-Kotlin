package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentGameBinding
import com.example.android.navigation.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_game_over.*


/**
 * A simple [Fragment] subclass.
 *
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        /**
         * Normally we use the DataBindingUtil.setContentView to get the databinding class for a layout
         * However, we are in a fragment view and seContent does not exist
         */

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title, container, false
        )

        // Sets onClick listener for play button
        binding.playButton.setOnClickListener { v: View ->
            /**
             * At this point we need to navigate between our destinations
             * To do this, we need to find an instance of the Navigation Controller
             * The Navigation Controller is a class that we use to manage navigation within our navigation host fragment
             * The Navigation host is the parent in the view hierarchy of our current fragment
             * Navigation provides a helper function called findNavController
             * findNavController takes a view, finds the enclosing nav host fragment and returns the navigation controller for that nav host fragment
             */
            v.findNavController().navigate(
                    TitleFragmentDirections.actionTitleFragmentToGameFragment())

            //OR
            /**
            *binding.playButton.setOnClickListener {
            *    Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
            *}
             */

            //OR
//            Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)


        }

        /**
         * Tell Android that TitleFragment has a menu
         */
        setHasOptionsMenu(true)

        // Returns the root of the layout we just inflated
        return binding.root

        /**
         * At this point our fragment is inflating the correct layout
         * But, it is not yet contained by our activity, so it's not visible
         * Go to activity main layout and add the fragment there
         */
    }

    /**
    * *Next we need to override onCreateOptionsMenu and inflate our new menu resource using the provided menu inflater and menu structure.
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    /**
     * Override onOptionsItemSelected to connect it to our NavigationUI.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
                view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}



