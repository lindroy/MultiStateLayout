package com.lindroy.morestatusview.interfaces

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.children
import com.lindroy.morestatusview.MoreStatusView
import com.lindroy.morestatusview.STATUS_CONTENT
import com.lindroy.morestatusview.constants.*
import com.lindroy.morestatusview.util.inflateView

/**
 * @author Lin
 * @date 2020/2/5
 * @function
 */
internal interface IStatusView {

    var emptyView: View?
    var loadingView: View?
    var errorView: View?
    var noNetworkView: View?
    var currentStatus: Int
    var viewStatusListener: ((oldStatus: Int, newStatus: Int) -> Unit)?
    var clickListener: ((status: Int, view: View) -> Unit)?
    val viewTags: ArrayList<Int>
    private val defaultLayoutParams
        get() = ViewGroup.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
        )

    val emptyInfo
        get() = MoreStatusView.instance.emptyInfo

    val loadingInfo
        get() = MoreStatusView.instance.loadingInfo

    val errorInfo
        get() = MoreStatusView.instance.errorInfo

    val noNetworkInfo
        get() = MoreStatusView.instance.noNetworkInfo

    /**
     * 当前是否是内容视图布局
     */
    val isContent
        get() = currentStatus == STATUS_CONTENT

    /**
     * 当前是否是空视图布局
     */
    val isEmpty: Boolean
        get() = currentStatus == STATUS_EMPTY

    /**
     * 当前是否是错误视图布局
     */
    val isError: Boolean
        get() = currentStatus == STATUS_ERROR

    /**
     * 当前是否是断网视图
     */
    val isNoNetwork: Boolean
        get() = currentStatus == STATUS_NO_NETWORK


    //region 子类对外提供的方法
    /**
     * 显示内容布局
     */
    fun showContent()

    /**
     * 显示加载中视图
     */
    fun showLoading(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String = loadingInfo.hintText
    )

    /**
     * 显示加载中视图
     */
    fun showLoading(
        @LayoutRes layoutId: Int,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes hintTextId: Int = NULL_RESOURCE_ID,
        hintText: String = loadingInfo.hintText
    )

    /**
     * 显示空视图布局
     */
    fun showEmpty(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * 显示空视图布局
     */
    fun showEmpty(
        @LayoutRes layoutId: Int,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * 显示错误视图
     */
    fun showError(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * 显示错误视图
     */
    fun showError(
        @LayoutRes layoutId: Int,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        @IdRes vararg clickViewIds: Int
    )

    /**
     * @see showError
     */
    fun showError(
        @LayoutRes layoutId: Int,
        @IdRes vararg clickViewIds: Int
    ) = showError(layoutId, defaultLayoutParams, *clickViewIds)

    /**
     * 显示断网视图
     */
    fun showNoNetwork(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        vararg clickViewIds: Int
    )

    /**
     * 显示断网视图
     */
    fun showNoNetwork(
        @LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        vararg clickViewIds: Int
    )

    /**
     * @see showNoNetwork
     */
    fun showNoNetwork(
        @LayoutRes layoutId: Int,
        vararg clickViewIds: Int
    ) = showNoNetwork(layoutId, defaultLayoutParams, *clickViewIds)

    /**
     * 显示状态视图
     */
    fun showStatusView(status: Int)

    /**
     * 视图改变监听事件
     * formerStatus：之前的状态
     * curStatus：当前状态
     */
    fun setOnViewStatusChangeListener(listener: (formerStatus: Int, curStatus: Int) -> Unit) {
        viewStatusListener = listener
    }

    /**
     * 设置点击事件
     */
    fun setOnViewsClickListener(clickListener: (status: Int, view: View) -> Unit) {
        this.clickListener = clickListener
    }
    //endregion

    fun ViewGroup.showLoadingView(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int = loadingInfo.hintId,
        hintText: String = ""
    ) {
        if (loadingView == null) {
            loadingView = if (view == null) {
                checkLayoutId(loadingInfo.layoutId)
                context.inflateView(loadingInfo.layoutId)
            } else view
            loadingView!!.tag = STATUS_LOADING
            viewTags.add(STATUS_LOADING)
            addView(loadingView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(loadingView)
                loadingView = view
                loadingView!!.tag = STATUS_LOADING
                addView(loadingView, 0, layoutParams)
            }
        }
        if (hintTextId.isLegalResId) {
            loadingView!!.findViewById<TextView>(hintTextId).text = hintText
        }
        showViewByStatus(STATUS_LOADING)
    }


    fun ViewGroup.showEmptyView(
        view: View? = null,
        layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        vararg clickViewIds: Int
    ) {
        if (emptyView == null) {
            emptyView = if (view == null) {
                checkLayoutId(emptyInfo.layoutId)
                context.inflateView(emptyInfo.layoutId)
            } else view
            emptyView!!.tag = STATUS_EMPTY
            viewTags.add(STATUS_EMPTY)
            addView(emptyView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(loadingView)
                emptyView = view
                emptyView!!.tag = STATUS_EMPTY
                addView(loadingView, 0, layoutParams)
            }
        }
        emptyView!!.setOnChildViewClickListener(
            STATUS_EMPTY,
            if (view == null) emptyInfo.clickViewIds else clickViewIds.toList()
        )
        showViewByStatus(STATUS_EMPTY)
    }

    fun ViewGroup.showContentView() {
        children.forEach {
            it.visibility =
                if (it.tag == STATUS_CONTENT || it.tag !in viewTags) View.VISIBLE else View.GONE
        }
        changeViewStatus(STATUS_CONTENT)
    }

    fun ViewGroup.showErrorView(
        view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        vararg clickViewIds: Int
    ) {
        if (errorView == null) {
            errorView = if (view == null) {
                checkLayoutId(errorInfo.layoutId)
                context.inflateView(errorInfo.layoutId)
            } else view
            errorView!!.tag = STATUS_ERROR
            viewTags.add(STATUS_ERROR)
            addView(errorView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(errorView)
                errorView = view
                errorView!!.tag = STATUS_ERROR
                addView(errorView, 0, layoutParams)
            }
        }
        errorView!!.setOnChildViewClickListener(
            STATUS_ERROR,
            if (view == null) errorInfo.clickViewIds else clickViewIds.toList()
        )
        showViewByStatus(STATUS_ERROR)
    }

    fun ViewGroup.showNoNetworkView(
        view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
        vararg clickViewIds: Int
    ) {
        if (noNetworkView == null) {
            noNetworkView = if (view == null) {
                checkLayoutId(noNetworkInfo.layoutId)
                context.inflateView(noNetworkInfo.layoutId)
            } else view
            noNetworkView!!.tag = STATUS_NO_NETWORK
            viewTags.add(STATUS_NO_NETWORK)
            addView(noNetworkView, 0, layoutParams)
        } else {
            if (view != null) {
                removeView(noNetworkView)
                noNetworkView = view
                noNetworkView!!.tag = STATUS_NO_NETWORK
                addView(noNetworkView, 0, layoutParams)
            }
        }
        noNetworkView!!.setOnChildViewClickListener(
            STATUS_NO_NETWORK,
            if (view == null) noNetworkInfo.clickViewIds else clickViewIds.toList()
        )
        showViewByStatus(STATUS_NO_NETWORK)
    }


    private fun checkLayoutId(@LayoutRes layoutId: Int) {
        if (layoutId == NULL_RESOURCE_ID) {
            throw NullPointerException("请先设置该状态视图的布局！")
        }
    }

    private fun ViewGroup.showViewByStatus(status: Int) {
        children.forEach {
            it.visibility = if (it.tag == status) View.VISIBLE else View.GONE
        }
    }

    fun ViewGroup.showStatusLayout(status: Int) {
        MoreStatusView.instance.getStatusView(status).apply {
            checkLayoutId(layoutId)
            if (status !in viewTags) {
                val statusView = context.inflateView(layoutId).apply { tag = status }
                addView(statusView, 0, defaultLayoutParams)
                viewTags.add(status)
                statusView.setOnChildViewClickListener(status, clickViewIds)
            }
            showViewByStatus(status)
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
        viewStatusListener = null
        clickListener = null
    }

    /**
     * 改变当前视图状态
     */
    private fun changeViewStatus(newStatus: Int) {
        if (currentStatus == newStatus) {
            return
        }
        viewStatusListener?.invoke(currentStatus, newStatus)
        currentStatus = newStatus
    }

    private val Int.isLegalResId
        get() = this != NULL_RESOURCE_ID

}


