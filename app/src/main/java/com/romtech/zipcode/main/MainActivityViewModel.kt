package com.romtech.zipcode.main

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.romtech.zipcode.CallbacksImpl
import org.koin.core.component.KoinComponent

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(private val callbacks: CallbacksImpl) : AndroidViewModel(callbacks.fetchActivity().application), KoinComponent {
    val searchVisibility = ObservableField(View.GONE)
    private var accumString = ""
    var idTextInputEditText: ObservableField<String> = ObservableField("")

    fun onIdTextInputEditTextChanged(string: CharSequence, start: Int, before: Int, count: Int) {
        accumString = string.toString()
    }

    fun onSearchClicked() {
        callbacks.fetchActivity().onSearchClicked(accumString)
    }
}