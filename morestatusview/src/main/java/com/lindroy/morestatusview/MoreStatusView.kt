package com.lindroy.morestatusview

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.lindroy.morestatusview.bean.StatusInfo
import com.lindroy.morestatusview.constants.NULL_RESOURCE_ID

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

        internal var instance = MoreStatusView()

        @JvmStatic
        fun init() = MoreStatusView().apply { instance = this }
    }

    private val statusViewList = arrayListOf<StatusInfo>()

    internal val emptyInfo: StatusInfo
        get() = getStatusView(STATUS_EMPTY)

    internal val loadingInfo: StatusInfo
        get() = getStatusView(STATUS_LOADING)

    internal val errorInfo: StatusInfo
        get() = getStatusView(STATUS_ERROR)

    internal val noNetworkInfo: StatusInfo
        get() = getStatusView(STATUS_NO_NETWORK)

    fun setLoadingView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null
    ) = addStatusViewToList(STATUS_LOADING, layoutId, hintTextId, hintText)

    fun setEmptyView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? =null,
        @IdRes vararg clickViewIds: Int
    ) = addStatusViewToList(STATUS_EMPTY, layoutId, hintTextId, hintText, *clickViewIds)

    fun setErrorView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStatusViewToList(STATUS_ERROR, layoutId, hintTextId, hintText, *clickViewIds)

    fun setNoNetworkView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStatusViewToList(STATUS_NO_NETWORK, layoutId, hintTextId, hintText, *clickViewIds)

    fun addStatusView(status: Int, @LayoutRes layoutId: Int, @IdRes vararg clickViewIds: Int) =
        addStatusViewToList(status, layoutId, clickViewIds = *clickViewIds)

    internal fun getStatusView(status: Int) = statusViewList.firstOrNull { it.status == status }
        ?: StatusInfo(status)

    private fun addStatusViewToList(
        status: Int,
        @LayoutRes layoutId: Int,
        @IdRes hintId: Int = NULL_RESOURCE_ID,
        hintText: String? =null,
        @IdRes vararg clickViewIds: Int
    ) =
        this.apply {
            statusViewList.add(
                StatusInfo(
                    status,
                    layoutId,
                    hintId,
                    hintText,
                    clickViewIds.toList()
                )
            )
        }
}