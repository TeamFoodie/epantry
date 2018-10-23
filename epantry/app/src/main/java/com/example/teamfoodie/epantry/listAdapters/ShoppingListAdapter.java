package com.example.teamfoodie.epantry.listAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.teamfoodie.R;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.ShoppingList;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.FoodMaterialViewHolder> {

    private final String TAG = "ShoppingListAdapter";

    private List<PantryIngredient> mItemList;
    private IOnCheckedChangeListener mIOnCheckedChangeListener;

    public void setIOnCheckedChangeListener(IOnCheckedChangeListener iOnCheckedChangeListener) {
        this.mIOnCheckedChangeListener = iOnCheckedChangeListener;
    }

    public interface IOnCheckedChangeListener {

        void onCheckedChanged(boolean b, ShoppingList shoppingList, int position);
    }

    public class FoodMaterialViewHolder extends RecyclerView.ViewHolder {
        CheckBox idCb;

        public FoodMaterialViewHolder(View v) {
            super(v);
            idCb = v.findViewById(R.id.id_cb);
        }

    }


    public ShoppingListAdapter(List<PantryIngredient> itemList) {

        mItemList = itemList;
    }

    @Override
    public ShoppingListAdapter.FoodMaterialViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopping_list_item, viewGroup, false);

        return new ShoppingListAdapter.FoodMaterialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMaterialViewHolder holder, final int position) {
        final PantryIngredient shoppingList = mItemList.get(position);
       // String desc = shoppingList.getCurrentQuantity() + " " + shoppingList.getUnitMeasure() + " " + shoppingList.getIngredientName();
        String desc = shoppingList.getTotalQuantity()-shoppingList.getCurrentQuantity() + " " + shoppingList.getUnitMeasure() + " " + shoppingList.getIngredientName();
//        holder.idCb.setChecked(shoppingList.set());
        holder.idCb.setText(desc);
//        holder.idCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                shoppingList.setChecked(b);
//                mItemList.set(position, shoppingList);
//                if (mIOnCheckedChangeListener != null) {
//                    mIOnCheckedChangeListener.onCheckedChanged(b, shoppingList, position);
//                }
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    /**
     * update data
     *
     * @param itemList
     */
    public void update(List<PantryIngredient> itemList) {
        mItemList.clear();
        mItemList.addAll(itemList);
        System.out.println("IN ITEM LIST:"+itemList.size());
        notifyDataSetChanged();
    }
}
