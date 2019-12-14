package com.example.chessteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout constraintLayout;
    ImageView[] chessImg1 = new ImageView[32];
    ImageView checkerBoardImg;
    TextView textView;
    int[] chessposition;
    int chessPictureNum = 0;
    final int[] checkerboard = {
            R.drawable.checkerboardchinas, R.drawable.checkerboardforeign, R.drawable.checkerboardjapans};
    final int[] chess = {
            R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4, R.drawable.r5,
            R.drawable.r6, R.drawable.r7};
    boolean firstTime = true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();//取得視窗屬性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenWidth = dm.widthPixels; //視窗的寬度
        int screenHeight = dm.heightPixels;//視窗高度

        constraintLayout = findViewById(R.id.gameactivity);

        checkerBoardImg = findViewById(R.id.imagecheckerboard);
        checkerBoardImg.setScaleType(ImageView.ScaleType.CENTER);
        ReducePicture reducePicture = new ReducePicture(this, screenWidth, checkerboard[0]);

        checkerBoardImg.setImageBitmap(reducePicture.ReturnStandardBitmap());
        if (firstTime) {
            BuidChess(reducePicture);//創建棋子
            firstTime = false;
        }
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(butOnClickListener);
        final Button button1 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(butOnClickListener1);
        textView = findViewById(R.id.textView);

    }

    void BuidChess(ReducePicture reducePicture) {
        for (int chessNum = 0; chessNum < 14; chessNum++) {
            if (chessNum == 0 || chessNum == 7)
                BuildChessTime(1, chessNum, reducePicture);//帥將
            else if (chessNum == 6 || chessNum == 13)
                BuildChessTime(5, chessNum, reducePicture);//兵卒
            else
                BuildChessTime(2, chessNum, reducePicture);//其餘棋子
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void BuildChessTime(int NumberOfExecutions, int chessNum, ReducePicture reducePicture) {
        //https://lynn5133.pixnet.net/blog/post/460064050-%3C%3Candroid-app%3E%3E%E5%8B%95%E6%85%8B%E6%96%B0%E5%A2%9Eimageview
        for (int i = NumberOfExecutions; i > 0; i--) {
            chessImg1[chessPictureNum] = new ImageView(getApplicationContext());
            Bitmap ConversionChessBitmap= reducePicture.ReturnObeyBitmap(this, chess[chessNum]);
            chessImg1[chessPictureNum].setImageBitmap(ConversionChessBitmap);
            int chessWidth = ConversionChessBitmap.getWidth();
            int chessHeight = ConversionChessBitmap.getHeight();
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(chessWidth, chessHeight);
            chessImg1[chessPictureNum].setOnTouchListener(imgListener);
            constraintLayout.addView(chessImg1[chessPictureNum], layoutParams);
            chessPictureNum++;
        }
    }

    private View.OnTouchListener imgListener = new View.OnTouchListener() {
        private float x, y;    // 原本圖片存在的X,Y軸位置
        private int mx, my; // 圖片被拖曳的X ,Y軸距離長度

        //https://blog.xuite.net/viplab/blog/250768633-%5BAndroid%5D%E9%9A%A8%E6%89%8B%E6%8C%87%E7%A7%BB%E5%8B%95%E7%9A%84ImageView
        //https://www.jianshu.com/p/d2561c971f4c
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {          //判斷觸控的動作
                case MotionEvent.ACTION_DOWN:// 按下圖片時
                    x = event.getX();                  //觸控的X軸位置
                    y = event.getY();                  //觸控的Y軸位
                    break;

                case MotionEvent.ACTION_MOVE:// 移動圖片時
                    //getX()：是獲取當前控件(View)的座標
                    //getRawX()：是獲取相對顯示螢幕左上角的座標
                    /*mx = (int) (event.getRawX() - x);
                    my = (int) (event.getRawY() - y - 3.5 * v.getHeight());//乘3不知道為什麼
                    //v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());

                    RelativeLayout.LayoutParams layoutParams
                            = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.setMargins(mx, my, mx + v.getWidth(), my + v.getHeight());
                    v.setLayoutParams(layoutParams);
                     */
                    RelativeLayout.LayoutParams layoutParams
                            = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.leftMargin = (int) event.getRawX()- (int) v.getWidth();
                    layoutParams.topMargin = (int) event.getRawY()- (int)4.75 * v.getHeight();
                    v.setLayoutParams(layoutParams);

                    break;
            }
            return true;
        }
    };
    private View.OnClickListener butOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            sava();
        }
    };

    void sava() {
        RelativeLayout.LayoutParams layoutParams
                = (RelativeLayout.LayoutParams) chessImg1[31].getLayoutParams();

        int Left = layoutParams.leftMargin;
        int Top = layoutParams.topMargin;
        int Right = layoutParams.rightMargin;
        int Bottom = layoutParams.bottomMargin;
        chessposition = new int[]{Left, Top, Right, Bottom};
    }

    private View.OnClickListener butOnClickListener1 = new View.OnClickListener() {
        public void onClick(View v) {
            textView.setText("" + chessposition[0] + chessposition[1] + chessposition[2] + chessposition[3]);
            chessImg1[31].layout(chessposition[0], chessposition[1], checkerboard[2], chessposition[3]);//Loooper 212 214dosomthing();
            RelativeLayout.LayoutParams layoutParams
                    = (RelativeLayout.LayoutParams) chessImg1[31].getLayoutParams();
            layoutParams.setMargins(chessposition[0], chessposition[1], checkerboard[2], chessposition[3]);
            chessImg1[31].setLayoutParams(layoutParams);

        }
    };
}
