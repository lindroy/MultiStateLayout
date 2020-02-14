package com.lindroy.sample

import android.os.Bundle
import com.lindroy.sample.base.BaseMenuActivity
import com.lindroy.sample.constants.*
import kotlinx.android.synthetic.main.activity_linear_status.*

class LinearStateActivity : BaseMenuActivity() {

    override fun showContent() {
        linearStateView.showContent()
    }

    override fun showLoading() {
        linearStateView.showLoading()
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> linearStateView.showContent()
            MENU_EMPTY -> linearStateView.showEmpty()
            MENU_LOADING -> linearStateView.showLoading()
            MENU_ERROR -> linearStateView.showError()
            MENU_NO_NETWORK -> linearStateView.showNoNetwork()
            MENU_NEED_LOGIN->linearStateView.showStateView(STATE_NEED_LOGIN)
            MENU_NO_COUPON->linearStateView.showStateView(STATE_NO_COUPON)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_status)
        linearStateView.setOnViewsClickListener(stateViewClickListener)
        linearStateView.setOnViewStateChangeListener(statusChangeListener)
    }


}
