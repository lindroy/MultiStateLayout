package com.lindroy.morestatusview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.lindroy.morestatusview.MoreStatusView
import com.lindroy.morestatusview.R
import com.lindroy.morestatusview.interfaces.IStatusView
import com.lindroy.morestatusview.util.inflateView

/**
 * @author Lin
 * @date 2020/2/6
 * @function
 */
class LinearStatusView : LinearLayoutCompat, IStatusView {

    override var curViewStatus: Int = MoreStatusView.STATUS_CONTENT
    override var viewStatusListener: ((oldStatus: Int, newStatus: Int) -> Unit)? = null
    override var clickListener: ((status: Int, view: View) -> Unit)? = null
    override val viewTags: ArrayList<Int> = arrayListOf()
    override var emptyView: View? = null
    override var loadingView: View? = null
    override var errorView: View? = null
    override var noNetworkView: View? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context,
                attrs: AttributeSet?,
                defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        context.obtainStyledAttributes(attrs, R.styleable.MoreStatusView, defStyleAttr, 0).apply {
            emptyInfo.layoutId = getResourceId(R.styleable.MoreStatusView_msv_emptyView, emptyInfo.layoutId)
            loadingInfo.layoutId = getResourceId(R.styleable.MoreStatusView_msv_loadingView, loadingInfo.layoutId)
            errorInfo.layoutId = getResourceId(R.styleable.MoreStatusView_msv_errorView, errorInfo.layoutId)
            noNetworkInfo.layoutId = getResourceId(R.styleable.MoreStatusView_msv_noNetworkView, noNetworkInfo.layoutId)
            recycle()
        }
    }

    override fun showContent() = showContentView()

    override fun showLoading(view: View? , layoutParams: ViewGroup.LayoutParams) =
        showLoadingView(view, layoutParams)

    override fun showEmpty(layoutId: Int, layoutParams: ViewGroup.LayoutParams) =
        showEmptyView(context.inflateView(layoutId),layoutParams)

    override fun showEmpty(view: View?, layoutParams: ViewGroup.LayoutParams) =
        showEmptyView(view, layoutParams)

    override fun showError(view: View?, layoutParams: ViewGroup.LayoutParams) =
        showErrorView(view, layoutParams)

    override fun showError(layoutId: Int, layoutParams: ViewGroup.LayoutParams)=
        showError(context.inflateView(layoutId),layoutParams)

    override fun showNoNetwork(view: View?, layoutParams: ViewGroup.LayoutParams) =
        showNoNetworkView(view, layoutParams)

    override fun showNoNetwork(layoutId: Int, layoutParams: ViewGroup.LayoutParams) =
        showNoNetworkView(context.inflateView(layoutId), layoutParams)

    override fun showStatusView(status: Int) =
        showStatusLayout(status)

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear()
    }
}