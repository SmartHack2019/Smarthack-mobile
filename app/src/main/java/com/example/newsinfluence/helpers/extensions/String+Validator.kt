package com.example.newsinfluence.helpers.extensions

import android.util.Patterns
import com.example.newsinfluence.helpers.Constants

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean = this.isNotEmpty() &&
        this.length >= Constants.ValidationRules.PASSWORD_MIN_CHARACTERS

fun String.isValidName(): Boolean = this.isNotEmpty() &&
        Constants.Patterns.NAME_PATTERN.matcher(this).matches()