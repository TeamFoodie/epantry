package com.example.teamfoodie.listener;


import com.example.teamfoodie.models.ShoppingList;

/**
 */

public interface IOnCheckedChangeListener {

    void onCheckedChanged(boolean b, ShoppingList shoppingList, int position);
}
