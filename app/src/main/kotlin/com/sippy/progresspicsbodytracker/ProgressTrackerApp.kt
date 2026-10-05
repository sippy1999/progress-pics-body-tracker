package com.sippy.progresspicsbodytracker

import android.app.Application
import android.content.Context

class ProgressTrackerApp : Application() {
    companion object {
        private lateinit var instance: ProgressTrackerApp

        fun getInstance(): ProgressTrackerApp = instance
        fun getContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
