package com.lindroy.sample

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


private const val MENU_CONTENT = 100
private const val MENU_LOADING = 200
private const val MENU_EMPTY = 300
private const val MENU_ERROR = 400
private const val MENU_NO_NETWORK = 500

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_CONTENT, 1, "内容视图")
        menu.add(0, MENU_LOADING, 1, "加载中视图")
        menu.add(0, MENU_EMPTY, 1, "空数据视图")
        menu.add(0, MENU_ERROR, 1, "错误视图")
        menu.add(0, MENU_NO_NETWORK, 1, "网络断开视图")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_CONTENT -> Log.e("Tag", "内容视图")
            MENU_LOADING -> Log.e("Tag", "内容视图")
            MENU_EMPTY -> Log.e("Tag", "内容视图")
            MENU_ERROR -> Log.e("Tag", "内容视图")
            MENU_NO_NETWORK -> Log.e("Tag", "内容视图")
        }
        return super.onOptionsItemSelected(item)
    }
}