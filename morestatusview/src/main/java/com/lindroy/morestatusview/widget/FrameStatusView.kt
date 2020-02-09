package com.lindroy.morestatusview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.view.children
import com.lindroy.morestatusview.MoreStatusView
import com.lindroy.morestatusview.R
import com.lindroy.morestatusview.STATUS_CONTENT
import com.lindroy.morestatusview.constants.*

/**
 * @author Lin
 * @date 2019/12/22
 * @function
 */
class FrameStatusView : FrameLayout {

    private var emptyView: View? = null
    private var loadingView: View? = null
    private var errorView: View? = null
    private var noNetworkView: View? = null
    private var curViewStatus = STATUS_CONTENT
    private val defaultLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    private var viewStatusListener: ((oldStatus: Int, newStatus: Int) -> Unit)? = null
    private var clickListener: ((status: Int, view: View) -> Unit)? = null
    private var statusParams = MoreStatusView.instance
    private val emptyParams = statusParams.emptyInfo
    private val loadingParams = statusParams.loadingInfo
    private val errorParams = statusParams.errorInfo
    private val noNetworkParams = statusParams.noNetworkInfo
    private val viewTags = arrayListOf<Int>()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        context.obtainStyledAttributes(attrs, R.styleable.MoreStatusView, defStyleAttr, 0).apply {
            emptyParams.layoutId = getResourceId(R.styleable.MoreStatusView_msv_emptyView, emptyParams.layoutId)
            loadingParams.layoutId = getResourceId(R.styleable.MoreStatusView_msv_loadingView, loadingParams.layoutId)
            errorParams.layoutId = getResourceId(R.styleable.MoreStatusView_msv_errorView, errorParams.layoutId)
            noNetworkParams.layoutId = getResourceId(R.styleable.MoreStatusView_msv_noNetworkView, noNetworkParams.layoutId)
            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (viewTags.isNotEmpty()){
            viewTags.clear()
        }
        viewStatusListener = null
        clickListener = null
    }

    fun showContent() {
        children.forEach {
            it.visibility = if (it.tag == STATUS_CONTENT || it.tag !in viewTags) View.VISIBLE else View.GONE
        }
        changeViewStatus(STATUS_CONTENT)
    }

    @JvmOverloads
    fun showEmpty(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams) {
        if (emptyView == null) {
            emptyView = if (view == null) {
                checkLayoutId(emptyParams.layoutId)
                inflateView(emptyParams.layoutId)
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

    @JvmOverloads
    fun showEmpty(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams) =
        showEmpty(inflateView(layoutId), layoutParams)

    @JvmOverloads
    fun showLoading(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams) {
        if (loadingView == null) {
            loadingView = if (view == null) {
                checkLayoutId(loadingParams.layoutId)
                inflateView(loadingParams.layoutId)
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

    @JvmOverloads
    fun showLoading(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams) =
        showLoading(inflateView(layoutId), layoutParams)

    @JvmOverloads
    fun showError(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
                  vararg clickViewIds: Int) {
        if (errorView == null) {
            errorView = if (view == null) {
                checkLayoutId(errorParams.layoutId)
                inflateView(errorParams.layoutId)
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
            if (clickViewIds.isNotEmpty()) clickViewIds.toList() else errorParams.clickViewIds)
        showViewByStatus(STATUS_ERROR)
    }

    @JvmOverloads
    fun showError(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
                  vararg clickViewIds: Int) =
        showError(inflateView(layoutId), layoutParams, *clickViewIds)

    @JvmOverloads
    fun showNoNetwork(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
                      vararg clickViewIds: Int) {
        if (noNetworkView == null) {
            noNetworkView = if (view == null) {
                checkLayoutId(noNetworkParams.layoutId)
                inflateView(noNetworkParams.layoutId)
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
            if (clickViewIds.isNotEmpty()) clickViewIds.toList() else noNetworkParams.clickViewIds)
        showViewByStatus(STATUS_NO_NETWORK)
    }

    @JvmOverloads
    fun showNoNetwork(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams,
                      vararg clickViewIds: Int) =
        showNoNetwork(inflateView(layoutId), layoutParams, *clickViewIds)


    fun showStatusView(status: Int) {
        statusParams.getStatusView(status).apply {
            checkLayoutId(layoutId)
            if (status !in viewTags) {
                val statusView = inflateView(layoutId).apply { tag = status }
                addView(statusView)
                viewTags.add(status)
                setOnViewClickListener(status,statusView, clickViewIds)
            }
            showViewByStatus(status)
        }
    }

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

    private fun inflateView(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, null)

    private fun setOnViewClickListener(status: Int, parent: View, retryViewIds: List<Int>) {
        clickListener?.also {
            retryViewIds.forEach { id ->
                parent.findViewById<View>(id).setOnClickListener { view ->
                    it.invoke(status, view)
                }
            }
        }
    }

    private fun checkLayoutId(@LayoutRes layoutId: Int) {
        if (layoutId == NULL_RESOURCE_ID) {
            throw NullPointerException("请先设置该状态视图的布局！")
        }
    }

    private fun showViewByStatus(status: Int) {
        children.forEach {
            it.visibility = if (it.tag == status) View.VISIBLE else View.GONE
        }
        changeViewStatus(status)
    }

}