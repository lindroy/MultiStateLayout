package com.lindroy.sample

import android.os.Bundle
import com.lindroy.sample.base.BaseMenuActivity
import com.lindroy.sample.constants.*
import kotlinx.android.synthetic.main.activity_constraint_status.*

/**
 * @author Lin
 * @date 2020/2/10
 * @function
 * @Description
 */
class ConstraintStateActivity : BaseMenuActivity() {


    override fun showLoading() {
        stateView.showLoading()
    }

    override fun showContent() {
        stateView.showContent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_status)
        stateView.setOnViewsClickListener(stateViewClickListener)
        stateView.setOnViewStateChangeListener(statusChangeListener)
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> stateView.showContent()
            MENU_EMPTY -> stateView.showEmpty()
            MENU_LOADING -> stateView.showLoading()
            MENU_ERROR -> stateView.showError()
            MENU_NO_NETWORK -> stateView.showNoNetwork()
            MENU_NEED_LOGIN ->stateView.showStateView(STATE_NEED_LOGIN)
            MENU_NO_COUPON ->stateView.showStateView(STATE_NO_COUPON)
        }
    }
}
