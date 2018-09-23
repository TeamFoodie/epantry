package com.example.teamfoodie.activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamfoodie.epantry.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.List;

public class CustomIngredientAdapter extends BaseAdapter{


    private List<PantryIngredient> ingredientList;
    private LayoutInflater layoutInflater;
    private Context context;
    private DatabaseHandler databaseHelper;

    public CustomIngredientAdapter(Context cont, List<PantryIngredient> list, DatabaseHandler db){
        this.context = cont;
        this.ingredientList = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.databaseHelper = new DatabaseHandler(context);
    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.view_pantry_contents, null);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) view.findViewById(R.id.listIngredientName);
            viewHolder.itemTotal = (TextView) view.findViewById(R.id.listIngredientCurrent);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        PantryIngredient ingredientItem = this.ingredientList.get(position);
        viewHolder.itemName.setText(ingredientItem.getIngredientName());
        viewHolder.itemTotal.setText(String.valueOf(ingredientItem.getCurrentQuantity()) + ingredientItem.getUnitMeasure());


        return view;
    }
    static class ViewHolder {
        TextView itemName;
        TextView itemTotal;
    }


}

