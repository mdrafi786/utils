package com.sample.utils.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mdrafi.utils.GlideUtils
import com.sample.utils.R
import com.sample.utils.databinding.FragmentGlideBinding


class GlideFragment : Fragment() {

    private lateinit var bindingGlideBinding: FragmentGlideBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingGlideBinding = FragmentGlideBinding.inflate(inflater)
        return bindingGlideBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImageWithGlide()
    }

    private fun loadImageWithGlide() {
        GlideUtils(
            context = activity,
            url = "https://i.ibb.co/LtK2PKv/nature.jpg",
            imageView = bindingGlideBinding.imageView,
            placeholder = R.drawable.placeholder,
            cacheImage = false
        )
        { isLoad, exception ->
            activity?.let {
                if (isLoad == false) {
                    Toast.makeText(
                        it,
                        exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        it,
                        "Image load successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.load()
    }
}