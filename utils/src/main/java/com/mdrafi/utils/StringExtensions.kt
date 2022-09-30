package com.mdrafi.utils

import android.text.SpannableString
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException

/*
* Encrypt String
* */
fun String?.encryptString(): String {
    val cryptography = Cryptography()
    var encryptedtring = ""
    try {
        cryptography.encryptData(this)?.let { encryptedtring = it }
    } catch (e: NoSuchPaddingException) {
        e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: UnrecoverableEntryException) {
        e.printStackTrace()
    } catch (e: CertificateException) {
        e.printStackTrace()
    } catch (e: KeyStoreException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InvalidAlgorithmParameterException) {
        e.printStackTrace()
    } catch (e: InvalidKeyException) {
        e.printStackTrace()
    } catch (e: NoSuchProviderException) {
        e.printStackTrace()
    } catch (e: BadPaddingException) {
        e.printStackTrace()
    } catch (e: IllegalBlockSizeException) {
        e.printStackTrace()
    }
    return encryptedtring
}

/*
* Decrypt String
* */
fun String?.decryptString(): String {
    val cryptography = Cryptography()
    var decryptedString = ""
    try {
        cryptography.decryptData(this).apply {
            decryptedString = this
        }
    } catch (e: NoSuchPaddingException) {
        e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: UnrecoverableEntryException) {
        e.printStackTrace()
    } catch (e: CertificateException) {
        e.printStackTrace()
    } catch (e: KeyStoreException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InvalidAlgorithmParameterException) {
        e.printStackTrace()
    } catch (e: InvalidKeyException) {
        e.printStackTrace()
    } catch (e: NoSuchProviderException) {
        e.printStackTrace()
    } catch (e: BadPaddingException) {
        e.printStackTrace()
    } catch (e: IllegalBlockSizeException) {
        e.printStackTrace()
    }
    return decryptedString
}

/*
* To underline any text
* Set this return string to text view etc.
* */
fun String?.setUnderline(): SpannableString {
    return SpannableString(this).underline(0, this?.length ?: 0)
}