package com.lindroy.sample;

import android.os.Bundle;

import com.lindroy.morestatusview.widget.LinearStatusView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lin
 * @date 2020/2/9
 * @function
 */
public class JavaActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_status);
        LinearStatusView linearStatusView = findViewById(R.id.linearStatusView);
    }
}
