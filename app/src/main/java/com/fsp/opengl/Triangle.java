package com.fsp.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {
    int mProgram;
    static float triangleCoords[] = {
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
    };
    private FloatBuffer vertexBuffer;
    private String verextShaderCode = "attribute vec4 vPosition;\n" +
            "void main(){" +
            "gl_Position=vPosition;" +
            "}";

    private final String fragmentShaderCode = "precision mediump float;\n" +
            "uniform vec4 vColor;\n" +
            "void main(){\n" +
            "gl_FragColor=vColor;\t\n" +
            "}";


    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        //把这个转化后的语言推动给GPU
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        //创造定点着色器，并且在GPU进行编译
        int shader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(shader, verextShaderCode);
        GLES20.glCompileShader(shader);
        //创造片元着色器，并且在GPU进行编译
        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
        GLES20.glCompileShader(fragmentShader);
        //将片元着色器和定点着色器放在同一程序今次那个管理
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, shader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram);
    }

    float color[] = {1.0f, 1.0f, 1.0f, 1.0f};

    public void onDrawFrame(GL10 gl) {
        GLES20.glUseProgram(mProgram);
        //指针 native的指针，， gup的某个区域
        int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //打开变量，应许对变量的读写
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, vertexBuffer);
        int mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
