package com.lindroy.morestatusview.bean

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.lindroy.morestatusview.constants.NULL_RESOURCE_ID

/**
 * @author Lin
 * @date 2020/2/4
 * @function 状态视图信息
 */
data class StatusInfo(
    val status: Int = -1,   //视图状态
    @LayoutRes var layoutId: Int = NULL_RESOURCE_ID,   //视图布局Id
    @IdRes var hintId: Int = NULL_RESOURCE_ID,
    var hintText: String? = null,
    val clickViewIds: List<Int> = listOf()  //设置点击事件的控件Id
)