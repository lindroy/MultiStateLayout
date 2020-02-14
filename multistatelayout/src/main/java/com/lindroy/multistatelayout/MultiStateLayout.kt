package com.lindroy.multistatelayout

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.lindroy.multistatelayout.bean.StateInfo
import com.lindroy.multistatelayout.constants.NULL_RESOURCE_ID

/**
 * @author Lin
 * @date 2019/12/22
 * @function
 */

class MultiStateLayout internal constructor() {

    companion object {
        const val STATE_CONTENT = 0x000
        const val STATE_LOADING = 0x001
        const val STATE_EMPTY = 0x002
        const val STATE_ERROR = 0x003
        const val STATE_NO_NETWORK = 0x004

        internal var instance = MultiStateLayout()

        @JvmStatic
        fun init() = MultiStateLayout().apply { instance = this }
    }

    private val stateViewList = arrayListOf<StateInfo>()

    internal val emptyInfo: StateInfo
        get() = getStateView(STATE_EMPTY)

    internal val loadingInfo: StateInfo
        get() = getStateView(STATE_LOADING)

    internal val errorInfo: StateInfo
        get() = getStateView(STATE_ERROR)

    internal val noNetworkInfo: StateInfo
        get() = getStateView(STATE_NO_NETWORK)

    fun setLoadingView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null
    ) = addStateViewToList(STATE_LOADING, layoutId, hintTextId, hintText)

    fun setEmptyView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? =null,
        @IdRes vararg clickViewIds: Int
    ) = addStateViewToList(STATE_EMPTY, layoutId, hintTextId, hintText, *clickViewIds)

    fun setErrorView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStateViewToList(STATE_ERROR, layoutId, hintTextId, hintText, *clickViewIds)

    fun setNoNetworkView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStateViewToList(STATE_NO_NETWORK, layoutId, hintTextId, hintText, *clickViewIds)

    fun addStateView(status: Int, @LayoutRes layoutId: Int, @IdRes vararg clickViewIds: Int) =
        addStateViewToList(status, layoutId, clickViewIds = *clickViewIds)

    internal fun getStateView(status: Int) = stateViewList.firstOrNull { it.state == status }
        ?: StateInfo(status)

    private fun addStateViewToList(
        status: Int,
        @LayoutRes layoutId: Int,
        @IdRes hintId: Int = NULL_RESOURCE_ID,
        hintText: String? =null,
        @IdRes vararg clickViewIds: Int
    ) =
        this.apply {
            stateViewList.add(
                StateInfo(
                    status,
                    layoutId,
                    hintId,
                    hintText,
                    clickViewIds.toList()
                )
            )
        }
}