package com.lindroy.multistatelayout.interfaces

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.children
import com.lindroy.multistatelayout.MultiStateLayout
import com.lindroy.multistatelayout.constants.*
import com.lindroy.multistatelayout.util.inflateView

/**
 * @author Lin
 * @date 2020/2/5
 * @function
 */
internal interface IStateLayout {

    var emptyView: View?
    var loadingView: View?
    var errorView: View?
    var noNetworkView: View?
    var currentState: Int
    var viewStateListener: ((formerState: Int, curState: Int) -> Unit)?
    var clickListener: ((state: Int, view: View) -> Unit)?
    val viewTags: ArrayList<Int>
    private val defaultLayoutParams
        get() = ViewGroup.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
        )

    val emptyInfo
        get() = MultiStateLayout.instance.emptyInfo

    val loadingInfo
        get() = MultiStateLayout.instance.loadingInfo

    val errorInfo
        get() = MultiStateLayout.instance.errorInfo

    val noNetworkInfo
        get() = MultiStateLayout.instance.noNetworkInfo

    /**
     * 当前是否是内容视图
     */
    val isContent
        get() = currentState == STATE_CONTENT

    /**
     * 当前是否是加载中视图
     */
    val isLoading
        get() = currentState == STATE_LOADING

    /**
     * 当前是否是空数据视图
     */
    val isEmpty: Boolean
        get() = currentState == STATE_EMPTY

    /**
     * 当前是否是错误视图
     */
    val isError: Boolean
        get() = currentState == STATE_ERROR

    /**
     * 当前是否是断网视图
     */
    val isNoNetwork: Boolean
        get() = currentState == STATE_NO_NETWORK


    //region 子类对外提供的方法
    /**
     * 显示内容布局
     */
    fun showContent()

    /**
     * 显示加载中视图
     * @param view:视图对象
     * @param layoutParams:视图布局参数
     * @param hintTextId:显示提示文字的控件Id
     * @param hintText:提示文字
     */
    fun showLoading(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = loadingInfo.hintId,
        hintText: String? = loadingInfo.hintText
    )

    /**
     * 显示加载中视图
     */
    fun showLoading(
        @LayoutRes layoutId: Int,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String? = loadingInfo.hintText
    )

    /**
     * @see showLoading
     */
    fun showLoading(hintText: String) =
        showLoading(loadingInfo.layoutId, defaultLayoutParams, loadingInfo.hintId, hintText)

    /**
     * @see showLoading
     */
    fun showLoading() =
        showLoading(
            loadingInfo.layoutId,
            defaultLayoutParams,
            loadingInfo.hintId,
            loadingInfo.hintText
        )

    /**
     * 显示空视图布局
     * @param view:视图对象
     * @param layoutParams:视图布局参数
     * @param hintTextId:显示提示文字的控件Id
     * @param hintText:提示文字
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun showEmpty(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = emptyInfo.hintId,
        hintText: String? = emptyInfo.hintText,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * 显示空视图布局
     */
    fun showEmpty(
        @LayoutRes layoutId: Int,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = emptyInfo.hintId,
        hintText: String? = emptyInfo.hintText,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * @see showEmpty
     */
    fun showEmpty(hintText: String) =
        showEmpty(emptyInfo.layoutId, defaultLayoutParams, emptyInfo.hintId, hintText)

    /**
     * @see showEmpty
     */
    fun showEmpty() =
        showEmpty(emptyInfo.layoutId, defaultLayoutParams, emptyInfo.hintId, emptyInfo.hintText)

    /**
     * 显示错误视图
     * @param view:视图对象
     * @param layoutParams:视图布局参数
     * @param hintTextId:显示提示文字的控件Id
     * @param hintText:提示文字
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun showError(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = errorInfo.hintId,
        hintText: String? = errorInfo.hintText,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * 显示错误视图
     */
    fun showError(
        @LayoutRes layoutId: Int,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = errorInfo.hintId,
        hintText: String? = errorInfo.hintText,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * @see showError
     */
    fun showError(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = errorInfo.hintId,
        hintText: String? = errorInfo.hintText,
        @IdRes vararg clickViewIds: Int
    ) = showError(layoutId, defaultLayoutParams, hintTextId, hintText, *clickViewIds)

    /**
     * @see showError
     */
    fun showError(hintText: String) =
        showError(errorInfo.layoutId, errorInfo.hintId, hintText)

    /**
     * @see showError
     */
    fun showError() =
        showError(
            errorInfo.layoutId,
            errorInfo.hintId,
            errorInfo.hintText,
            *errorInfo.clickViewIds.toIntArray()
        )

    /**
     * 显示断网视图
     * @param view:视图对象
     * @param layoutParams:视图布局参数
     * @param hintTextId:显示提示文字的控件Id
     * @param hintText:提示文字
     * @param clickViewIds:需要设置点击事件的控件Id
     */
    fun showNoNetwork(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = emptyInfo.hintId,
        hintText: String? = emptyInfo.hintText,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * 显示断网视图
     */
    fun showNoNetwork(
        @LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = emptyInfo.hintId,
        hintText: String? = emptyInfo.hintText,
        vararg clickViewIds: Int
    )

    /**
     * @see showNoNetwork
     */
    fun showNoNetwork(
        @LayoutRes layoutId: Int,
        @IdRes hintTextId: Int = emptyInfo.hintId,
        hintText: String? = emptyInfo.hintText,
        @IdRes vararg clickViewIds: Int
    ) = showNoNetwork(layoutId, defaultLayoutParams, hintTextId, hintText, *clickViewIds)

    /**
     * @see showNoNetwork
     */
    fun showNoNetwork(hintText: String) =
        showNoNetwork(noNetworkInfo.layoutId, noNetworkInfo.hintId, hintText)

    /**
     * @see showNoNetwork
     */
    fun showNoNetwork() =
        showNoNetwork(
            noNetworkInfo.layoutId,
            noNetworkInfo.hintId,
            noNetworkInfo.hintText,
            *noNetworkInfo.clickViewIds.toIntArray()
        )

    /**
     * 显示请求成功的视图
     * @param isDataEmpty:页面数据是否为空，若为空，则显示空数据视图，否则显示内容视图
     * @return true:显示内容视图；false:显示空数据视图
     */
    fun showSuccessView(isDataEmpty: Boolean) = when (isDataEmpty) {
        true -> {
            showEmpty()
            false
        }
        false -> {
            showContent()
            true
        }
    }

    /**
     * 显示失败视图
     * @param isNoNetwork:是否是断网状态
     */
    fun showFailedView(isNoNetwork:Boolean) = when(isNoNetwork){
        true-> showNoNetwork()
        false-> showError()
    }

    /**
     * 显示自定义状态视图
     */
    fun showStateView(state: Int)

    /**
     * 视图改变监听事件
     * formerState：之前的状态
     * curState：当前状态
     */
    fun setOnViewStateChangeListener(listener: (formerState: Int, curState: Int) -> Unit) {
        viewStateListener = listener
    }

    /**
     * 设置点击事件
     */
    fun setOnViewsClickListener(clickListener: (state: Int, view: View) -> Unit) {
        this.clickListener = clickListener
    }
    //endregion

    fun ViewGroup.showLoadingView(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int = loadingInfo.hintId,
        hintText: String? = null
    ) {
        if (loadingView == null) {
            loadingView = if (view == null) {
                checkLayoutId(loadingInfo.layoutId)
                context.inflateView(loadingInfo.layoutId)
            } else view
            loadingView!!.tag = STATE_LOADING
            viewTags.add(STATE_LOADING)
            addView(loadingView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(loadingView)
                loadingView = view
                loadingView!!.tag = STATE_LOADING
                addView(loadingView, 0, layoutParams)
            }
        }
        if (hintTextId.isLegalResId && hintText != null) {
            loadingView!!.findViewById<TextView>(hintTextId).text = hintText
        }
        showViewByState(STATE_LOADING)
    }


    fun ViewGroup.showEmptyView(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = emptyInfo.hintId,
        hintText: String? = null,
        vararg clickViewIds: Int
    ) {
        if (emptyView == null) {
            emptyView = if (view == null) {
                checkLayoutId(emptyInfo.layoutId)
                context.inflateView(emptyInfo.layoutId)
            } else view
            emptyView!!.tag = STATE_EMPTY
            viewTags.add(STATE_EMPTY)
            addView(emptyView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(loadingView)
                emptyView = view
                emptyView!!.tag = STATE_EMPTY
                addView(emptyView, 0, layoutParams)
            }
        }
        //设置点击事件
        emptyView!!.setOnChildViewClickListener(
            STATE_EMPTY,
            if (view == null) emptyInfo.clickViewIds else clickViewIds.toList()
        )
        //设置提示文字
        if (hintTextId.isLegalResId && hintText != null) {
            emptyView!!.findViewById<TextView>(hintTextId).text = hintText
        }
        showViewByState(STATE_EMPTY)
    }

    fun ViewGroup.showContentView() {
        children.forEach {
            it.visibility =
                if (it.tag == STATE_CONTENT || it.tag !in viewTags) View.VISIBLE else View.GONE
        }
        changeViewStatus(STATE_CONTENT)
    }

    fun ViewGroup.showErrorView(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        vararg clickViewIds: Int
    ) {
        if (errorView == null) {
            errorView = if (view == null) {
                checkLayoutId(errorInfo.layoutId)
                context.inflateView(errorInfo.layoutId)
            } else view
            errorView!!.tag = STATE_ERROR
            viewTags.add(STATE_ERROR)
            addView(errorView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(errorView)
                errorView = view
                errorView!!.tag = STATE_ERROR
                addView(errorView, 0, layoutParams)
            }
        }
        //设置点击事件
        errorView!!.setOnChildViewClickListener(
            STATE_ERROR,
            if (view == null) errorInfo.clickViewIds else clickViewIds.toList()
        )
        //设置提示文字
        if (hintTextId.isLegalResId && hintText != null) {
            errorView!!.findViewById<TextView>(hintTextId).text = hintText
        }
        showViewByState(STATE_ERROR)
    }

    fun ViewGroup.showNoNetworkView(
        view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        vararg clickViewIds: Int
    ) {
        if (noNetworkView == null) {
            noNetworkView = if (view == null) {
                checkLayoutId(noNetworkInfo.layoutId)
                context.inflateView(noNetworkInfo.layoutId)
            } else view
            noNetworkView!!.tag = STATE_NO_NETWORK
            viewTags.add(STATE_NO_NETWORK)
            addView(noNetworkView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(noNetworkView)
                noNetworkView = view
                noNetworkView!!.tag = STATE_NO_NETWORK
                addView(noNetworkView, 0, layoutParams)
            }
        }
        //设置点击事件
        noNetworkView!!.setOnChildViewClickListener(
            STATE_NO_NETWORK,
            if (view == null) noNetworkInfo.clickViewIds else clickViewIds.toList()
        )
        //设置提示文字
        if (hintTextId.isLegalResId && hintText != null) {
            noNetworkView!!.findViewById<TextView>(hintTextId).text = hintText
        }
        showViewByState(STATE_NO_NETWORK)
    }


    private fun checkLayoutId(@LayoutRes layoutId: Int) {
        if (layoutId == NULL_RESOURCE_ID) {
            throw NullPointerException("请先设置该状态视图的布局！")
        }
    }

    private fun ViewGroup.showViewByState(status: Int) {
        children.forEach {
            it.visibility = if (it.tag == status) View.VISIBLE else View.GONE
        }
    }

    fun ViewGroup.showStateLayout(status: Int) {
        MultiStateLayout.instance.getStateView(status).apply {
            checkLayoutId(layoutId)
            if (status !in viewTags) {
                val statusView = context.inflateView(layoutId).apply { tag = status }
                addView(statusView, 0, defaultLayoutParams)
                viewTags.add(status)
                statusView.setOnChildViewClickListener(status, clickViewIds)
            }
            showViewByState(status)
        }
    }

    private fun View.setOnChildViewClickListener(status: Int, childViewIds: List<Int>) {
        clickListener?.also {
            if (childViewIds.isEmpty()) {
                return
            }
            childViewIds.forEach { id ->
                this.findViewById<View>(id).setOnClickListener { view ->
                    it.invoke(status, view)
                }
            }
        }
    }


    fun clear() {
        if (viewTags.isNotEmpty()) {
            viewTags.clear()
        }
        viewStateListener = null
        clickListener = null
    }


    /**
     * 改变当前视图状态
     */
    private fun changeViewStatus(curState: Int) {
        if (currentState == curState) {
            return
        }
        viewStateListener?.invoke(currentState, curState)
        currentState = curState
    }

    private val Int.isLegalResId
        get() = this != NULL_RESOURCE_ID

}


