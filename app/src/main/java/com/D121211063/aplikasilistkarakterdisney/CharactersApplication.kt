package com.D121211063.aplikasilistkarakterdisney

import android.app.Application
import com.D121211063.aplikasilistkarakterdisney.data.AppContainer
import com.D121211063.aplikasilistkarakterdisney.data.DefaultAppContainer

class CharactersApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}