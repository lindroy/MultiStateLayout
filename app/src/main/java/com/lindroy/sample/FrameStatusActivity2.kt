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
class FrameStatusActivity2 : BaseFrameStatusActivity() {
    override fun showLoading() {
        statusView.showLoading(R.layout.status_view_loading2)
    }

    override fun showContent() {
        statusView.showContent()
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> statusView.showContent()
            MENU_LOADING -> statusView.showLoading(R.layout.status_view_loading2)
            MENU_EMPTY -> statusView.showEmpty(R.layout.status_view_empty2)
            MENU_ERROR -> statusView.showError()
            MENU_NO_NETWORK -> statusView.showNoNetwork()
            MENU_NEED_LOGIN -> statusView.showStatusView(STATUS_NEED_LOGIN)
            MENU_NO_COUPON -> statusView.showStatusView(STATUS_NO_COUPON)
        }
    }

}
