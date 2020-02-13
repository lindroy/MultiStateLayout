package com.lindroy.morestatusview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.lindroy.morestatusview.R
import com.lindroy.morestatusview.constants.STATUS_CONTENT
import com.lindroy.morestatusview.interfaces.IStatusView
import com.lindroy.morestatusview.util.inflateView

/**
 * @author Lin
 * @date 2020/2/9
 * @function 可以显示多状态布局的ConstraintLayout
 */
class ConstraintStatusView : ConstraintLayout, IStatusView {

    override var currentStatus: Int = STATUS_CONTENT
    override var viewStatusListener: ((oldStatus: Int, newStatus: Int) -> Unit)? = null
    override var clickListener: ((status: Int, view: View) -> Unit)? = null
    override val viewTags: ArrayList<Int> = arrayListOf()
    override var emptyView: View? = null
    override var loadingView: View? = null
    override var errorView: View? = null
    override var noNetworkView: View? = null

    @SuppressLint("CustomViewStyleable")
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : super(context, attrs, defStyleAttr) {
        context.obtainStyledAttributes(attrs, R.styleable.MoreStatusView, defStyleAttr, 0).apply {
            emptyInfo.layoutId =
                getResourceId(R.styleable.MoreStatusView_msv_emptyView, emptyInfo.layoutId)
            loadingInfo.layoutId =
                getResourceId(R.styleable.MoreStatusView_msv_loadingView, loadingInfo.layoutId)
            errorInfo.layoutId =
                getResourceId(R.styleable.MoreStatusView_msv_errorView, errorInfo.layoutId)
            noNetworkInfo.layoutId =
                getResourceId(R.styleable.MoreStatusView_msv_noNetworkView, noNetworkInfo.layoutId)
            recycle()
        }
    }

    override fun showContent() = showContentView()

    override fun showLoading(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?
    ) = showLoadingView(view, layoutParams, hintTextId, hintText)

    override fun showLoading(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?
    ) = showLoadingView(context.inflateView(layoutId), layoutParams, hintTextId, hintText)

    override fun showEmpty(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showEmptyView(
        context.inflateView(layoutId),
        layoutParams,
        hintTextId,
        hintText,
        *clickViewIds
    )

    override fun showEmpty(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showEmptyView(view, layoutParams, hintTextId, hintText, *clickViewIds)

    override fun showError(
        view: View?,
        layoutParams: ViewGroup.LayoutParams, @IdRes vararg clickViewIds: Int
    ) = showErrorView(view, layoutParams, *clickViewIds)

    override fun showError(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams, @IdRes vararg clickViewIds: Int
    ) = showError(context.inflateView(layoutId), layoutParams, *clickViewIds)

    override fun showNoNetwork(
        view: View?,
        layoutParams: ViewGroup.LayoutParams, @IdRes vararg clickViewIds: Int
    ) = showNoNetworkView(view, layoutParams, *clickViewIds)

    override fun showNoNetwork(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams, @IdRes vararg clickViewIds: Int
    ) = showNoNetworkView(context.inflateView(layoutId), layoutParams, *clickViewIds)

    override fun showStatusView(status: Int) = showStatusLayout(status)

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear()
    }
}