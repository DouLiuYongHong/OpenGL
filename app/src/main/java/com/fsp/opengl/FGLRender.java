package com.fsp.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FGLRender implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    protected View mFGLView;

    public FGLRender(View view) {
        this.mFGLView = view;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0, 111, 0, 0);
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }


    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mTriangle.onDrawFrame(gl);
    }
}
