package com.lindroy.multistatelayout.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.lindroy.multistatelayout.R
import com.lindroy.multistatelayout.constants.STATE_CONTENT
import com.lindroy.multistatelayout.interfaces.IStateLayout
import com.lindroy.multistatelayout.util.inflateView

/**
 * @author Lin
 * @date 2020/2/6
 * @function 可以显示多状态布局的LinearLayout
 */
class LinearStateLayout : LinearLayoutCompat, IStateLayout {

    override var currentState: Int = STATE_CONTENT
    override var viewStateListener: ((formerState: Int, curState: Int) -> Unit)? = null
    override var clickListener: ((state: Int, view: View) -> Unit)? = null
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
        context.obtainStyledAttributes(attrs, R.styleable.LinearStateLayout, defStyleAttr, 0).apply {
            emptyInfo.layoutId =
                getResourceId(R.styleable.LinearStateLayout_msl_emptyView, emptyInfo.layoutId)
            loadingInfo.layoutId =
                getResourceId(R.styleable.LinearStateLayout_msl_loadingView, loadingInfo.layoutId)
            errorInfo.layoutId =
                getResourceId(R.styleable.LinearStateLayout_msl_errorView, errorInfo.layoutId)
            noNetworkInfo.layoutId =
                getResourceId(R.styleable.LinearStateLayout_msl_noNetworkView, noNetworkInfo.layoutId)
            recycle()
        }
    }

    override fun showContent() = showContentView()

    override fun showLoading(
        view: View?,
        layoutParams: ViewGroup.LayoutParams, @IdRes hintTextId: Int,
        hintText: String?
    ) = showLoadingView(view, layoutParams, hintTextId, hintText)

    override fun showLoading(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams, @IdRes hintTextId: Int,
        hintText: String?
    ) = showLoadingView(context.inflateView(layoutId), layoutParams, hintTextId, hintText)

    override fun showEmpty(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showEmptyView(context.inflateView(layoutId), layoutParams)

    override fun showEmpty(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showEmptyView(view, layoutParams,hintTextId,hintText, *clickViewIds)

    override fun showError(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showErrorView(view, layoutParams, hintTextId, hintText, *clickViewIds)

    override fun showError(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showError(context.inflateView(layoutId), layoutParams, hintTextId, hintText ,* clickViewIds)

    override fun showNoNetwork(
        view: View?,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showNoNetworkView(view, layoutParams,hintTextId,hintText, *clickViewIds)

    override fun showNoNetwork(
        layoutId: Int,
        layoutParams: ViewGroup.LayoutParams,
        @IdRes hintTextId: Int,
        hintText: String?,
        @IdRes vararg clickViewIds: Int
    ) = showNoNetworkView(context.inflateView(layoutId), layoutParams,hintTextId,hintText, *clickViewIds)

    override fun showStateView(state: Int) =
        showStateLayout(state)

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear()
    }
}