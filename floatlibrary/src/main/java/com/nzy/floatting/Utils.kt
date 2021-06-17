package com.nzy.floatting

import android.app.Application
import android.content.res.Resources
import android.util.TypedValue

/**
 *  @author niezhiyang
 *  since 2021/6/11
 */

val Float.px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

fun getScreenWeith(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getApplicationByReflect():Application ?{
    var app: Application? = null
    try {
        app = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication")
            .invoke(null) as Application
    } catch (e: Exception) {
        e.printStackTrace()
        try {
            app = Class.forName("android.app.ActivityThread").getMethod("currentApplication")
                .invoke(null) as Application
        } catch (ex: Exception) {
            e.printStackTrace()
        }
    }
    return app
}

