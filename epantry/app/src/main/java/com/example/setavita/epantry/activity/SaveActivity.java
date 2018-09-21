package com.example.setavita.epantry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.setavita.epantry.database.SQLiteDatabaseDao;
import com.example.setavita.epantry.R;
import com.example.setavita.epantry.models.MainContentBean;

import java.util.List;

public class SaveActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SaveActivity";

    protected TextView idTvContent;
    protected Button idBtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_save);
        initView();
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<MainContentBean> mainContentBeanList = SQLiteDatabaseDao.getInstance().init(SaveActivity.this).queryMainContent();
                //This is just for display, so we only got the latest one.
                Log.e(TAG, "run: mainContentBeanList ==" + mainContentBeanList.size());
                final MainContentBean mainContentBean = mainContentBeanList.get(mainContentBeanList.size() - 1);
                SaveActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        idTvContent.setText(mainContentBean.toString());
                    }
                });
            }
        }.start();
    }

    private void initView() {
        idTvContent = findViewById(R.id.id_tv_content);
        idBtnShow = findViewById(R.id.id_btn_show);
        idBtnShow.setOnClickListener(SaveActivity.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_btn_show) {
            startActivity(new Intent(SaveActivity.this, FoodMaterialListActivity.class));
        }
    }
}
