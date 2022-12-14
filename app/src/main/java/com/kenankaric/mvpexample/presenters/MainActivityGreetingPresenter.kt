package com.kenankaric.mvpexample.presenters

import android.widget.TextView

/**
 * We define our class which will conform to the contract we created.
 * It's two parts are:
 * 1. Input data
 * 2. UI components which will be updated.
 *
 * Overriding methods from the contract, we get access to the part of the UI. We then use data from
 * the input and handle it there.
 */
class MainActivityGreetingPresenter(private val greetingMessage: String) : MainActivityPresenter {

    override fun configureGreetingMessage(textView: TextView) {
        textView.text = greetingMessage
    }
}