package com.romtech.zipcode.main

import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.romtech.zipcode.CallbacksImpl
import org.koin.core.component.KoinComponent

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(private val callbacks: CallbacksImpl) : AndroidViewModel(callbacks.fetchActivity().application), KoinComponent {
    private var accumString = ""
    var idTextInputEditText: ObservableField<String> = ObservableField("")
    fun onIdTextInputEditTextChanged(string: CharSequence, start: Int, before: Int, count: Int) {
        accumString = string.toString()
    }
    fun onSearchClicked() {
        if (accumString.length == 5) {
            val zipCode: ZipCode? = callbacks.fetchActivity().zipCodes[accumString]
            var dialog : SearchResultsDialog? = null
            zipCode?.let {
                dialog = SearchResultsDialog(zipCode)
            } ?: run {
                dialog = SearchResultsDialog(ZipCode(zipCode = accumString, state = "UnknownState", city = "UnknownCity", classification = "D", isFdc = false))
            }
            dialog?.show(callbacks.fetchActivity().supportFragmentManager, null)
        } else {
            val dialog = SearchAlertDialog()
            dialog.show(callbacks.fetchActivity().supportFragmentManager, null)
        }
    }
}