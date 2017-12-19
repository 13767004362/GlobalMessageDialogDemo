package com.xingen.globalmessagedialog.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xingen.globalmessagedialog.R;
import com.xingen.globalmessagedialog.service.NetWorkService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetWorkService.startService(this);
    }
}
