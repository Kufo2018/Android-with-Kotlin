package com.example.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.android.navigation.databinding.FragmentGameBinding
import com.example.android.navigation.databinding.FragmentTitleBinding


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

        // Returns the root of the layout we just inflated
        return binding.root

        /**
         * At this point our fragment is inflating the correct layout
         * But, it is not yet contained by our activity, so it's not visible
         * Go to activity main layout and add the fragment there
         */

    }


}



