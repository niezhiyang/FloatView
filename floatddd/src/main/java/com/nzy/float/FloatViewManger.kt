package com.nzy.float

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

/**
 *  @author niezhiyang
 *  since 2021/6/11
 */
object FloatViewManger : Application.ActivityLifecycleCallbacks {
    var height: Float = 0F
    var width: Float = 0F
    var startX: Int = 0
    var startY: Int = 0
    private lateinit var context: Context
    var floatView: View? = null


    fun init(context: Application) {
        this.context = context
        // 注册Activity生命周期的回调监听
        context.registerActivityLifecycleCallbacks(this)
    }


    /**
     * 给每个Activity的DecorView上添加所需要的View
     * 因为使用了ViewDragHelper类 ，所以 找到 android.R.id.content
     * 当咱们 setContentView 之后，会把view设置到上面的android.R.id.content中
     * 之后咱们拿到  android.R.id.content 的第一个child 就是xml的view
     * 然后 android.R.id.content 添加子View DragViewParent，DragViewParent 添加子View xml
     */
    private fun addView(activity: Activity) {
        if (floatView == null) {
            return
        }
        val decorView = activity.window.decorView as ViewGroup
        // 如果添加过 可以从 decorView 中找到  DragViewParent 就不用再继续初始化 DragViewParent
        val tagView = decorView.findViewWithTag<View>(TAG)

        var dragViewParent: DragViewParent
        if (tagView != null) {
            // 证明以前已经把 DragViewParent 添加到过 ContentIdView 里面了
            dragViewParent = tagView as DragViewParent
        } else {
            // 初始化
            dragViewParent = DragViewParent(context)

            val contentView = decorView.findViewById<ViewGroup>(android.R.id.content)

            // 这个就是咱们写setContentView的时候的View
            val xmlView = contentView.getChildAt(0);

            // 把这个View先从 contentView移除
            contentView.removeView(xmlView)

            // 把这个View添加到咱们自定义View中
            dragViewParent.addView(xmlView)


            // 把咱们自定义滑动的View添加到contentView中
            contentView.addView(dragViewParent)

            dragViewParent.tag = TAG
        }



        if (floatView!!.parent != null) {
            // 先移除 用户需要虚浮的View,因为虚浮的View是唯一的，添加过之后必须得移除之后才能添加
            val parentView = floatView!!.parent as ViewGroup
            parentView.removeView(floatView)
        }

        // 把用户需要虚浮的View添加到 咱们自定义滑动DragViewParent中
        dragViewParent.setDragViewChild(floatView!!, width, height)


    }



    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        addView(activity)
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {

    }


}