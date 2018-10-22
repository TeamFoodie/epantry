package com.example.teamfoodie.epantry.listAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;
/*
This class is used to create the outline for the recipe list
outlining variable attributes
in which to streamline the performance
 */
public class CustomRecipeListAdapter extends BaseAdapter {

    private List<Recipe> recipeList;
    private List<Recipe> searchList;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomRecipeListAdapter(Context aContext, List<Recipe> recipeList) {
        this.context = aContext;
        this.recipeList = recipeList;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //    Hold the reference to the id of view resource instantiating its context
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.recipe_list_layout, null);
            holder = new ViewHolder();
            holder.recipePicView = (ImageView) convertView.findViewById(R.id.imageView_recipe);
            holder.RecipeNameView = (TextView) convertView.findViewById(R.id.textView_recipeName);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.textView_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Recipe Recipe = this.recipeList.get(position);
        holder.RecipeNameView.setText(Recipe.getRecipeName());
        holder.descriptionView.setText(Recipe.getDescription());

//        if(Recipe.getPhoto() != null){
            holder.recipePicView.setImageResource(Recipe.getPhoto());
//        }


        return convertView;
    }

    //  Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView recipePicView;
        TextView RecipeNameView;
        TextView descriptionView;
    }

    public void filter(String charText, DatabaseHandler db) {
        charText = charText.toLowerCase();
        searchList = new ArrayList<>();
        searchList.addAll(recipeList);
        if (charText.length() == 0) {
            recipeList.clear();
            List<Recipe> fullList = db.loadAllRecipes();
            recipeList.addAll(fullList);
        } else {
            recipeList.clear();
            for (Recipe recipe: searchList) {
                if (recipe.getRecipeName().toLowerCase().contains(charText)) {
                    recipeList.add(recipe);
                }
            }
        }
        notifyDataSetChanged();
    }

}