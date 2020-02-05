package com.lindroy.morestatusview.interfaces

import android.view.View
import androidx.annotation.LayoutRes
import com.lindroy.morestatusview.constants.NULL_RESOURCE_ID

/**
 * @author Lin
 * @date 2020/2/5
 * @function
 */
interface IStatusView {
     var noNetworkView: View?
    fun checkLayoutId(@LayoutRes layoutId: Int) {
        if (layoutId == NULL_RESOURCE_ID) {
            throw NullPointerException("请先设置该状态视图的布局！")
        }
    }

}