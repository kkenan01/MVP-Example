package com.kenankaric.mvpexample.presenters

import android.widget.TextView

/**
 * We define contract which our presenter will conform to.
 * It's main purpose is to, as the name suggest, present something to specific part
 * of our UI
 */
interface MainActivityPresenter {
    fun configureGreetingMessage(textView: TextView)
}