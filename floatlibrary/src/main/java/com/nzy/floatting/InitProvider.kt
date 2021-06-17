package com.nzy.floatting

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri


/**
 *  @author niezhiyang
 *  since 2021/6/17
 */
class InitProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        var application: Context? = context?.applicationContext
        if (application == null) {
            application = getApplicationByReflect()
        }

        FloatViewManger.apply {
            proportionY = 0.5F
            proportionX = 0.5F
            height = 100f.px
            width = 100f.px
        }.init(application as Application)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }


}