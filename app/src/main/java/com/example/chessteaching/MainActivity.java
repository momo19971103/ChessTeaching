package com.example.chessteaching;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView chessImg;
    ImageView checkerBoardImg;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();//取得視窗屬性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenWidth = dm.widthPixels; //視窗的寬度
        int screenHeight = dm.heightPixels;//視窗高度


        final int[] checkerboard = {
                R.drawable.checkerboardchinas, R.drawable.checkerboardforeign, R.drawable.checkerboardjapans};
        final int[] chess = {
                R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
                R.drawable.b7, R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4, R.drawable.r5,
                R.drawable.r6, R.drawable.r7};
        checkerBoardImg = findViewById(R.id.imagecheckerboard);
        checkerBoardImg.setScaleType(ImageView.ScaleType.CENTER);
        checkerBoardImg.setImageResource(checkerboard[0]);



        //int getcheckerWidth = checkerBoardImg.getWidth();
        //int getcheckerHight = checkerBoardImg.getHeight();
        //checkerBoardImg.buildDrawingCache();
        //Bitmap bmp=checkerBoardImg.getDrawingCache();
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.checkerboardchinas);
        //獲得圖片的寬高
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        float scaleWidth = (float)screenWidth/(float)width;
        float scaleHeight = (float) screenWidth/(float)height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbm = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix,true);
        checkerBoardImg.setImageBitmap(newbm);


        chessImg = findViewById(R.id.imagechess);
        chessImg.setImageResource(chess[1]);
        chessImg.setOnTouchListener(imgListener);
    }

    private View.OnTouchListener imgListener = new View.OnTouchListener() {//https://blog.xuite.net/viplab/blog/250768633-%5BAndroid%5D%E9%9A%A8%E6%89%8B%E6%8C%87%E7%A7%BB%E5%8B%95%E7%9A%84ImageView
        private float x, y;    // 原本圖片存在的X,Y軸位置
        private int mx, my; // 圖片被拖曳的X ,Y軸距離長度

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // Log.e("View", v.toString());
            switch (event.getAction()) {          //判斷觸控的動作

                case MotionEvent.ACTION_DOWN:// 按下圖片時
                    x = event.getX();                  //觸控的X軸位置
                    y = event.getY();                  //觸控的Y軸位置

                case MotionEvent.ACTION_MOVE:// 移動圖片時

                    //getX()：是獲取當前控件(View)的座標

                    //getRawX()：是獲取相對顯示螢幕左上角的座標
                    mx = (int) (event.getRawX() - x);
                    my = (int) (event.getRawY() - y);
                    v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                    break;
            }
            Log.e("address", mx + "~~" + my); // 記錄目前位置
            return true;
        }
    };

}
