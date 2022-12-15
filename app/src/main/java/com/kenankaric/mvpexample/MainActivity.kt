package com.kenankaric.mvpexample

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.kenankaric.mvpexample.databinding.ActivityMainBinding
import com.kenankaric.mvpexample.presenters.MainActivityGreetingPresenter
import com.kenankaric.mvpexample.presenters.MainActivityInitialPresenter
import com.kenankaric.mvpexample.presenters.MainActivityInitialUiPresenter
import com.kenankaric.mvpexample.presenters.MainActivityPresenter

class MainActivity : AppCompatActivity() {

    /**
     * Using [ActivityMainBinding] we're providing safe access to our UI components
     * by view binding.
     */
    private lateinit var binding: ActivityMainBinding

    companion object {
        /**
         * This is for consistency. It clearly indicates that our message on the UI will either
         * contain name or not. It makes code easier to read.
         */
        private const val EMPTY_NAME = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Here we're inflating our layout.
         * [binding] magically knows which layout belongs to which activity/fragment.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        updateGreetingMessage()
    }

    private fun setupUi() {
        val initialMessage = getString(R.string.greeting_message, EMPTY_NAME)

        /**
         * We created presenter and passed to it necessary data which we want to display on UI.
         */
        val presenter = MainActivityInitialUiPresenter(initialMessage)

        /**
         * Calling such private method, we also passed our presenter which will
         * use data above, and bind it to the UI component it accepts.
         */
        presentInitialState(presenter)
    }

    private fun updateGreetingMessage() {
        binding.etName.addTextChangedListener { name: Editable? ->

            val updatedMessage = getString(R.string.greeting_message, name)
            val presenter = MainActivityGreetingPresenter(updatedMessage)

            presentUpdatedState(presenter)
        }
    }

    private fun presentUpdatedState(presenter: MainActivityPresenter) {
        presenter.configureGreetingMessage(binding.lblGreetingMessage)
    }

    /**
     * Our UI component, passed in as a parameter to our [configureInitialGreetingMessage] method,
     * will use data from our presenter to update it on the screen accordingly.
     */
    private fun presentInitialState(presenter: MainActivityInitialPresenter) {
        presenter.configureInitialGreetingMessage(binding.lblGreetingMessage)
    }
}