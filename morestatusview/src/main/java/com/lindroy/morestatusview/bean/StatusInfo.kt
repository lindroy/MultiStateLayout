package com.lindroy.morestatusview.bean

import androidx.annotation.LayoutRes
import com.lindroy.morestatusview.constants.NULL_RESOURCE_ID

/**
 * @author Lin
 * @date 2020/2/4
 * @function
 */
data class StatusInfo(
    val status: Int = -1,
    @LayoutRes var layoutId: Int = NULL_RESOURCE_ID ,
    val clickViewIds: List<Int> = listOf()
)