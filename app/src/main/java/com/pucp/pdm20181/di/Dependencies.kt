package com.pucp.pdm20181.di

import android.content.Context

class Dependencies {
    companion object {
        @JvmStatic private var _instance: Dependencies? = null
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): Dependencies {
            if (_instance == null) {
                _instance = Dependencies(context)
            }
            return _instance!!
        }
    }

    constructor(context: Context) {

    }
}