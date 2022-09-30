package com.mdrafi.utils

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

/**
 * Extension method to provide show keyboard for [Activity].
 */
fun Activity.showSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this.currentFocus, InputMethodManager.SHOW_IMPLICIT)
    }
}

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

fun EditText.openKeyboardWhenFocus() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/*
* Change hint text color of TextInput Layout
* */
fun TextInputLayout.changeHintTextColor(context: Context?, color: Int) {
    context?.let {
        val colorInt = ContextCompat.getColor(
            it,
            color
        )
        val csl: ColorStateList = ColorStateList.valueOf(colorInt)
        this.hintTextColor = csl
    }
}

/*
* Call if you want to change edittext text within text watcher
* */
fun TextView.applyWithDisabledTextWatcher(
    textWatcher: TextWatcher,
    codeBlock: TextView.() -> Unit
) {
    this.removeTextChangedListener(textWatcher)
    codeBlock()
    this.addTextChangedListener(textWatcher)
}

/*
* Use this method if you want to use two link in single string and their link callback
* */
fun TextView.makeLinks(
    spannableString: SpannableString,
    vararg links: Pair<String, View.OnClickListener>
) {
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = spannableString.indexOf(link.first, startIndexOfLink + 1)
        spannableString.bold(startIndexOfLink, startIndexOfLink + link.first.length)
        spannableString.setSpan(
            clickableSpan,
            startIndexOfLink,
            startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}
