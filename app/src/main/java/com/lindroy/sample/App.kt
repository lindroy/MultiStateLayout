package com.lindroy.sample

import android.app.Application
import com.lindroy.morestatusview.MoreStatusView
import com.lindroy.sample.constants.STATUS_NEED_LOGIN
import com.lindroy.sample.constants.STATUS_NO_COUPON

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
            .setEmptyView(R.layout.status_view_empty,R.id.ivEmpty)
            .setErrorView(R.layout.status_view_error,R.id.btnError)
            .setNoNetworkView(R.layout.status_view_no_network,R.id.btnNoNetwork)
            .addStatusView(STATUS_NEED_LOGIN,R.layout.status_view_need_login,R.id.btnLogin) //自定义状态视图1：需要登录视图
            .addStatusView(STATUS_NO_COUPON,R.layout.status_view_no_coupon) //自定义状态视图2：没有优惠券视图
    }
}