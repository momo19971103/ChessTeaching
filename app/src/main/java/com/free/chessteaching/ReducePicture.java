package com.free.chessteaching;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import androidx.appcompat.app.AppCompatActivity;

public class ReducePicture {

    private Matrix proportionMatrix;
    private Bitmap bmp;
    private Bitmap OriginalNewbmp, ObeyNewbmp;
    int width, height;

    ReducePicture(AppCompatActivity appCompatActivity, int OriginalWindowWidth, int StandardOriginalPictureResources) {
        bmp = BitmapFactory.decodeResource(appCompatActivity.getResources(), StandardOriginalPictureResources);
        //獲得圖片的寬高
        width = bmp.getWidth();
        height = bmp.getHeight();
        float scaleWidth = (float) OriginalWindowWidth / (float) width;
        float scaleHeight = (float) OriginalWindowWidth / (float) height;
        proportionMatrix = new Matrix();
        proportionMatrix.postScale(scaleWidth, scaleHeight);
        OriginalNewbmp = Bitmap.createBitmap(bmp, 0, 0, width, height, proportionMatrix, true);

    }

    public Bitmap ReturnObeyBitmap(AppCompatActivity appCompatActivity, int StandardObeyPictureResources) {
        bmp = BitmapFactory.decodeResource(appCompatActivity.getResources(), StandardObeyPictureResources);
        width = bmp.getWidth();
        height = bmp.getHeight();
        ObeyNewbmp = Bitmap.createBitmap(bmp, 0, 0, width, height, proportionMatrix, true);
        return ObeyNewbmp;
    }

    public Bitmap ReturnStandardBitmap() {
        return OriginalNewbmp;
    }
}
