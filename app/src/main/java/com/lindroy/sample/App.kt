package com.lindroy.sample

import android.app.Application
import com.lindroy.morestatusview.MoreStatusView

/**
 * @author Lin
 * @date 2020/2/4
 * @function
 */
class App:Application() {

    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        MoreStatusView.init()
            .setLoadingView(R.layout.status_view_loading)
            .setEmptyView(R.layout.status_view_empty)
            .setErrorView(R.layout.status_view_error,R.id.btnRetry)
    }
}