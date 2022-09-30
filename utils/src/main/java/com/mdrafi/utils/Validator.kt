package com.mdrafi.utils

object Validator {
    private var signUpMobile: String = "[0-9]{10}"
    private var mobile: String = "[0-9]+"

    /*
    * Validate Email Format
    * */
    fun isValidEmail(email: String): Boolean {
        val regex = "^[\\w-_.+]*[\\w-_.]@([\\w-]+\\.)+[\\w]+[\\w]$"
        return email.matches(regex.toRegex())
    }

    /*
    * Validate Phone number contains only number and length should be 10
    * */
    fun isValidMobileNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches(signUpMobile.toRegex())
    }

    /*
    * Validate Phone number contains only number not any special character
    * */
    fun isContainOnlyNumbers(phoneNumber: String): Boolean {
        return phoneNumber.matches(mobile.toRegex())
    }

    /*
    * Validate name contain only alphabet and length at least 2
    * */
    fun isValidName(name: String): Boolean {
        return name.matches("[a-zA-Z ]{2,}".toRegex())
    }

    /*
    * Validate password contains at least a Capital letter, small letter , number and special character
    * */
    fun isValidPassword(password: String): Boolean {
        return if (!password.contains("[A-Z]".toRegex())) {
            false
        } else if (!password.contains("[a-z]".toRegex())) {
            false
        } else if (!password.contains("[0-9]".toRegex())) {
            false
        } else password.contains("[!\"#\$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

}