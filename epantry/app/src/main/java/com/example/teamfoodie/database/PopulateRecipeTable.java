package com.example.teamfoodie.database;

//import com.amitshekhar.utils.DatabaseHelper;
import com.example.teamfoodie.R;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.Procedure;
import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class PopulateRecipeTable {

    public static void populateRecipeDatabase(DatabaseHandler db){

        Ingredient ing1 = new Ingredient("Softened butter", 250, "grams");
        Ingredient ing2 = new Ingredient("Caster sugar", 0.75, "cup");
        Ingredient ing3 = new Ingredient("Desiccated coconut", 1, "cup");
        Ingredient ing4 = new Ingredient("vanilla essence", 1, "tsp");
        Ingredient ing5 = new Ingredient("flour", 1.5, "cups");
        Ingredient ing6 = new Ingredient("cocoa powder", 2, "tbsp");
        Ingredient ing7 = new Ingredient("cornflakes", 1.5, "cups");
        Ingredient ing8 = new Ingredient("cocoa powder", 1, "tsp");
        Ingredient ing9 = new Ingredient("icing sugar", 2, "cups");
        Ingredient ing10 = new Ingredient("softened butter", 1, "tbsp");
        Ingredient ing11 = new Ingredient("boiling water", 3, "tbsp");
        Ingredient ing12 = new Ingredient("vanilla essence", .25, "tsp");
        Ingredient ing13 = new Ingredient("walnut halves", 13, " ");

        ArrayList<Ingredient> ingredientarray = new ArrayList<>();
        ingredientarray.add(ing1);
        ingredientarray.add(ing2);
        ingredientarray.add(ing3);
        ingredientarray.add(ing4);
        ingredientarray.add(ing5);
        ingredientarray.add(ing6);
        ingredientarray.add(ing7);
        ingredientarray.add(ing8);
        ingredientarray.add(ing9);
        ingredientarray.add(ing10);
        ingredientarray.add(ing11);
        ingredientarray.add(ing12);
        ingredientarray.add(ing13);

        ArrayList<Procedure> procedureArray = new ArrayList<>();
        Procedure procedure1 = new Procedure("1. Preheat the oven to 180C.");
        Procedure procedure2 = new Procedure("2. Cream the butter and sugar together. Add the coconut and vanilla essence.");
        Procedure procedure3 = new Procedure("3. Sift the four and cocoa and add to the bowl. Mix well and add the cornflakes. Lightly blend.");
        Procedure procedure4 = new Procedure("4. Place spoonfuls of the mixture on a lightly greased baking tray, allowing room for them to spread.");
        Procedure procedure5 = new Procedure("5. Bake for 20 minutes.");
        Procedure procedure6 = new Procedure("6. Allow to cool then spread with chocolate icing.");
        Procedure procedure7 = new Procedure("7. For the icing, sift sugar and cocoa into a bowl. Add butter, then water and vanilla. Beat to a spreadable consistency. Once iced, top biscuits with a walnut half.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);
        procedureArray.add(procedure5);
        procedureArray.add(procedure6);
        procedureArray.add(procedure7);

        Recipe r1 = new Recipe("Afghans", R.drawable.afghans, "Afghans are a kiwi classic", 650,40,1,4,"Comfort Food", ingredientarray, procedureArray);
        db.addHandle(r1);

        //===========================================================================================================
        ing1.setInfo("Mung bean sprouts", 1, "cup");
        ing2.setInfo("White cabbage", .5, "cup");
        ing3.setInfo("Red cabbage", .5, "cup");
        ing4.setInfo("Mushrroms", 2, " ");
        ing5.setInfo("Sliced tomato", 1, "cup");
        ing6.setInfo("Fresh coriander", 1, "small");
        ing7.setInfo("Miso paste", 2, "sachets");
        ing8.setInfo("Boiling water", 1.5, "cups");
        ing9.setInfo("Mint leaves", 1, "2");
        ing10.setInfo("Spring Onion", 1, " ");
        ing11.setInfo("Red chilli", .25, " ");
        ing12.setInfo("Lime juice", .5, " ");
        ing13.setInfo("Fish sauce", 1, "tsp");
        Ingredient ing14 = new Ingredient("Sesame oil", .25, "tsp");

        ingredientarray.clear();
        ingredientarray.add(ing1);
        ingredientarray.add(ing2);
        ingredientarray.add(ing3);
        ingredientarray.add(ing4);
        ingredientarray.add(ing5);
        ingredientarray.add(ing6);
        ingredientarray.add(ing7);
        ingredientarray.add(ing8);
        ingredientarray.add(ing9);
        ingredientarray.add(ing10);
        ingredientarray.add(ing11);
        ingredientarray.add(ing12);
        ingredientarray.add(ing13);
        ingredientarray.add(ing13);
        ingredientarray.add(ing14);

        procedureArray.clear();

        procedure1.setStep("1. Place the sprouts, cabbage, mushroom and tomato in separate mounds around the outside of a bowl, with the coriander in the middle.");
        procedure1.setStep("2. Add the miso soup paste to the boiling water in a small pot and stir to dissolve, then pour over the veges in the bowl. Top with the mint, spring onion and chilli.");
        procedure1.setStep("3. Just as you start to eat, sprinkle the lime juice, fish sauce and sesame oil (use more or less to taste) over the top of the soup.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);

        Recipe r2 = new Recipe("Miso Fresh Bowl", R.drawable.miso_soup, "A light and healthy salad",230,15,1,1,"Vegetarian", ingredientarray, procedureArray);
        db.addHandle(r2);
       //=========================================================================================================

//        ing1.setInfo("Mung bean sprouts", 1, "cup");
//        ing2.setInfo("White cabbage", .5, "cup");
//        ing3.setInfo("Red cabbage", .5, "cup");
//        ing4.setInfo("Mushrroms", 2, " ");
//        ing5.setInfo("Sliced tomato", 1, "cup");
//        ing6.setInfo("Fresh coriander", 1, "small");
//        ing7.setInfo("Miso paste", 2, "sachets");
//        ing8.setInfo("Boiling water", 1.5, "cups");
//        ing9.setInfo("Mint leaves", 1, "2");
//        ing10.setInfo("Spring Onion", 1, " ");
//        ing11.setInfo("Red chilli", .25, " ");
//        ing12.setInfo("Lime juice", .5, " ");
//        ing13.setInfo("Fish sauce", 1, "tsp");
//        ing14.setInfo("Sesame oil", .25, "tsp");
//
//        ingredientarray.clear();
//        ingredientarray.add(ing1);
//        ingredientarray.add(ing2);
//        ingredientarray.add(ing3);
//        ingredientarray.add(ing4);
//        ingredientarray.add(ing5);
//        ingredientarray.add(ing6);
//        ingredientarray.add(ing7);
//        ingredientarray.add(ing8);
//        ingredientarray.add(ing9);
//        ingredientarray.add(ing10);
//        ingredientarray.add(ing11);
//        ingredientarray.add(ing12);
//        ingredientarray.add(ing13);
//        ingredientarray.add(ing13);
//        ingredientarray.add(ing14);
//
//        procedureArray.clear();
//        procedureArray.add("1. Place the sprouts, cabbage, mushroom and tomato in separate mounds around the outside of a bowl, with the coriander in the middle.");
//        procedureArray.add("2. Add the miso soup paste to the boiling water in a small pot and stir to dissolve, then pour over the veges in the bowl. Top with the mint, spring onion and chilli.");
//        procedureArray.add("3. Just as you start to eat, sprinkle the lime juice, fish sauce and sesame oil (use more or less to taste) over the top of the soup.");
//
//        Recipe r3 = new Recipe("Apricot Balls", R.drawable.apricot_balls,"These are quickly",230,30,1,4,"Comfort Food");
//        //=========================================================================================================
//








//        Recipe r4 = new Recipe("Apricot Jam", R.drawable.apricot_jam, "Simple Jam",230,30,1,4,"Comfort Food");
//        Recipe r5 = new Recipe("Basic Biscuits", R.drawable.basic_biscuits, "Make one simple dough",230,30,1,4,"Comfort Food");
//        Recipe r6= new Recipe("Bliss Balls", R.drawable.bliss_balls, "Bliss balls",230,30,1,4,"Comfort Food");
//        Recipe r7= new Recipe("Chelsea Buns", R.drawable.chelsea_buns, "Chelsea Buns",230,30,1,4,"Comfort Food");
//        Recipe r8= new Recipe("Chocolate Cake", R.drawable.chocolate_cake, "Chocolate Cake ",230,30,1,4,"Comfort Food");
//        Recipe r9= new Recipe("Chocolate Gateau", R.drawable.chocolate_gateau, "MMMMMM Gateau",230,30,1,4,"Comfort Food");
//        Recipe r10= new Recipe("Chocolate Meringue", R.drawable.chocolate_meringue_cake, "Meringue what?",230,30,1,4,"Comfort Food");
//        Recipe r11=new Recipe("Chorizo and Tomato Salad", R.drawable.chorizo_and_tomato_salad, "Chorizo what a lovely sausage",230,30,1,4,"Comfort Food");
//        Recipe r12= new Recipe("Crumpets", R.drawable.crumpets, "Ahhh the breakfast classic",230,30,1,4,"Comfort Food");
//        Recipe r13= new Recipe("Lamb and Feta Sliders", R.drawable.lamb_and_feta_sliders, "Sliders?! Miniture burgers!!",230,30,1,4,"Comfort Food");
//        Recipe r14= new Recipe("Potato Salad", R.drawable.potato_salad, "Summer BBQ classic",230,30,1,4,"Comfort Food");
//        Recipe r15= new Recipe("Raspberry Jam", R.drawable.raspberry_jam, "Is the 'p' silent?",230,30,1,4,"Comfort Food");
//        Recipe r16= new Recipe("Soft White Loaf", R.drawable.soft_white_loaf, "Hot bread and the paper",230,30,1,4,"Comfort Food");
//        Recipe r17= new Recipe("Wagyu Burger", R.drawable.wagyu_burgers, "What on earth is a Wagyu?",230,30,1,4,"Comfort Food");


//        db.addHandle(r3);
//        db.addHandle(r4);
//        db.addHandle(r5);
//        db.addHandle(r6);
//        db.addHandle(r7);
//        db.addHandle(r8);
//        db.addHandle(r9);
//        db.addHandle(r10);
//        db.addHandle(r11);
//        db.addHandle(r12);
//        db.addHandle(r13);
//        db.addHandle(r14);
//        db.addHandle(r15);
//        db.addHandle(r16);
//        db.addHandle(r17);

    }
}
