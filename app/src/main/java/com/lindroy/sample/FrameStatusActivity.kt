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
class FrameStatusActivity : BaseFrameStatusActivity() {
    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> statusView.showContent()
            MENU_LOADING -> statusView.showLoading()
            MENU_EMPTY -> statusView.showEmpty()
            MENU_ERROR -> statusView.showError()
            MENU_NO_NETWORK -> statusView.showNoNetwork()
            MENU_NEED_LOGIN -> statusView.showStatusView(STATUS_NEED_LOGIN)
            MENU_NO_COUPON -> statusView.showStatusView(STATUS_NO_COUPON)
        }
    }

}
