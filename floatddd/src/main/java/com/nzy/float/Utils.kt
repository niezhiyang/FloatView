package com.nzy.float

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

