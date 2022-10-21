package com.sample.utils.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdrafi.utils.decryptString
import com.mdrafi.utils.encryptString
import com.sample.utils.databinding.FragmentCryptographyBinding

class CryptographyFragment : Fragment() {

    private lateinit var binding: FragmentCryptographyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCryptographyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.encryptBt.setOnClickListener {
            with(binding.editText) {
                text?.trim().toString().apply {
                    if (isNotEmpty()) {
                        setText(this.encryptString())
                    }
                }
                setSelection(text?.trim().toString().length)
            }
        }
        binding.decryptBt.setOnClickListener {
            with(binding.editText) {
                text?.trim().toString().apply {
                    if (isNotEmpty()) {
                        setText(this.decryptString())
                    }
                }
                setSelection(text?.trim().toString().length)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("CustomTag", "onResume() of CryptoFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CustomTag", "OnCreate() of CryptoFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("CustomTag", "onDestroyView() of CryptoFragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CustomTag", "onDestroy() of CryptoFragment")
    }
}