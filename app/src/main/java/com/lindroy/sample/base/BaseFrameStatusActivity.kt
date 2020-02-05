package com.lindroy.sample.base

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lindroy.sample.R
import com.lindroy.sample.constants.*
import kotlinx.android.synthetic.main.activity_sample.*

/**
 * @author Lin
 * @date 2020/2/5
 * @function
 */
abstract class BaseFrameStatusActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        statusView.setOnViewsClickListener { status, view ->
            when (view.id) {
                R.id.btnError,
                R.id.btnNoNetwork ->{
                    statusView.showLoading()
                    Handler().postDelayed( {
                        statusView.showContent()
                    },2000)
                }
                R.id.btnLogin -> Toast.makeText(this, "点击登录", Toast.LENGTH_LONG).show()
            }
        }
        statusView.setOnViewStatusChangeListener { oldStatus, newStatus ->
            Log.d(com.lindroy.sample.TAG, "oldStatus=$oldStatus,newStatus=$newStatus")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_CONTENT, 1, "内容视图")
        menu.add(0, MENU_LOADING, 1, "加载中视图")
        menu.add(0, MENU_EMPTY, 1, "空数据视图")
        menu.add(0, MENU_ERROR, 1, "错误视图")
        menu.add(0, MENU_NO_NETWORK, 1, "网络断开视图")
        menu.add(0, MENU_NEED_LOGIN, 1, "需要登录视图")
        menu.add(0, MENU_NO_COUPON, 1, "没有优惠券视图")
        return true
    }


}