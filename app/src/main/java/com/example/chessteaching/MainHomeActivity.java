package com.example.chessteaching;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.chessteaching.MainActivity.LeisureMode;
import static com.example.chessteaching.MainActivity.TraditionMode;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        final ImageView imageView = findViewById(R.id.homeimageView);

        final Button traditionbutton = (Button) findViewById(R.id.Traditionbtn);
        traditionbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, MainActivity.class);
                intent.putExtra("Width", imageView.getWidth());
                intent.putExtra("Height", imageView.getHeight());
                intent.putExtra("Mode",TraditionMode);
                startActivity(intent);
                MainHomeActivity.this.setResult(RESULT_OK, intent);
                MainHomeActivity.this.finish();
            }
        });
        final Button leisurebtn = (Button) findViewById(R.id.Leisurebtn);
        leisurebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, MainActivity.class);
                intent.putExtra("Width", imageView.getWidth());
                intent.putExtra("Height", imageView.getHeight());
                intent.putExtra("Mode",LeisureMode);
                startActivity(intent);
                MainHomeActivity.this.setResult(RESULT_OK, intent);
                MainHomeActivity.this.finish();
            }
        });
        final Button elsemodebtn = (Button) findViewById(R.id.elsemodebtn);
        elsemodebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainHomeActivity.this,"日本將棋、國際象棋...敬請期待\n" +
                        "有空再回來補~我時間不夠啦!!!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        final Button reloadbtn = (Button) findViewById(R.id.reloadbtn);
        reloadbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainHomeActivity.this,"記錄槽為空...無法讀取", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        final Button messagebutton = (Button) findViewById(R.id.Messagebtn);
        messagebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //產生視窗物件
                new AlertDialog.Builder(MainHomeActivity.this)
                        .setTitle("關於我們")   		//設定視窗標題
                        // .setIcon(R.mipmap.ic_launcher)	//設定對話視窗圖示
                        .setMessage("哈哈哈~沒有我們啦!\n只有 阿毛 我而已拉~\n" +
                                "哈哈哈ㄏㄨ嗚嗚嗚...\n。･ﾟ･(つд`ﾟ)･ﾟ･\n" +
                                "美術物件圖片甚麼都要自己來\n" +
                                "好忙好忙┳━┳ノ( ' - 'ノ)  \n明明那麼早就開始做\n" +
                                " _(┐「ε:)_時間期限快到了\n" +
                                "快來不及了(╯°Д°)╯ ┻━┻\n" +
                                "想要做到的功能還沒做完\n")	//設定顯示的文字
                        .setPositiveButton("拍拍",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();//dismiss:關閉回傳值 ；cancel:尚未完成工作關閉
                            }
                        })			//設定結束的子視窗
                        .show();		//呈現對話視窗
            }
        });
    }

}
