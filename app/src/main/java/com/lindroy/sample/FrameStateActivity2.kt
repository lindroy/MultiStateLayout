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
class FrameStateActivity2 : BaseFrameStatusActivity() {
    override fun showLoading() {
        stateView.showLoading(R.layout.status_view_loading2)
    }

    override fun showContent() {
        stateView.showContent()
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> stateView.showContent()
            MENU_LOADING -> stateView.showLoading(R.layout.status_view_loading2,hintTextId = R.id.tvLoading,hintText = "玩命加载中……")
            MENU_EMPTY -> stateView.showEmpty(R.layout.status_view_empty2)
            MENU_ERROR -> stateView.showError(R.layout.status_view_error2,R.id.btnError,clickViewIds = *intArrayOf(R.id.btnError))
            MENU_NO_NETWORK -> stateView.showNoNetwork(hintTextId =R.id.btnNoNetwork,clickViewIds = *intArrayOf(R.id.btnNoNetwork))
            MENU_NEED_LOGIN -> stateView.showStateView(STATE_NEED_LOGIN)
            MENU_NO_COUPON -> stateView.showStateView(STATE_NO_COUPON)
        }
    }

}
