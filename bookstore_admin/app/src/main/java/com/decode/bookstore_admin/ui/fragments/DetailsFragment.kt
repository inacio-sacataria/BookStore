package com.decode.bookstore_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.databinding.FragmentDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class DetailsFragment : Fragment() {

   lateinit var binding : FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_details, container, false)
        binding = FragmentDetailsBinding.bind(view)

        BottomSheetBehavior.from(binding.standardBottomSheet).apply {
            peekHeight=400
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return binding.root
    }

}