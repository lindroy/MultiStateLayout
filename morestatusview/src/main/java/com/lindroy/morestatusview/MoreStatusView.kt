package com.lindroy.morestatusview

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

/**
 * @author Lin
 * @date 2019/12/22
 * @function
 */
class MoreStatusView {

    companion object {
        const val STATUS_CONTENT = 0
        const val STATUS_LOADING = 1
        const val STATUS_EMPTY = 2
        const val STATUS_ERROR = 3
        const val STATUS_NO_NETWORK = 4

        fun init() = Builder.init()
    }

    class Builder private constructor() {
        fun setLoadingView(@LayoutRes layoutId: Int,@IdRes vararg retryViewIds:Int) = this

        fun setErrorView(@LayoutRes layoutId: Int,@IdRes vararg retryViewIds:Int) = this

        fun setNoNetworkView(@LayoutRes layoutId: Int,@IdRes vararg retryViewIds:Int) = this

        companion object{
            fun init() = Builder()
        }
    }
}