package com.romtech.zipcode.main

import android.os.Bundle
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
    private lateinit var activityMainBinding: ActivityMainBinding
    lateinit var zipCodes: HashMap<String,String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.lifecycleOwner = this
        activityMainBinding.mainActivityViewModel = mainActivityViewModel
        val inputStream: InputStream = resources.openRawResource(R.raw.zipcodes)
        val csvFile = CSVFile(inputStream)
        zipCodes = csvFile.read()
    }
}