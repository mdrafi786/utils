[![](https://jitpack.io/v/mdrafi786/utils.svg)](https://jitpack.io/#mdrafi786/utils)

# **Utils**

Utils is a library which contain uitlity classes for encryption, decryption, DateTime format, Glide, Spannable String and Validation of email, phone number, name etc.

## Sample

https://user-images.githubusercontent.com/94779226/197925033-07ef0f45-7b0c-458b-bcc7-5714f6dfa1be.mp4



## Features

* Encryption
* Decryption
* Date and Time Format
* Load Image From Url With Glide
* Change font, style, color of a text and create link
* Validate name, email, password, phone number

## Installation
1. Add the following to the `settings.gradle` file.
   ```gradle
   dependencyResolutionManagement {
      repositories {
          maven { url 'https://jitpack.io' }
    }}
   ```
2. Add library dependency to the app level `build.gradle` file.
   ```gradle
    dependencies {
	   implementation 'com.github.mdrafi786:utils:$latest_stable_version'
	}
   ```

## Usage

### Encryption Decryption
* To `Encrypt` a string :

  ```kotlin
    val string = "Hello world!"
    string.encryptString()
  ```
* To `Decrypt` a string :

  ```kotlin
   string.decryptString()
  ```
  
### Date Time Format
* Get `Date` from `UTC`:

  ```kotlin
     //"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" - > "dd/MM/YYYY"
     val input = "2011-11-02T02:50:12.208Z"
     findDateFromUTC(input)
  ```
  
* Get `Date` and `Time` from `UTC`:

  ```kotlin
     //"yyyy-MM-dd'T'HH:mm:ss'Z'" - > "dd/MM/YYYY \n hh:mm aa"
     val input = "2011-11-02T02:50:12.208Z"
     findDateAndTimeFromUTC(input)
  ```
* Convert `yyyy-MM-dd'T'HH:mm:ss'Z'` to `MMMM dd, yyyy hh:mm aa`:

  ```kotlin
     val input = "2011-11-02T02:50:12Z"
     findDateTimeInMMddyyFormat(input)
  ```
* Get `Date` from `Timestamp`:

  ```kotlin
     val currentTimeStamp = System.currentTimeMillis()
     findDateTimeInMMddyyFormat(input)
  ```
  
  * Get `Timestamp` from `UTC`:

  ```kotlin
     val input = "2011-11-02T02:50:12.208Z"
     findTimeStampFromDate(input)
  ```
* Get `Time` from `Timestamp`:

  ```kotlin
     // 1628347729-> "HH:mm:ss"
     val currentTimeStamp = System.currentTimeMillis()
     getTimeFromTimestamp(currentTimeStamp)
  ```
* Find time ago from given time like just now, one minute ago etc.

  ```kotlin
     val timestamp = System.currentTimeMillis()
     val string = findTimeAgo(currentTimeStamp)
     output :- just now
  ```
  
### Load Image From URL
* Add `Internet` permission to `Manifest.xml` like this 
   ```xml
    <uses-permission android:name="android.permission.INTERNET" />
   ```
  
* Use `GlideUtils` class to download image and show in `ImageView` like this:
  ```kotlin
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
  ```
  
### Spannable String Utility

![Screenshot_2022-10-26-07-22-48-502_com sample utils](https://user-images.githubusercontent.com/94779226/197917511-9bb19c17-8d8a-41c9-9578-62bba5d20031.jpg)

* `Bold` a string :
  ```kotlin
     val spannableString= SpannableString("I am Groot!")
     spannableString.bold(0, spannableString.length)
  ```
* Change `Color` for particular alphabet in a string :
  ```kotlin
     val spannableString= SpannableString("I am Groot!")
     spannableString.color(0, 5, ContextCompat.getColor(it, R.color.yellow))
  ```
* `Underline` a string :
  ```kotlin
     val spannableString= SpannableString("I am Groot!")
     spannableString.underline(0, spannableString.length)
  ```
* `Strike` a string :
  ```kotlin
     val spannableString= SpannableString("I am Groot!")
     spannableString.strike(5, 10)
  ```
* Change `Font` in a string :
  ```kotlin
     val spannableString= SpannableString("I am Groot!")
     spannableString.fontType(5, this.length, ResourcesCompat.getFont((it), R.font.new_york_regular))
  ```

* Create two `Links` from single string and get seperate `click` like this:

![Screenshot_2022-10-26-07-22-56-934_com sample utils](https://user-images.githubusercontent.com/94779226/197917534-b0659b4c-55d6-4c0d-8928-bd7961a06a77.jpg)
 - Add strings in `strings.xml` as following :
  ```xml
     <string name="i_agree_to_terms_and_conditions_and_privacy_notice"><![CDATA[I 
     agree to Terms & Conditions and Privacy Policy]]></string>
     <string name="privacy_notice_underline"><u>Privacy Policy</u></string>
     <string name="terms_conditions_underline"><u>Terms &amp; Conditions</u></str
     ing>
  ```
  - Use `makeLinks` method :
  ```kotlin
     val spanString = SpannableString(getString(R.string.i_agree_to_terms_and_conditions_and_privacy_notice))
     binding.linkTv.makeLinks(spanString,
     Pair(getString(R.string.terms_conditions_underline), View.OnClickListener {
                Toast.makeText(activity, "Term and condition clicked",
                Toast.LENGTH_SHORT).show()
            }),
     Pair(getString(R.string.privacy_notice_underline), View.OnClickListener {
                Toast.makeText(activity, "Privacy Policy clicked",
                Toast.LENGTH_SHORT).show()
            }),
        )
  ```
  
### Name, Email, Password and Phone Number Validation

* Validate `Name` :
  ```kotlin
     val name = "Rafi"
     val boolean = Validator.isValidName(name)
  ```
* Validate `Email` :
  ```kotlin
     val email = "Rafi"
     val boolean = Validator.isValidEmail(email)
  ```
* Validate `Password` :
  ```kotlin
     val password= "example@gmail.com"
     val boolean = Validator.isValidPassword(password)
  ```
* Validate `PhoneNumber` :
  ```kotlin
     //Validate Phone number contains only number and length should be 10
     val phone= "12345678"
     val boolean = Validator.isValidMobileNumber(phone)
     
     //Validate Phone number contains only number not any special character
     val phone= "1234567$#"
     val boolean = Validator.isContainOnlyNumbers(phone)
  ```
### Thanks for supporting
