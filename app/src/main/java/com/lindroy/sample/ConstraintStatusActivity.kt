package com.lindroy.sample

import android.os.Bundle
import com.lindroy.sample.base.BaseMenuActivity
import com.lindroy.sample.constants.*
import kotlinx.android.synthetic.main.activity_constraint_status.*

class ConstraintStatusActivity : BaseMenuActivity() {


    override fun showLoading() {
        statusView.showLoading()
    }

    override fun showContent() {
        statusView.showContent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_status)
        statusView.setOnViewsClickListener(statusViewClickListener)
        statusView.setOnViewStatusChangeListener(statusChangeListener)
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> statusView.showContent()
            MENU_EMPTY -> statusView.showEmpty()
            MENU_LOADING -> statusView.showLoading()
            MENU_ERROR -> statusView.showError()
            MENU_NO_NETWORK -> statusView.showNoNetwork()
            MENU_NEED_LOGIN ->statusView.showStatusView(STATUS_NEED_LOGIN)
            MENU_NO_COUPON ->statusView.showStatusView(STATUS_NO_COUPON)
        }
    }
}
