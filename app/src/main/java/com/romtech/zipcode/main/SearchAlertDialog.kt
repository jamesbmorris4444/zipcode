package com.romtech.zipcode.main

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.romtech.zipcode.R
import kotlinx.android.synthetic.main.search_alert_dialog.view.*

class SearchAlertDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.search_alert_dialog, null)
            builder.setView(dialogView)
            dialogView.ok_button.setOnClickListener {
                dismiss()
            }
            val dialog = builder.create()
            dialog.window?.decorView?.setBackgroundResource(R.drawable.search_alert_dialog_bg)
            dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}