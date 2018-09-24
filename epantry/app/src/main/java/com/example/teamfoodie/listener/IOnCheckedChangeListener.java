package com.example.teamfoodie.listener;


import com.example.teamfoodie.models.ShoppingList;

/*
 * listener fot checking change
 */

public interface IOnCheckedChangeListener {

    void onCheckedChanged(boolean b, ShoppingList shoppingList, int position);
}
