package com.fsp.opengl;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FGLView glSurfaceView;

    @Override
    protected void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        glSurfaceView = findViewById(R.id.glSurfaceView);

    }

    private void initData() {
    ;
    }
}
