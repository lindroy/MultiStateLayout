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
        stateLayout.showLoading(R.layout.state_view_loading2)
    }

    override fun showContent() {
        stateLayout.showContent()
    }

    override fun onMenuItemClickListener(id: Int) {
        when (id) {
            MENU_CONTENT -> stateLayout.showContent()
            MENU_LOADING -> stateLayout.showLoading(R.layout.state_view_loading2,hintTextId = R.id.tvLoading,hintText = "玩命加载中……")
            MENU_EMPTY -> stateLayout.showEmpty(R.layout.state_view_empty2)
            MENU_ERROR -> stateLayout.showError(R.layout.state_view_error2,R.id.btnError,clickViewIds = *intArrayOf(R.id.btnError))
            MENU_NO_NETWORK -> stateLayout.showNoNetwork(hintTextId =R.id.btnNoNetwork,clickViewIds = *intArrayOf(R.id.btnNoNetwork))
            MENU_NEED_LOGIN -> stateLayout.showStateView(STATE_NEED_LOGIN)
            MENU_NO_COUPON -> stateLayout.showStateView(STATE_NO_COUPON)
        }
    }

}
