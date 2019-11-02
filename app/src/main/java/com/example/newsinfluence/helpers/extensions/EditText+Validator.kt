package com.example.newsinfluence.helpers.extensions

import android.support.design.widget.TextInputEditText

fun TextInputEditText.validate(validator: (String) -> Boolean, message: String): Boolean {
    return if (validator(this.text.toString())) {
        this.error = null
        true
    } else {
        this.requestFocus()
        this.error = message
        false
    }
}
