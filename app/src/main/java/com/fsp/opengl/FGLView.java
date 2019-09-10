package com.fsp.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class FGLView extends GLSurfaceView {
    public FGLView(Context context) {
        super(context);
    }

    public FGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setRenderer(new FGLRender(this));
        //渲染有两种方式：实时渲染,另外一种渲染模式，
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        //        requestRender();//手动渲染，需要渲染就渲染;
    }
}
