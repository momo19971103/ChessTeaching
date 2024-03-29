package com.free.chessteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ConstraintLayout constraintLayout;
    ReducePicture reducePicture;
    ProgressDialogUtil progressDialogUtil;
    ImageView[] chessImg1 = new ImageView[32];
    ImageView checkerBoardImg;
    final static int TraditionMode = 1, LeisureMode = 2, ChessbackPicture = 14;
    int WorkAreaWidth, WorkAreaHeight;
    int Mode = 0;
    int chessPictureNum = 0;
    //boolean firstTime = true;
    final int[] checkerboard = {
            R.drawable.checkerboardchinas, R.drawable.checkerboardforeign, R.drawable.checkerboardjapans};
    final int[] chess = {
            R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4, R.drawable.r5,
            R.drawable.r6, R.drawable.r7, R.drawable.back1};
    int[] LeisureLibrary = {0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 6, 6, 6, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 13, 13, 13};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        WorkAreaWidth = intent.getIntExtra("Width", 0);
        WorkAreaHeight = intent.getIntExtra("Height", 0);
        Mode = intent.getIntExtra("Mode", 0);

        DisplayMetrics dm = new DisplayMetrics();//取得視窗屬性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        relativeLayout = findViewById(R.id.gameactivity);
        constraintLayout = findViewById(R.id.ConstraintLayout);

        int screenWidth = dm.widthPixels; //視窗的寬度

        checkerBoardImg = findViewById(R.id.imagecheckerboard);
        checkerBoardImg.setScaleType(ImageView.ScaleType.CENTER);
        reducePicture = new ReducePicture(this, screenWidth, checkerboard[0]);
        checkerBoardImg.setImageBitmap(reducePicture.ReturnStandardBitmap());

        try {
            new GetImage().execute();

        } catch (Exception e) {
            Log.e("ERROR", "" + e);
        }

    }

    /*
        @Override
        public void onWindowFocusChanged(boolean hasFocus) {
            // TODO Auto-generated method stub/
            //https://stackoverflow.com/questions/20547974/how-to-get-programmatically-width-and-height-of-relative-linear-layout-in-andr
            //...

     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.quit) {
            Intent intent = new Intent(MainActivity.this, MainHomeActivity.class);
            startActivity(intent);
            MainActivity.this.setResult(RESULT_OK, intent);
            MainActivity.this.finish();
        }
        if (id == R.id.save) {
            Toast toast = Toast.makeText(this, "不好意思~還無此功能!敬請期待σˋ∀ˊ)σ", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        return true;
    }

    void BuidChess(ReducePicture reducePicture) {
        //WorkAreaWidth = relativeLayout.getWidth();
        //WorkAreaHeight = relativeLayout.getHeight();
        if (Mode == TraditionMode) {
            for (int chessNum = 0; chessNum < 14; chessNum++) {
                if (chessNum == 0 || chessNum == 7)
                    BuildChessTime(1, chessNum, reducePicture);//帥將
                else if (chessNum == 6 || chessNum == 13)
                    BuildChessTime(5, chessNum, reducePicture);//兵卒
                else
                    BuildChessTime(2, chessNum, reducePicture);//其餘棋子
            }
        } else if (Mode == LeisureMode) {

            BuildChessTime(32, ChessbackPicture, reducePicture);//test

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void BuildChessTime(int NumberOfExecutions, int chessNum, ReducePicture reducePicture) {
        //https://lynn5133.pixnet.net/blog/post/460064050-%3C%3Candroid-app%3E%3E%E5%8B%95%E6%85%8B%E6%96%B0%E5%A2%9Eimageview
        for (int i = NumberOfExecutions; i > 0; i--) {

            chessImg1[chessPictureNum] = new ImageView(getApplicationContext());
            chessImg1[chessPictureNum].setId(chessPictureNum);
            Bitmap ConversionChessBitmap = reducePicture.ReturnObeyBitmap(this, chess[chessNum]);
            chessImg1[chessPictureNum].setImageBitmap(ConversionChessBitmap);

            int chessWidth = ConversionChessBitmap.getWidth();
            int chessHeight = ConversionChessBitmap.getHeight();
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(chessWidth, chessHeight);
            double originPointX = 0;
            double originPointY = 0;
            if (Mode == TraditionMode) {
                originPointX = WorkAreaWidth * 0.06;
                originPointY = ((WorkAreaHeight - WorkAreaWidth) * 0.5) + originPointX * 0.2;
                chessImg1[chessPictureNum].setOnTouchListener(imgListener);
            } else if (Mode == LeisureMode) {
                originPointX = WorkAreaWidth * 0.108;
                originPointY = ((WorkAreaHeight - WorkAreaWidth) * 0.5) + originPointX * 0.6;
                chessImg1[chessPictureNum].setLongClickable(true);
                chessImg1[chessPictureNum].setOnLongClickListener(onLongClickListener);
            }
            double Unit = WorkAreaWidth * 0.1;
            ChinasChessGameConfiguration CCGC
                    = new ChinasChessGameConfiguration(originPointX, originPointY, Unit, Mode);
            layoutParams.leftMargin = CCGC.getChessPositionWidth(chessPictureNum);
            layoutParams.topMargin = CCGC.getChessPositionHeight(chessPictureNum);
            relativeLayout.addView(chessImg1[chessPictureNum], layoutParams);
            chessPictureNum++;

        }
    }

    private class GetImage extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            //執行前 設定可以在這邊設定
            super.onPreExecute();

            try {
                if (!MainActivity.this.isFinishing())//xActivity即为本界面的Activity
                {
                    progressDialogUtil = new ProgressDialogUtil();
                    progressDialogUtil.showProgressDialog(MainActivity.this, "整軍備戰...");
                }
            } catch (WindowManager.BadTokenException e) {
                Log.e("ERROR", "" + e);
                //use a log message
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            //執行中 在背景做事情
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //執行中 可以在這邊告知使用者進度
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void v) {
            //執行後 完成背景任務
            super.onPostExecute(v);
            BuidChess(reducePicture);
            progressDialogUtil.dismiss();
            progressDialogUtil.resectAlertDialog();

        }
    }

    private View.OnLongClickListener onLongClickListener = (new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            int randomGetNum = (int) (Math.random() * LeisureLibrary.length);
            Bitmap ConversionChessBitmap =
                    reducePicture.ReturnObeyBitmap(MainActivity.this,
                            chess[LeisureLibrary[randomGetNum]]);
            cutBackLibrary(randomGetNum);

            int ID = v.getId();
            chessImg1[ID].setImageBitmap(ConversionChessBitmap);
            v.setLongClickable(false);
            v.setOnTouchListener(imgListener);
            return true;
        }
    });

    private void cutBackLibrary(int randomGetNum) {
        int moveNum = 0;
        int[] test = new int[LeisureLibrary.length - 1];
        for (int i = 0; i < LeisureLibrary.length - 1; i++) {
            if (i == randomGetNum) {
                moveNum = 1;
            }
            test[i] = LeisureLibrary[i + moveNum];
        }
        LeisureLibrary = test;
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
                    RelativeLayout.LayoutParams layoutParams
                            = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.leftMargin = (int) event.getRawX() - (int) v.getWidth();
                    layoutParams.topMargin = (int) event.getRawY() - (int) 5 * v.getHeight();
                    v.setLayoutParams(layoutParams);
                    String s = layoutParams.leftMargin + "---" + layoutParams.topMargin;
                    Log.d("Tag", s);
                    break;
            }
            return true;
        }
    };
}
