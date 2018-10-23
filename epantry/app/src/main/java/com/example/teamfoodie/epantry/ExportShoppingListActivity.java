package com.example.teamfoodie.epantry;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.teamfoodie.R;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;

public class ExportShoppingListActivity extends AppCompatActivity implements View.OnClickListener {
    protected Button export;
    protected LinearLayout idLlRoot;

    private ExportShoppingListActivity mainActivity;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=this;
        mContext=this;

        super.setContentView(R.layout.export_shopping_list);
        export=findViewById(R.id.bt_export);
        export.setOnClickListener(mainActivity);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_export:

        }
    }
    private void setFoucs(View view) {
        view.setFocusable(true);
        view.requestFocus();
        view.setFocusableInTouchMode(true);
        view.requestFocusFromTouch();
    }

    public ArrayList<PantryIngredient> createArrayList(){
        ArrayList<PantryIngredient> p=new ArrayList<PantryIngredient>();
        return p;
    }
}
