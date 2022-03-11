package com.romtech.zipcode.main

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.romtech.zipcode.CallbacksImpl
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.core.component.KoinComponent

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(private val callbacks: CallbacksImpl) : AndroidViewModel(callbacks.fetchActivity().application), KoinComponent {
    val searchVisibility = ObservableField(View.GONE)
    private var accumString = ""
    val idTextInputEditText: ObservableField<String> = ObservableField("")

    init {
        callbacks.fetchRootView().id_text_input_edit_text.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callbacks.fetchActivity().onWindowFocusChanged(true)
                callbacks.fetchActivity().onSearchClicked(accumString)

            }
            false
        }
    }

    fun onIdTextInputEditTextChanged(string: CharSequence, start: Int, before: Int, count: Int) {
        accumString = string.toString()
        if (accumString.length > 5) {
            val shortenedString = accumString.substring(0,5)
            callbacks.fetchActivity().onSearchClicked(shortenedString)
        }
    }

    fun onSearchClicked() {
        callbacks.fetchActivity().onSearchClicked(accumString)
    }
}