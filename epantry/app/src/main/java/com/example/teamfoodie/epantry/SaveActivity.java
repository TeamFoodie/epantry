//package com.example.teamfoodie.epantry;
///*
//SaveActivity show what happened after clicking save button
// */
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.teamfoodie.R;
//import com.example.teamfoodie.database.SQLiteDatabaseDao;
//import com.example.teamfoodie.models.Recipe;
////import com.example.teamfoodie.models.UserRecipe;
//
//import java.util.List;
//
//public class SaveActivity extends AppCompatActivity implements View.OnClickListener {
//    private static final String TAG = "SaveActivity";
//
//    protected TextView idTvContent;
//    protected Button idBtnShow;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_save);
//        initView();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                List<Recipe> userRecipeList = SQLiteDatabaseDao.getInstance().init(SaveActivity.this).queryMainContent();
//                //This is just for display, so we only got the latest one.
//                Log.e(TAG, "run: userRecipeList ==" + userRecipeList.size());
//                final UserRecipe userRecipe = userRecipeList.get(userRecipeList.size() - 1);
//                SaveActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        idTvContent.setText(userRecipe.toString());
//                    }
//                });
//            }
//        }.start();
//    }
//
//    private void initView() {
//        idTvContent = findViewById(R.id.id_tv_content);
//        idBtnShow = findViewById(R.id.id_btn_show);
//        idBtnShow.setOnClickListener(SaveActivity.this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.id_btn_show) {
//            startActivity(new Intent(SaveActivity.this, ShoppingListActivity.class));
//        }
//    }
//}
