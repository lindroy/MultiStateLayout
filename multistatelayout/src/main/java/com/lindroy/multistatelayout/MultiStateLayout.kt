package com.lindroy.multistatelayout

import androidx.annotation.IdRes
import androidx.annotation.IntRange
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

    /**
     * 设置加载中视图
     * @param layoutId:布局Id
     * @param hintTextId:用于显示提示文字的控件Id
     * @param hintText:提示文字
     */
    fun setLoadingView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null
    ) = addStateViewToList(STATE_LOADING, layoutId, hintTextId, hintText)

    /**
     * 设置空数据视图
     * @param layoutId:布局Id
     * @param hintTextId:用于显示提示文字的控件Id
     * @param hintText:提示文字
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun setEmptyView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStateViewToList(STATE_EMPTY, layoutId, hintTextId, hintText, *clickViewIds)

    /**
     * 设置错误视图
     * @param layoutId:布局Id
     * @param hintTextId:用于显示提示文字的控件Id
     * @param hintText:提示文字
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun setErrorView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStateViewToList(STATE_ERROR, layoutId, hintTextId, hintText, *clickViewIds)

    /**
     * 设置网络断开视图
     * @param layoutId:布局Id
     * @param hintTextId:用于显示提示文字的控件Id
     * @param hintText:提示文字
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun setNoNetworkView(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
        @IdRes vararg clickViewIds: Int
    ) = addStateViewToList(STATE_NO_NETWORK, layoutId, hintTextId, hintText, *clickViewIds)

    /**
     * 添加自定义视图
     * @param state:视图状态值，取值时
     * @param layoutId:布局Id
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun addStateView(@IntRange(from = 5L)  state: Int, @LayoutRes layoutId: Int, @IdRes vararg clickViewIds: Int) =
        addStateViewToList(state, layoutId, clickViewIds = *clickViewIds)

    internal fun getStateView(status: Int) = stateViewList.firstOrNull { it.state == status }
        ?: StateInfo(status)

    private fun addStateViewToList(
        status: Int,
        @LayoutRes layoutId: Int,
        @IdRes hintId: Int = NULL_RESOURCE_ID,
        hintText: String? = null,
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