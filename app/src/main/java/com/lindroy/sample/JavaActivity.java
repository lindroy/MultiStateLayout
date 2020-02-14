package com.lindroy.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lindroy.multistatelayout.widget.FrameStateLayout;

/**
 * @author Lin
 * @date 2020/2/9
 * @function
 */
public class JavaActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        FrameStateLayout statusView = findViewById(R.id.stateView);

    }
}
