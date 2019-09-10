package com.fsp.opengl;

import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;


public class CameraHelper implements Camera.PreviewCallback {

    private int WIDTH = 360;
    private int HEIGHT = 540;
    private Camera mCamera;
    private int mCameraid = 0;
    private Camera.PreviewCallback mPreviewCallback;
    private SurfaceTexture mSurfaceTexture;
    private byte[] buffer;

    public CameraHelper(int cameraId) {

    }

    public void switchCamera() {
        if (mCameraid == Camera.CameraInfo.CAMERA_FACING_BACK) {
            mCameraid = Camera.CameraInfo.CAMERA_FACING_FRONT;
        } else {
            mCameraid = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        stopPreview();
        startPreview(mSurfaceTexture);
    }

    public int getmCameraid() {
        return mCameraid;
    }

    ;

    private void startPreview(SurfaceTexture mSurfaceTexture) {
        try {
            mSurfaceTexture = mSurfaceTexture;
            mCamera = Camera.open(mCameraid);
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPreviewFormat(ImageFormat.NV21);
            parameters.setPreviewSize(WIDTH, HEIGHT);
            mCamera.setParameters(parameters);
            buffer = new byte[WIDTH * HEIGHT * 3 / 2];
            //设置数据缓存区
            mCamera.addCallbackBuffer(buffer);
            mCamera.setPreviewCallbackWithBuffer(this);
            //设置预览画面
            mCamera.setPreviewTexture(mSurfaceTexture);
            mCamera.startPreview();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void stopPreview() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;

        }
    }

    public void setmPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.mPreviewCallback = previewCallback;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (null != mPreviewCallback) {
            mPreviewCallback.onPreviewFrame(data, camera);
        }
        camera.addCallbackBuffer(buffer);
    }
}
