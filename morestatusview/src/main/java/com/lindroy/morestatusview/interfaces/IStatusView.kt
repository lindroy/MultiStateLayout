package com.lindroy.morestatusview.interfaces

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
    var curViewStatus: Int
    var viewStatusListener: ((oldStatus: Int, newStatus: Int) -> Unit)?
    var clickListener: ((status: Int, view: View) -> Unit)?
    val viewTags: ArrayList<Int>
    val defaultLayoutParams
        get() = FrameLayout.LayoutParams(
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

    //region 子类对外提供的方法
    /**
     * 显示内容布局
     */
    fun showContent()

    /**
     * 显示加载中视图
     */
    fun showLoading(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)
    /**
     * 显示加载中视图
     */
    fun showLoading(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示空视图布局
     */
    fun showEmpty(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示空视图布局
     */
    fun showEmpty(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示错误视图
     */
    fun showError(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示错误视图
     */
    fun showError(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示断网视图
     */
    fun showNoNetwork(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示断网视图
     */
    fun showNoNetwork(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams)

    /**
     * 显示状态视图
     */
    fun showStatusView(status: Int)

    /**
     * 视图改变监听事件
     */
    fun setOnViewStatusChangeListener(listener: (oldStatus: Int, newStatus: Int) -> Unit) {
        viewStatusListener = listener
    }

    /**
     * 设置点击事件
     */
    fun setOnViewsClickListener(clickListener: (status: Int, view: View) -> Unit) {
        this.clickListener = clickListener
    }
    //endregion

    fun ViewGroup.showLoadingView(view: View? , layoutParams: ViewGroup.LayoutParams) {
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
        showViewByStatus(STATUS_LOADING)
    }


    fun ViewGroup.showEmptyView(view: View? = null,
                                layoutParams: ViewGroup.LayoutParams = defaultLayoutParams
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
        showViewByStatus(STATUS_EMPTY)
    }

    fun ViewGroup.showContentView() {
        children.forEach {
            it.visibility = if (it.tag == STATUS_CONTENT || it.tag !in viewTags) View.VISIBLE else View.GONE
        }
        changeViewStatus(STATUS_CONTENT)
    }

    fun ViewGroup.showErrorView(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
                                vararg clickViewIds: Int) {
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
        setOnViewClickListener(STATUS_ERROR, errorView!!,
            if (clickViewIds.isNotEmpty()) clickViewIds.toList() else errorInfo.clickViewIds)
        showViewByStatus(STATUS_ERROR)
    }

    fun ViewGroup.showNoNetworkView(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
                                    vararg clickViewIds: Int) {
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
        setOnViewClickListener(STATUS_NO_NETWORK, noNetworkView!!,
            if (clickViewIds.isNotEmpty()) clickViewIds.toList() else noNetworkInfo.clickViewIds)
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
                addView(statusView,0,defaultLayoutParams)
                viewTags.add(status)
                setOnViewClickListener(status, statusView, clickViewIds)
            }
            showViewByStatus(status)
        }
    }

    private fun setOnViewClickListener(status: Int, parent: View, retryViewIds: List<Int>) {
        clickListener?.also {
            retryViewIds.forEach { id ->
                parent.findViewById<View>(id).setOnClickListener { view ->
                    it.invoke(status, view)
                }
            }
        }
    }



    /**
     * 改变当前视图状态
     */
    private fun changeViewStatus(newStatus: Int) {
        if (curViewStatus == newStatus) {
            return
        }
        viewStatusListener?.invoke(curViewStatus, newStatus)
        curViewStatus = newStatus
    }

    fun clear() {
        if (viewTags.isNotEmpty()) {
            viewTags.clear()
        }
        viewStatusListener = null
        clickListener = null
    }
}


