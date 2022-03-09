package com.romtech.zipcode.main

import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.romtech.zipcode.CallbacksImpl
import org.koin.core.component.KoinComponent

@Suppress("UNCHECKED_CAST")
class SearchResultsViewModel(callbacks: CallbacksImpl, leadZipCodePlusClass: String) : AndroidViewModel(callbacks.fetchActivity().application), KoinComponent {
    val leadZipCodeAndClass = ObservableField("")
    init {
        val index = leadZipCodePlusClass.indexOf(":")
        val leadZipCode = leadZipCodePlusClass.substring(0, index)
        val leadClass = leadZipCodePlusClass.substring(index + 1)
        leadZipCodeAndClass.set("lead ZipCode $leadZipCode: Lead Class $leadClass")
    }
}