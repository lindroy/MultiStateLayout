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
import com.lindroy.morestatusview.constants.NULL_RESOURCE_ID
import com.lindroy.morestatusview.constants.STATUS_EMPTY

/**
 * @author Lin
 * @date 2019/12/22
 * @function
 */
class FrameStatusView : FrameLayout {

    private var loadingViewId = 0
    private var errorViewId = 0
    private var noNetworkViewId = 0
    private var emptyView: View? = null
    private var curViewStatus = STATUS_CONTENT
    private var defaultLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    private var viewStatusListener: ((oldStatus: Int, newStatus: Int) -> Unit)? = null
    private var statusParams = MoreStatusView.instance
    private var emptyParams = statusParams.emptyParams

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        context.obtainStyledAttributes(attrs, R.styleable.MoreStatusView, defStyleAttr, 0).apply {
            emptyParams.layoutId = getResourceId(R.styleable.MoreStatusView_msv_emptyView, NULL_RESOURCE_ID)
            loadingViewId = getResourceId(R.styleable.MoreStatusView_msv_loadingView, NULL_RESOURCE_ID)
            errorViewId = getResourceId(R.styleable.MoreStatusView_msv_errorView, NULL_RESOURCE_ID)
            noNetworkViewId = getResourceId(R.styleable.MoreStatusView_msv_noNetworkView, NULL_RESOURCE_ID)
            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    fun showContent(){

    }

    @JvmOverloads
    fun showEmpty(view: View? = null, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams) {
        if (view == null) {
            if (emptyView == null) {
                checkLayoutId(emptyParams.layoutId)
                emptyView = inflateView(emptyParams.layoutId)
            }
        } else {
            emptyView = view
        }
        changeViewStatus(STATUS_EMPTY)
        addView(emptyView, 0, layoutParams)
        emptyView!!.tag = STATUS_EMPTY
        showViewByStatus(STATUS_EMPTY)
    }

    @JvmOverloads
    fun showEmpty(@LayoutRes layoutId: Int, layoutParams: ViewGroup.LayoutParams = defaultLayoutParams) =
        showEmpty(inflateView(layoutId), layoutParams)

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

    private fun checkLayoutId(@LayoutRes layoutId: Int) {
        if (layoutId == NULL_RESOURCE_ID) {
            throw NullPointerException("请先设置该状态视图的布局！")
        }
    }

    private fun showViewByStatus(status: Int) {
        children.forEach {
            it.visibility = if (it.tag == status) View.VISIBLE else View.GONE
        }
    }

}