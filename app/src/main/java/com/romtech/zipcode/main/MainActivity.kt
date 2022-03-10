package com.romtech.zipcode.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.romtech.zipcode.CallbacksImpl
import com.romtech.zipcode.R
import com.romtech.zipcode.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private val callbacksImpl: CallbacksImpl by lazy { CallbacksImpl(this, activityMainBinding.root, null) }
    private val mainActivityViewModel: MainActivityViewModel by viewModel { parametersOf(callbacksImpl) }
    private val searchResultsViewModel: SearchResultsViewModel by viewModel { parametersOf(callbacksImpl) }
    private lateinit var activityMainBinding: ActivityMainBinding
    lateinit var zipCodes: HashMap<String, ZipCode>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.lifecycleOwner = this
        activityMainBinding.mainActivityViewModel = mainActivityViewModel
        activityMainBinding.searchResultsViewModel = searchResultsViewModel
        val inputStream: InputStream = resources.openRawResource(R.raw.zipcodes)
        val jsonFile = JSONFile(inputStream)
        zipCodes = jsonFile.readJsonFromFile()
    }

    fun onSearchClicked(accumString: String) {
        hideKeyboard(activityMainBinding.root)
        if (accumString.length == 5) {
            val zip: ZipCode? = zipCodes[accumString]
            zip?.let {
                searchResultsViewModel.setZipCode(it)
            } ?: run {
                searchResultsViewModel.setZipCode(ZipCode(zipCode = accumString, state = "UnknownState", city = "UnknownCity", classification = "D", isFdc = false))
            }
            mainActivityViewModel.searchVisibility.set(View.VISIBLE)
        } else {
            val dialog = SearchAlertDialog()
            dialog.show(supportFragmentManager, null)
        }
    }

    fun hideKeyboard(view: View) {
        if (view == null) return
        val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}