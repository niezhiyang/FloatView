package com.nzy.floatview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nzy.floatting.FloatViewManger


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun showChild(view: View) {
        val avatar = FloatViewManger.floatView?.findViewById<View>(R.id.iv_demo);
        avatar?.visibility = View.VISIBLE
    }

    fun hideChild(view: View) {
        val avatar = FloatViewManger.floatView?.findViewById<View>(R.id.iv_demo);
        avatar?.visibility = View.GONE
    }

    fun show(view: View) {
        FloatViewManger.show()
    }

    fun hide(view: View) {
        FloatViewManger.hide()
    }

    fun goNext(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}