package com.example.teamfoodie.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;

import com.example.teamfoodie.epantry.R;

public class CaptureActivity extends AppCompatActivity {

    protected ImageView idIvCapture;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_capture);
        initView();
        mSharedPreferences = this.getSharedPreferences("cache",
                Context.MODE_PRIVATE);

        String captureUrl = mSharedPreferences.getString("captureUrl", "");
        byte[] bytes = Base64.decode(captureUrl.getBytes(),1);
        idIvCapture.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
    }

    private void initView() {
        idIvCapture = (ImageView) findViewById(R.id.id_iv_capture);
    }
}
