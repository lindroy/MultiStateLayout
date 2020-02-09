package com.lindroy.sample

import android.os.Bundle
import android.view.MenuItem
import com.lindroy.sample.base.BaseMenuActivity
import com.lindroy.sample.constants.MENU_CONTENT
import com.lindroy.sample.constants.MENU_EMPTY
import com.lindroy.sample.constants.MENU_LOADING
import kotlinx.android.synthetic.main.activity_linear_status.*

class LinearStatusActivity : BaseMenuActivity() {
    override fun onMenuItemClickListener(id: Int) {
        when(id){
            MENU_EMPTY ->linearStatusView.showEmpty()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_status)
        linearStatusView.setOnViewsClickListener(statusViewClickListener)
        linearStatusView.setOnViewStatusChangeListener(statusChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_CONTENT->linearStatusView.showContent()
            MENU_EMPTY ->linearStatusView.showEmpty()
            MENU_LOADING ->linearStatusView.showLoading()
        }
        return super.onOptionsItemSelected(item)
    }

}
