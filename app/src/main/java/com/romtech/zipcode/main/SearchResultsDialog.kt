package com.romtech.zipcode.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.romtech.zipcode.CallbacksImpl
import com.romtech.zipcode.R
import com.romtech.zipcode.databinding.SearchResultsDialogBinding
import kotlinx.android.synthetic.main.search_results_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchResultsDialog(private val leadZipCode: String, private val leadClass: String): DialogFragment() {

    private val callbacksImpl: CallbacksImpl by lazy { CallbacksImpl(requireActivity() as MainActivity, searchResultsDialogBinding.root, null) }
    private val searchResultsViewModel: SearchResultsViewModel by viewModel { parametersOf(callbacksImpl, "$leadZipCode:$leadClass") }
    private lateinit var searchResultsDialogBinding: SearchResultsDialogBinding

    init {
        Log.d("JIMX","V="+leadZipCode+"   "+leadClass)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchResultsDialogBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.search_results_dialog, container, false) as SearchResultsDialogBinding
        searchResultsDialogBinding.lifecycleOwner = this
        searchResultsDialogBinding.searchResultsViewModel = searchResultsViewModel
        val builder = AlertDialog.Builder(callbacksImpl.fetchActivity())
        builder.setView(searchResultsDialogBinding.root)
        searchResultsDialogBinding.root.ok_button.setOnClickListener {
            dismiss()
        }
        val dialog = builder.create()
        dialog.window?.decorView?.setBackgroundResource(R.drawable.search_results_alert_bg)
        return searchResultsDialogBinding.root
    }

}