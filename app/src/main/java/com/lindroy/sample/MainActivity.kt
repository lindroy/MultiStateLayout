package com.lindroy.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


const val TAG = "MoreStatus"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGlobalSetting.setOnClickListener {
            startActivity(FrameStateActivity::class.java)
        }
        btnCustomSetting.setOnClickListener {
            startActivity(FrameStateActivity2::class.java)
        }
        btnLinear.setOnClickListener {
            startActivity(LinearStateActivity::class.java)
        }
        btnConstrain.setOnClickListener {
            startActivity(ConstraintStateActivity::class.java)
        }
    }

    private fun startActivity(cls: Class<*>?){
        startActivity(Intent(this,cls))

    }

}
