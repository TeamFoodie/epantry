package com.example.teamfoodie.epantry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.models.Ingredient;

import java.util.List;

/*
This class is used to create the outline for the recipe list
outlining variable attributes
in which to streamline the performance
 */
public class CustomRecipeAdapter extends BaseAdapter {

    private List<Ingredient> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomRecipeAdapter(Context aContext, List<Ingredient> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.ingredient_list, null);
            holder = new ViewHolder();
            holder.ingredientName = (TextView) convertView.findViewById(R.id.recipe_ing_name);
            holder.ingredientAmount = (TextView) convertView.findViewById(R.id.recipe_ing_count);
            holder.ingredientType = (TextView) convertView.findViewById(R.id.recipe_ing_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ingredient ingredient = this.listData.get(position);
        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientAmount.setText(String.valueOf(ingredient.getMeasurement()));
        holder.ingredientType.setText(ingredient.getUnitCount());

        return convertView;
    }

    static class ViewHolder {
        TextView ingredientName;
        TextView ingredientAmount;
        TextView ingredientType;
    }

}
