package com.sample.utils.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.mdrafi.utils.Validator
import com.sample.utils.R
import com.sample.utils.databinding.FragmentValidationBinding

class ValidationFragment : Fragment() {

    private lateinit var binding: FragmentValidationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentValidationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextWatchers()
        setClickListener()
    }

    private fun setClickListener() {
        binding.signupBt.setOnClickListener {
            binding.signUpTv.text = getString(R.string.signup)
            activity?.let {
                Toast.makeText(it, "Signup Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setTextWatchers() {
        binding.firstNameEt.doAfterTextChanged {
            when {
                TextUtils.isEmpty(it) -> {
                    signupButtonVisibility()
                    binding.errorFirstNameTv.visibility = View.INVISIBLE
                    binding.firstNameEt.setColor(R.color.dark_blue)
                    binding.firstNameEt.setBackgroundResource(R.drawable.edit_text_background)
                }
                Validator.isValidName(it.toString()) -> {
                    binding.firstNameEt.setColor(R.color.dark_blue)
                    binding.errorFirstNameTv.visibility = View.INVISIBLE
                    signupButtonVisibility(true)
                    binding.firstNameEt.setBackgroundResource(R.drawable.bg_no_error)

                }
                else -> {
                    binding.firstNameEt.setColor(R.color.red)
                    binding.errorFirstNameTv.visibility = View.VISIBLE
                    signupButtonVisibility()
                    binding.firstNameEt.setBackgroundResource(R.drawable.error_bg)
                }
            }
        }
        binding.lastNameEt.doAfterTextChanged {
            when {
                TextUtils.isEmpty(it) -> {
                    binding.lastNameEt.setColor(R.color.dark_blue)
                    binding.errorLastNameTv.visibility = View.INVISIBLE
                    signupButtonVisibility()
                    binding.lastNameEt.setBackgroundResource(R.drawable.edit_text_background)
                }
                Validator.isValidName(it.toString()) -> {
                    binding.lastNameEt.setColor(R.color.dark_blue)
                    binding.errorLastNameTv.visibility = View.INVISIBLE
                    signupButtonVisibility(true)
                    binding.lastNameEt.setBackgroundResource(R.drawable.bg_no_error)

                }
                else -> {
                    binding.lastNameEt.setColor(R.color.red)
                    binding.errorLastNameTv.visibility = View.VISIBLE
                    signupButtonVisibility()
                    binding.lastNameEt.setBackgroundResource(R.drawable.error_bg)
                }
            }
        }
        binding.emailEt.doAfterTextChanged {

            when {
                TextUtils.isEmpty(it) -> {
                    binding.emailEt.setColor(R.color.dark_blue)
                    binding.errorInvalidEmailTv.visibility = View.INVISIBLE
                    signupButtonVisibility()
                    binding.emailEt.setBackgroundResource(R.drawable.edit_text_background)
                }
                Validator.isValidEmail(it.toString()) -> {
                    binding.emailEt.setColor(R.color.dark_blue)
                    binding.errorInvalidEmailTv.visibility = View.INVISIBLE
                    signupButtonVisibility(true)
                    binding.emailEt.setBackgroundResource(R.drawable.bg_no_error)

                }
                else -> {
                    binding.emailEt.setColor(R.color.red)
                    binding.errorInvalidEmailTv.visibility = View.VISIBLE
                    signupButtonVisibility()
                    binding.emailEt.setBackgroundResource(R.drawable.error_bg)
                }
            }
        }
        binding.passwordEt.doAfterTextChanged {
            when {
                TextUtils.isEmpty(it) -> {
                    binding.passwordEt.setColor(R.color.dark_blue)
                    binding.errorInvalidPasswordTv.visibility = View.INVISIBLE
                    signupButtonVisibility()
                    binding.passwordEt.setBackgroundResource(R.drawable.edit_text_background)
                }
                Validator.isValidPassword(it.toString()) -> {
                    binding.passwordEt.setColor(R.color.dark_blue)
                    binding.errorInvalidPasswordTv.visibility = View.INVISIBLE
                    binding.passwordEt.setBackgroundResource(R.drawable.bg_no_error)
                    signupButtonVisibility(true)
                }
                else -> {
                    binding.passwordEt.setColor(R.color.red)
                    binding.errorInvalidPasswordTv.visibility = View.VISIBLE
                    binding.passwordEt.setBackgroundResource(R.drawable.error_bg)
                    signupButtonVisibility()
                }
            }
        }
    }

    private fun signupButtonVisibility(enabled: Boolean = false) {
        binding.signupBt.isEnabled = enabled
        binding.signupBt.alpha = if (enabled) 1f else 0.5f
    }

    fun EditText.setColor(color: Int) {
        activity?.let { context ->
            setTextColor(
                ContextCompat.getColor(
                    context,
                    color
                )
            )
        }
    }
}