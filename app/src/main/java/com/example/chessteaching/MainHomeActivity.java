package com.example.chessteaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        final ImageView imageView = findViewById(R.id.homeimageView);
        final Button button = (Button) findViewById(R.id.Traditionbtu);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, MainActivity.class);
                intent.putExtra("Width", imageView.getWidth());
                intent.putExtra("Height", imageView.getHeight());
                startActivity(intent);
                MainHomeActivity.this.setResult(RESULT_OK, intent);
                MainHomeActivity.this.finish();
            }
        });
    }
}
