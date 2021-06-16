package com.nzy.floatview

import android.app.Application
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
    override fun onCreate() {
        super.onCreate()
        val view = View.inflate(this, R.layout.demo_float, null)
        view.setOnClickListener {
            Toast.makeText(applicationContext, "点我干嘛", Toast.LENGTH_SHORT).show()
        }
        FloatViewManger.apply {
            floatView = view
            proportionY = 0.5F
            proportionX = 0.5F
            height = 100f.px
            width = 100f.px
        }.init(this)
    }
}