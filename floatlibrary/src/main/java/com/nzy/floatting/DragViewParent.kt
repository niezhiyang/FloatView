package com.nzy.floatting

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper

/**
 *  @author niezhiyang
 *  since 2021/6/11
 */
const val TAG = "tag"

/**
 * 记录滑动过的最后的位置
 */

var lastX = -1f
var lastY = -1f

class DragViewParent(context: Context) : FrameLayout(context) {
    private lateinit var dragHelper: ViewDragHelper

    private lateinit var dragView: View
    private val viewWidth = 0
    private val viewHeight = 0

    fun setDragViewChild(view: View, width: Float, height: Float) {
        isClickable = false
        dragView = view

        var layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        addView(view, layoutParams)
        setViewDragHelper()
    }

    private fun setViewDragHelper() {
        dragHelper = ViewDragHelper.create(this, 1.0f, object : ViewDragHelper.Callback() {


            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                val leftBound = paddingLeft
                val rightBound: Int = width - dragView.width - leftBound
                return Math.min(Math.max(left, leftBound), rightBound)
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                val leftBound = paddingTop
                val rightBound: Int = height - dragView.height - dragView.paddingBottom
                return Math.min(Math.max(top, leftBound), rightBound)
            }


            //在边界拖动时回调
            override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {}

            override fun getViewHorizontalDragRange(child: View): Int {
                return measuredWidth - child.measuredWidth
            }

            override fun getViewVerticalDragRange(child: View): Int {
                return measuredHeight - child.measuredHeight
            }

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return dragView === child
            }


            override fun onViewPositionChanged(
                changedView: View,
                left: Int,
                top: Int,
                dx: Int,
                dy: Int
            ) {
                super.onViewPositionChanged(changedView, left, top, dx, dy)
                lastX = changedView.x
                lastY = changedView.y
            }
        })
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            invalidate()
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        restorePosition()

    }

    private fun restorePosition() {
        if (lastX == -1f && lastY == -1f) { // 初始位置
//            lastX = (measuredWidth - (if (dragView.measuredWidth > 0) dragView.measuredWidth else viewWidth).toFloat())
//            lastY = (measuredHeight - (if (dragView.measuredHeight > 0) dragView.measuredHeight else viewHeight).toFloat())
            lastX = (measuredWidth-dragView.measuredWidth) * FloatViewManger.proportionX
            lastY = (measuredHeight-dragView.measuredHeight) * FloatViewManger.proportionY
        }
        dragView.layout(
            lastX.toInt(),
            lastY.toInt(),
            lastX.toInt() + dragView.measuredWidth,
            lastY.toInt() + dragView.measuredHeight
        )
    }


}