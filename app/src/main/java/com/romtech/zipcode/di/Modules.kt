package com.romtech.zipcode.di

import com.romtech.zipcode.main.MainActivityViewModel
import com.romtech.zipcode.main.SearchResultsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { SearchResultsViewModel(get()) }
}