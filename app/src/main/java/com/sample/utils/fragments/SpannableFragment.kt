package com.sample.utils.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.mdrafi.utils.*
import com.sample.utils.R
import com.sample.utils.databinding.FragmentSpannableBinding

class SpannableFragment : Fragment() {

    private lateinit var binding: FragmentSpannableBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpannableBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTextColor()
        changeFont()
        boldText()
        underline()
        strike()
        createTwoLinkInOneString()
    }

    private fun boldText() {
        val spannableString = SpannableString(getString(R.string.i_am_groot))
        binding.boldTv.text = spannableString.bold(0, spannableString.length)
    }

    private fun changeTextColor() {
        context?.let {
            val spannableString = SpannableString(getString(R.string.i_am_groot))
            binding.colorTv.text =
                spannableString.color(0, 5, ContextCompat.getColor(it, R.color.yellow))
        }
    }

    private fun underline() {
        val spannableString = SpannableString(getString(R.string.i_am_groot))
        binding.underlineTv.text = spannableString.underline(0, spannableString.length)
    }


    private fun strike() {
        val spannableString = SpannableString(getString(R.string.i_am_groot))
        binding.strikeTv.text = spannableString.strike(5, 10)
    }

    private fun changeFont() {

        context?.let {
            val spannableString1 = SpannableString(getString(R.string.i_am_groot)).apply {
                fontType(5, this.length, ResourcesCompat.getFont((it), R.font.new_york_regular))
            }

            binding.fontTv.text = spannableString1
        }
    }

    private fun createTwoLinkInOneString() {
        val spanString =
            SpannableString(getString(R.string.i_agree_to_terms_and_conditions_and_privacy_notice))
        binding.linkTv.makeLinks(
            spanString,
            Pair(getString(R.string.terms_conditions_underline), View.OnClickListener {
                Toast.makeText(activity, "Term and condition clicked", Toast.LENGTH_SHORT).show()
            }),
            Pair(getString(R.string.privacy_notice_underline), View.OnClickListener {
                Toast.makeText(activity, "Privacy Policy clicked", Toast.LENGTH_SHORT).show()
            }),
        )
    }
}