package com.lindroy.sample

import android.os.Bundle
import com.lindroy.sample.base.BaseMenuActivity
import com.lindroy.sample.constants.*
import kotlinx.android.synthetic.main.activity_linear_status.*

class LinearStatusActivity : BaseMenuActivity() {

    override fun showContent() {
        linearStatusView.showContent()
    }

    override fun showLoading() {
        linearStatusView.showLoading()
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> linearStatusView.showContent()
            MENU_EMPTY -> linearStatusView.showEmpty()
            MENU_LOADING -> linearStatusView.showLoading()
            MENU_ERROR -> linearStatusView.showError()
            MENU_NO_NETWORK -> linearStatusView.showNoNetwork()
            MENU_NEED_LOGIN->linearStatusView.showStatusView(STATUS_NEED_LOGIN)
            MENU_NO_COUPON->linearStatusView.showStatusView(STATUS_NO_COUPON)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_status)
        linearStatusView.setOnViewsClickListener(statusViewClickListener)
        linearStatusView.setOnViewStatusChangeListener(statusChangeListener)
    }


}
