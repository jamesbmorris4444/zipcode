package com.romtech.zipcode.main

import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.romtech.zipcode.CallbacksImpl
import com.romtech.zipcode.R
import org.koin.core.component.KoinComponent

@Suppress("UNCHECKED_CAST")
class SearchResultsViewModel(callbacks: CallbacksImpl, zipCode: ZipCode) : AndroidViewModel(callbacks.fetchActivity().application), KoinComponent {
    val cityStateClassification = ObservableField("")
    val classificationText = ObservableField(0)
    val searchTitle = ObservableField("")
    init {
        cityStateClassification.set("${zipCode.city}, ${zipCode.state} ::  Lead Class ${zipCode.classification}")
        searchTitle.set("Search Results for ${zipCode.zipCode}:")
        classificationText.set(when (zipCode.classification) {
            "A" -> R.string.immediate_support_a
            "B" -> R.string.immediate_support_b
            "C" -> R.string.immediate_support_c
            "D" -> R.string.immediate_support_d
            else -> R.string.immediate_support_d
        })
    }
}