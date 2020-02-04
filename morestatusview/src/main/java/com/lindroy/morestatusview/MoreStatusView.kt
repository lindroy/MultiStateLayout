package com.lindroy.morestatusview

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.lindroy.morestatusview.bean.StatusInfo

/**
 * @author Lin
 * @date 2019/12/22
 * @function
 */
internal const val STATUS_CONTENT = 0

class MoreStatusView internal constructor() {


    companion object {
        const val STATUS_CONTENT = 0x000
        const val STATUS_LOADING = 0x001
        const val STATUS_EMPTY = 0x002
        const val STATUS_ERROR = 0x003
        const val STATUS_NO_NETWORK = 0x004

        internal  var instance = MoreStatusView()

        @JvmStatic
        fun init() = MoreStatusView().apply { instance = this }
    }

    internal val statusViewList = arrayListOf<StatusInfo>()

    internal val emptyParams: StatusInfo
        get() = statusViewList.firstOrNull { it.status == STATUS_EMPTY } ?: StatusInfo(STATUS_EMPTY)

    fun setLoadingView(@LayoutRes layoutId: Int, @IdRes vararg retryViewIds: Int) =
        addStatusView(STATUS_LOADING, layoutId, *retryViewIds)

    fun setErrorView(@LayoutRes layoutId: Int, @IdRes vararg retryViewIds: Int) =
        addStatusView(STATUS_ERROR, layoutId, *retryViewIds)

    fun setNoNetworkView(@LayoutRes layoutId: Int, @IdRes vararg retryViewIds: Int) =
        addStatusView(STATUS_NO_NETWORK, layoutId, *retryViewIds)

    fun setEmptyView(@LayoutRes layoutId: Int, @IdRes vararg retryViewIds: Int) =
        addStatusView(STATUS_EMPTY, layoutId, *retryViewIds)

    fun setStatusView(status: Int, @LayoutRes layoutId: Int, @IdRes vararg retryViewIds: Int) =
        addStatusView(status, layoutId, *retryViewIds)

    private fun addStatusView(status: Int, @LayoutRes layoutId: Int, @IdRes vararg retryViewIds: Int) =
        this.apply { statusViewList.add(StatusInfo(status, layoutId, retryViewIds.toList())) }

    class Builder private constructor() {


        companion object {
            fun init() = Builder()
        }
    }
}