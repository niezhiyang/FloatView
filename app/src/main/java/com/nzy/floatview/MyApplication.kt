package com.nzy.floatview

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import com.nzy.floatting.FloatViewManger
import com.nzy.floatting.getScreenHeight
import com.nzy.floatting.getScreenWeith
import com.nzy.floatting.px

/**
 *  @author niezhiyang
 *  since 2021/6/11
 */
class MyApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }
    override fun onCreate() {
        super.onCreate()
        val view = View.inflate(this, R.layout.demo_float, null)
        FloatViewManger.floatView = view


    }
}