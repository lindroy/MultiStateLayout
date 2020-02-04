package com.lindroy.morestatusview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.lindroy.morestatusview.R

/**
 * @author Lin
 * @date 2019/12/22
 * @function
 */
class FrameStatusView :FrameLayout{

    private  val NULL_RESOURCE_ID = -1
    private var loadingViewId =0
    private var errorViewId =0
    private var emptyViewId =0
    private var noNetworkViewId =0

    constructor(context: Context):this(context,null)

    constructor(context:Context,attrs:AttributeSet?):this(context,attrs,0)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr:Int):super(context,attrs,defStyleAttr){
        context.obtainStyledAttributes(attrs, R.styleable.MoreStatusView,defStyleAttr,0).apply {
            emptyViewId = getResourceId(R.styleable.MoreStatusView_msv_emptyView,NULL_RESOURCE_ID)
            loadingViewId = getResourceId(R.styleable.MoreStatusView_msv_loadingView,NULL_RESOURCE_ID)
            errorViewId = getResourceId(R.styleable.MoreStatusView_msv_errorView,NULL_RESOURCE_ID)
            noNetworkViewId = getResourceId(R.styleable.MoreStatusView_msv_noNetworkView,NULL_RESOURCE_ID)
            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

}