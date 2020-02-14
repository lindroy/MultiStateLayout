package com.lindroy.sample

import com.lindroy.sample.base.BaseFrameStatusActivity
import com.lindroy.sample.constants.*
import kotlinx.android.synthetic.main.activity_sample.*

/**
 * @author Lin
 * @date 2020/2/5
 * @function
 * @Description
 */
class FrameStateActivity : BaseFrameStatusActivity() {
    override fun showLoading() {
        stateView.showLoading()
    }

    override fun showContent() {
        stateView.showContent()

    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> stateView.showContent()
            MENU_LOADING -> stateView.showLoading()
            MENU_EMPTY -> stateView.showEmpty()
            MENU_ERROR -> stateView.showError()
            MENU_NO_NETWORK -> stateView.showNoNetwork()
            MENU_NEED_LOGIN -> stateView.showStateView(STATE_NEED_LOGIN)
            MENU_NO_COUPON -> stateView.showStateView(STATE_NO_COUPON)
        }
    }

}
