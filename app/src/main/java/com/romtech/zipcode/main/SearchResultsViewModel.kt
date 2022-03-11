package com.romtech.zipcode.main

import android.content.Context
import android.util.DisplayMetrics
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.romtech.zipcode.CallbacksImpl
import com.romtech.zipcode.R
import org.koin.core.component.KoinComponent


@Suppress("UNCHECKED_CAST")
class SearchResultsViewModel(val callbacks: CallbacksImpl) : AndroidViewModel(callbacks.fetchActivity().application), KoinComponent {
    val cityStateClassification = ObservableField("")
    val immediateSupportMargin = ObservableField(0)
    val classificationText = ObservableField(R.string.app_name)
    val searchTitle = ObservableField("")
    val pleaseRemindMargin = ObservableField(0)

    fun setZipCode(zipCode: ZipCode) {
        if (zipCode.classification == "D" && zipCode.city.isEmpty()) {
            cityStateClassification.set("Lead Class D")
        } else {
            cityStateClassification.set("${zipCode.city}, ${zipCode.state} >> Lead Class ${zipCode.classification}")
        }
        searchTitle.set("Search Results for ${zipCode.zipCode}:")
        classificationText.set(when (zipCode.classification) {
            "A" -> {
                pleaseRemindMargin.set(convertDpToPixels(25, callbacks.fetchActivity()))
                immediateSupportMargin.set(convertDpToPixels(195, callbacks.fetchActivity()))
                R.string.immediate_support_a
            }
            "B" -> {
                pleaseRemindMargin.set(convertDpToPixels(25, callbacks.fetchActivity()))
                immediateSupportMargin.set(convertDpToPixels(195, callbacks.fetchActivity()))
                R.string.immediate_support_b
            }
            "C" -> {
                pleaseRemindMargin.set(convertDpToPixels(25, callbacks.fetchActivity()))
                immediateSupportMargin.set(convertDpToPixels(165, callbacks.fetchActivity()))
                R.string.immediate_support_c
            }
            "D" -> {
                pleaseRemindMargin.set(convertDpToPixels(25, callbacks.fetchActivity()))
                immediateSupportMargin.set(convertDpToPixels(165, callbacks.fetchActivity()))
                R.string.immediate_support_d
            }
            else -> {
                pleaseRemindMargin.set(convertDpToPixels(25, callbacks.fetchActivity()))
                immediateSupportMargin.set(convertDpToPixels(165, callbacks.fetchActivity()))
                R.string.immediate_support_d
            }
        })
    }

    private fun convertDpToPixels(dp: Int, context: Context): Int {
        return dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }
}