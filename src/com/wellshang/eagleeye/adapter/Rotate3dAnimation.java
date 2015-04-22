package com.wellshang.eagleeye.adapter;

import android.view.animation.*;
import android.graphics.*;

public class Rotate3dAnimation extends Animation
{
    private Camera mCamera;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final float mFromDegrees;
    private final boolean mReverse;
    private final float mToDegrees;
    
    public Rotate3dAnimation(final float mFromDegrees, final float mToDegrees, final float mCenterX, final float mCenterY, final float mDepthZ, final boolean mReverse) {
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthZ = mDepthZ;
        this.mReverse = mReverse;
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        final float mFromDegrees = this.mFromDegrees;
        final float mToDegrees = this.mToDegrees;
        final float mCenterX = this.mCenterX;
        final float mCenterY = this.mCenterY;
        final Camera mCamera = this.mCamera;
        final Matrix matrix = transformation.getMatrix();
        mCamera.save();
        if (this.mReverse) {
            mCamera.translate(0.0f, 0.0f, this.mDepthZ * n);
        }
        else {
            mCamera.translate(0.0f, 0.0f, this.mDepthZ * (1.0f - n));
        }
        mCamera.rotateY(mFromDegrees + (mToDegrees - mFromDegrees) * n);
        mCamera.getMatrix(matrix);
        mCamera.restore();
        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX, mCenterY);
    }
    
    public void initialize(final int n, final int n2, final int n3, final int n4) {
        super.initialize(n, n2, n3, n4);
        this.mCamera = new Camera();
    }
}
