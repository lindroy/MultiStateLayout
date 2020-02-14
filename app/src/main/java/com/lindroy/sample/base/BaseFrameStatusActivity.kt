package com.lindroy.sample.base

import android.os.Bundle
import com.lindroy.sample.R
import kotlinx.android.synthetic.main.activity_sample.*

/**
 * @author Lin
 * @date 2020/2/5
 * @function
 */
abstract class BaseFrameStatusActivity: BaseMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        stateLayout.setOnViewsClickListener(stateViewClickListener)
        stateLayout.setOnViewStateChangeListener(statusChangeListener)
    }

}