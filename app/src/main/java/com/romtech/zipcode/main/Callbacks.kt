package com.romtech.zipcode

import android.view.View
import androidx.fragment.app.Fragment
import com.romtech.zipcode.main.MainActivity

interface Callbacks {
    fun fetchActivity(): MainActivity
    fun fetchRootView(): View
    fun fetchFragment(): Fragment?
}

class CallbacksImpl(val mainActivity: MainActivity, val rootView: View, val fragment: Fragment? = null) : Callbacks {
    override fun fetchActivity(): MainActivity {
        return mainActivity
    }

    override fun fetchRootView(): View {
        return rootView
    }

    override fun fetchFragment(): Fragment? {
        return fragment
    }
}