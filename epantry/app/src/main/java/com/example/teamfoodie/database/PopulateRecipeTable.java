package com.example.teamfoodie.database;


import com.example.teamfoodie.R;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.PantryIngredient;
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

        Recipe r1 = new Recipe("Afghans", String.valueOf(R.drawable.afghans),"Afghans are a kiwi classic", 650,40,1,4,"Comfort Food", ingredientarray, procedureArray);

//        r1.setPhoto(String.valueOf(R.drawable.afghans));
        db.addHandle(r1);

//        ===========================================================================================================
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
//
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
        procedure2.setStep("2. Add the miso soup paste to the boiling water in a small pot and stir to dissolve, then pour over the veges in the bowl. Top with the mint, spring onion and chilli.");
        procedure3.setStep("3. Just as you start to eat, sprinkle the lime juice, fish sauce and sesame oil (use more or less to taste) over the top of the soup.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);

        Recipe r2 = new Recipe("Miso Fresh Bowl", String.valueOf(R.drawable.miso_soup),"A light and healthy salad",230,15,1,1,"Vegetarian", ingredientarray, procedureArray);

        db.addHandle(r2);
//       ============================================================================================================


        ing1.setInfo("Extra-virgin olive oil", 2, "tbsp");
        ing2.setInfo("Boneless skinless chicken breasts", 2, " ");
        ing3.setInfo("Kosher Salt", .5, "tsp");
        ing4.setInfo("Freshly ground black pepper", .5, "tsp");
        ing5.setInfo("Whole milk", 1.5, "cup");
        ing6.setInfo("Chicken broth", 1.5, "cups");
        ing7.setInfo("Garlic cloves, minced", 2, "");
        ing8.setInfo("Fetuccini", 8, "grams");
        ing9.setInfo("Heavy cream", 1.5, "cups");
        ing10.setInfo("Grated Parmesan", 1, "cup");
        ing11.setInfo("Chopped parsley", 2, "lots");

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

        procedureArray.clear();
        procedure1.setStep("1. In a large skillet over medium-high heat, heat oil. Add chicken and season with salt and pepper. Cook until golden and cooked through, 8 minutes per side. Let rest 10 minutes, then slice.");
        procedure2.setStep("2. Add milk, broth, and garlic to skillet. Season with salt and pepper and bring to a simmer. Add fettuccine, stirring frequently for about 3 minutes. Let cook until al dente, 8 minutes more.");
        procedure3.setStep("3. Stir in heavy cream and Parmesan until combined. Simmer until sauce thickens.");
        procedure4.setStep("4. Remove from heat and stir in sliced chicken. Garnish with parsley.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);


        Recipe r3 = new Recipe("Chicken Alfredo", String.valueOf(R.drawable.chicken_alfredo), "It's the classic pasta we just can't seem to get enough of.",540,40,1,4,"Comfort Food", ingredientarray,procedureArray);

        db.addHandle(r3);
//        //=========================================================================================================

        ing1.setInfo("Hoagie, cut into 1inch cubes", 4, "rolls");
        ing2.setInfo("butter, melted", 2, "tbsp");
        ing3.setInfo("garlic powder", 1, "tsp");
        ing4.setInfo("extra-virgin olive oil, divided", 2, "tbsp");
        ing5.setInfo("onion, sliced", 1, "");
        ing6.setInfo("bell peppers, sliced", 2, "");
        ing7.setInfo("Garlic, minced", 1.5, "");
        ing8.setInfo("sirloin steak, sliced into strips", 500, "grams");
        ing9.setInfo("Kosher salt", 1, "tsp");
        ing10.setInfo("Freshly ground black pepper", 1, "tsp");
        ing11.setInfo("Worcestershire sauce", 2, "tbsp");
        ing12.setInfo("low-sodium beef broth", 1, "cup");
        ing13.setInfo("provolone", 12, "slices");
        ing14.setInfo("Chopped parsley", 2, "lots");

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
        ingredientarray.add(ing14);

        procedureArray.clear();
        procedure1.setStep("1. Preheat oven to 350°. On a large baking sheet, toss hoagie rolls with melted butter and garlic powder. Bake until lightly golden, 10 minutes.");
        procedure2.setStep("2. In a large skillet over medium heat, heat 1 tablespoon oil. Add onion and peppers and season with salt and pepper. Cook, stirring occasionally until soft, 5 minutes. Stir in garlic and cook until fragrant, 1 minute more, then transfer mixture to a large bowl.");
        procedure3.setStep("3. Increase heat to medium-high and heat remaining tablespoon of oil. Add steak in a single layer, working in batches if necessary. Season generously with salt and pepper and let sear until bottom is golden, 3 minutes. Flip, then sear the other side. Return vegetables to skillet and stir in Worcestershire. Remove from heat.");
        procedure4.setStep("4.Grease a 9\"-x-13\" baking dish with cooking spray. Spread half the hoagie rolls on the bottom of the dish, then add in half the meat and vegetable mixture. Top with 6 slices provolone and repeat with remaining half of the hoagie rolls and meat and vegetable mixture. Pour broth over then top with remaining 6 slices of cheese. Bake until cheese is melty and bubbly, 15 minutes.");
        procedure5.setStep("5. Garnish with parsley before serving");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);
        procedureArray.add(procedure5);

        Recipe r4 = new Recipe("Philly Cheesesteak Casserole", String.valueOf(R.drawable.philly_cheesesteak),"Best cheesy caserole",1200,50,1,6,"Comfort Food", ingredientarray,procedureArray);

        db.addHandle(r4);
//        =========================================================================================================

        ing1.setInfo("vegetable oil", 1, "tbsp");
        ing2.setInfo("garlic, minced", 2, "cloves");
        ing3.setInfo("carrots, peeled and finely chopped", 1, "");
        ing4.setInfo("green bell pepper, finely chopped", 1, "");
        ing5.setInfo("shrimp, peeled", 750, "grams");
        ing6.setInfo("white rice, cooked", 3, "cups");
        ing7.setInfo("frozen peas, defrosted", 1, "cup");
        ing8.setInfo("soy sauce", 2, "tbsp");
        ing9.setInfo("sesame oil", 2, "tsp");
        ing10.setInfo("large egg, whisked", 1, "");
        ing11.setInfo("sriracha, for serving", 2, "tsp");
        ing12.setInfo("green onions, sliced", 2, "tbsp");

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

        procedureArray.clear();
        procedure1.setStep("1. In a medium skillet over medium heat, heat oil. Add garlic and stir for one minute. Add carrots and peppers and sauté, 3 minutes, then add shrimp and cook, 4 minutes, stirring occasionally.");
        procedure2.setStep("2. Stir in rice and peas and and season with soy sauce and sesame oil. Sauté for 2 more minutes.");
        procedure3.setStep("3. Push rice to one side of the skillet and add the egg. Stir egg constantly until almost fully cooked, then fold into rice mixture. Garnish with Sriracha and green onions and serve.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);


        Recipe r5 = new Recipe("Shrimp Fried Rice",String.valueOf(R.drawable.shrimp_fried_rice), "Tasty fried rice with a twist",300,30,1,4,"Comfort Food", ingredientarray,procedureArray);
//        r5.setPhoto();

        db.addHandle(r5);
//        =========================================================================================================


        ing1.setInfo("cooking spray", 1, "");
        ing2.setInfo("butternut squash, peeled, seeds removed, cubed", 1, "");
        ing3.setInfo("extra-virgin olive oil", 2, "tbsp");
        ing4.setInfo("kosher salt", 1, "tsp");
        ing5.setInfo("black pepper, freshly ground", 1, "tsp");
        ing6.setInfo("red pepper flakes", 1, "tsp");

        ingredientarray.clear();
        ingredientarray.add(ing1);
        ingredientarray.add(ing2);
        ingredientarray.add(ing3);
        ingredientarray.add(ing4);
        ingredientarray.add(ing5);
        ingredientarray.add(ing6);


        procedureArray.clear();
        procedure1.setStep("Preheat oven to 425° and grease a baking sheet with cooking spray. In a large bowl, toss squash with oil, salt, pepper, and red pepper flakes.");
        procedure2.setStep("2. Spread out on prepared baking sheet and roast until fork tender, about 40 minutes.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);


        Recipe r6 = new Recipe("Roasted butternut squash",String.valueOf( R.drawable.butternut_squash),"A delicious fall staple",250,60,1,4,"Vegetarian", ingredientarray,procedureArray);

        db.addHandle(r6);
//        =========================================================================================================

        ing1.setInfo("potatoes, cooked and diced", 8, "");
        ing2.setInfo("mayonnaise", 1.5, "cups");
        ing3.setInfo("cider vinegar", 2, "tbsp");
        ing4.setInfo("sugar", 2, "tbsp");
        ing5.setInfo("yellow mustard", 1, "tbsp");
        ing6.setInfo("salt", 1, "tsp");
        ing7.setInfo("garlic powder", 1, "tsp");
        ing8.setInfo("pepper", .5, "tsp");
        ing9.setInfo("celery, sliced", 2, "ribs");
        ing10.setInfo("onion, minced", 1, "cup");
        ing11.setInfo("eggs, hard-boiled", 5, "");
        ing12.setInfo("paprika", 1, "tbsp");

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

        procedureArray.clear();
        procedure1.setStep("1. Boil peeled potatoes in salted water until done. Cool to room temperature.");
        procedure2.setStep("2. Place diced potatoes in large bowl.");
        procedure3.setStep("3. Mix mayonnaise, cider vinegar, sugar, mustard, salt, garlic powder, and pepper in another bowl.");
        procedure4.setStep("4. Add to potatoes.");
        procedure5.setStep("5. Add celery and onions and mix well.");
        procedure6.setStep("6. Stir in eggs.");
        procedure7.setStep("7. Sprinkle a little paprika on top.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);
        procedureArray.add(procedure5);
        procedureArray.add(procedure6);
        procedureArray.add(procedure7);

        Recipe r7 = new Recipe("Potato Salad", String.valueOf(R.drawable.potato_salad),"Classic potato salad for pot lucks and picnics",550,15,1,8,"Vegan", ingredientarray,procedureArray);
//        r7.setPhoto();

        db.addHandle(r7);
//        =========================================================================================================

        ing1.setInfo("spaghetti pasta", 500, "grams");
        ing2.setInfo("olive oil", 2, "tbsp");
        ing3.setInfo("rosemary, chopped", 2, "tbsp");
        ing4.setInfo("garlic", 2, "cloves");
        ing5.setInfo("beef mince", 400, "grams");
        ing6.setInfo("onion, thinly sliced", 1, "");
        ing7.setInfo("can tomatoes, pureed", 400, "grams");
        ing8.setInfo("tomato paste", 3, "tbsp");
        ing9.setInfo("salt", 2, "tsp");
        ing10.setInfo("pepper", 2, "tsp");
        ing11.setInfo("parmesan cheese", 5, "grams");

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

        procedureArray.clear();
        procedure1.setStep("1. Cook 500g pasta according to directions, drain.");
        procedure2.setStep("2. Heat olive oil in a pan, fry chopped rosemary, crushed garlic, 400g beef mince, thinly sliced onion and diced red capsicum.");
        procedure3.setStep("3. Cook until the onions are soft and beef starts to brown. Add canned tomatoes and tomato paste.");
        procedure4.setStep("4. Cook until the sauce is boiling, then reduce heat and simmer 15 minutes until the beef is cooked and the capsicum tender.");
        procedure5.setStep("5. Season with salt and pepper to taste.");
        procedure6.setStep("6. Divide pasta into bowls and top with sauce. Sprinkle with grated parmesan cheese.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);
        procedureArray.add(procedure5);
        procedureArray.add(procedure6);


        Recipe r8 = new Recipe("Spaghetti Bolognese", String.valueOf(R.drawable.spaghetti_bolognese),"Classic potato salad for pot lucks and picnics",230,110,1,8,"Dairy Free", ingredientarray,procedureArray);
//        r8.setPhoto());

        db.addHandle(r8);
//        =========================================================================================================

        ing1.setInfo("plain flour", 250, "grams");
        ing2.setInfo("white sugar", 460, "grams");
        ing3.setInfo("cocoa power", 90, "grams");
        ing4.setInfo("baking powder", 1, "tsp");
        ing5.setInfo("salt", 1, "tsp");
        ing6.setInfo("water", 1, "cup");
        ing7.setInfo("vegetable oil", 1, "cup");
        ing8.setInfo("vanilla essence", 1, "tsp");

        ingredientarray.clear();
        ingredientarray.add(ing1);
        ingredientarray.add(ing2);
        ingredientarray.add(ing3);
        ingredientarray.add(ing4);
        ingredientarray.add(ing5);
        ingredientarray.add(ing6);
        ingredientarray.add(ing7);
        ingredientarray.add(ing8);

        procedureArray.clear();
        procedure1.setStep("1. Preheat the oven to 180 degrees C. Lightly gress a 20cmx30cm baking tray.");
        procedure2.setStep("2. In a large bowl stir together the flour, sugar, cocoa powder, baking powder and salt. Pour in water, vegetable oil and vanilla; mix until well blended. Spread evenly in baking tray.");
        procedure3.setStep("3. Bake for 25 to 30 minutes in the preheated oven until the top is no longer shiny. Let cool for at least 10 minutes before cutting into squares.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);


        Recipe r9 = new Recipe("Vegan Brownies", String.valueOf(R.drawable.chocolate_brownies),"Soft, rich, fudgy, dangerously chocolatey… and surprisingly vegan!",100,35,1,16,"Vegan", ingredientarray,procedureArray);
        db.addHandle(r9);
//        =========================================================================================================

        ing1.setInfo("jasmine rice", 1.5, "cups");
        ing2.setInfo("boiling water", 2.5, "cups");
        ing3.setInfo("carrot, grated", 1, "");
        ing4.setInfo("red onion, finely diced", .5, "");
        ing5.setInfo("tumeric", 1, "tsp");
        ing6.setInfo("lemon, zest", 1, "tbsp");
        ing7.setInfo("salmon, fillet", 600, "grams");
        ing8.setInfo("ciabatta, diced 2cm", 1, "roll");
        ing9.setInfo("semi-dried tomatoes, roughly chopped", 1, "cup");
        ing10.setInfo("red onion, finely diced", .25, "");
        ing11.setInfo("mesclun", .5, "bag");
        ing12.setInfo("vinegar", 2, "tsp");
        ing13.setInfo("mayonnaise", .25, "cup");
        ing14.setInfo("lemon, juice", .5, "cup");

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
        ingredientarray.add(ing14);

        procedureArray.clear();
        procedure1.setStep("1. Preheat oven to 200°C. Line an oven tray with baking paper.");
        procedure2.setStep("2. Combine all baked rice ingredients in deep, oven-proof dish. Mix well and cover dish with foil, making sure to seal the edges well. Bake for 25 minutes, then remove from oven and set aside.");
        procedure3.setStep("3. While rice is cooking, pat salmon dry and remove any remaining scales or pin bones. Season salmon and place, skin-side-up, to one side of prepared tray. Add diced bread to other side of tray, drizzle with olive oil and season. Bake (on rack above rice) for about 12 minutes, until salmon is cooked through..");
        procedure4.setStep("4. While rice and salmon are baking, add remaining red onion and semi-fried tomato to a large bowl and set aside.");
        procedure5.setStep("5. When salmon has finished cooking, set aside to rest for at least 5 minutes. Carefully peel skin off salmon then break into large chunks using a fork. Add to bowl with onion and tomatoes. In a small bowl, combine mayonnaise and lemon juice and set aside.");
        procedure6.setStep("6. Fluff up rice with a fork. Add mesclun, baked ciabatta and vinegar to bowl with salmon and gently toss to combine. Season to taste.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);
        procedureArray.add(procedure6);
        procedureArray.add(procedure7);



        Recipe r10 = new Recipe("Flake salmon salad", String.valueOf(R.drawable.salmon_salad),"This tasty flaked salmon salad is great for those that might not love salmon, like kids.",120,25,1,5,"Vegan", ingredientarray,procedureArray);
//        r1.setPhoto();
        db.addHandle(r10);
//        =========================================================================================================
    }




    public static void populatePantryIngredients(DatabaseHandler db, int owner){


        List<PantryIngredient> pList = new ArrayList<>();
        pList.add(new PantryIngredient("0000001", "Jasmine rice", 300, 300, "grams", "Staple", owner));
        pList.add(new PantryIngredient("0000002", "Carrots", 300, 300, "grams", "Vegetables", owner));
        pList.add(new PantryIngredient("0000003", "Red Onion", 10, 10, "", "Vegetables", owner));
        pList.add(new PantryIngredient("0000004", "Tumeric", 5, 5, "", "Vegetables", owner));
        pList.add(new PantryIngredient("0000005", "Lemon", 5, 5, "", "Vegetables", owner));
        pList.add(new PantryIngredient("0000006", "Ciabatta", 5, 5, "grams", "Staple", owner));
        pList.add(new PantryIngredient("0000007", "Mesclun", 300, 300, "grams", "Spices", owner));
        pList.add(new PantryIngredient("0000008", "Vinegar", 300, 300, "litres", "Oils", owner));
        pList.add(new PantryIngredient("0000009", "Mayonnaise", 300, 300, "grams", "Sauces", owner));
        pList.add(new PantryIngredient("0000008", "Vinegar", 300, 300, "litres", "Oils", owner));
        pList.add(new PantryIngredient("0000009", "Mayonnaise", 300, 300, "grams", "Sauces", owner));
        pList.add(new PantryIngredient("0000010", "Plain Flour", 500, 500, "grams", "Staple", owner));
        pList.add(new PantryIngredient("0000011", "White Sugar", 500, 500, "grams", "Staple", owner));
        pList.add(new PantryIngredient("0000012", "Cocoa Powder", 100, 100, "grams", "Baking", owner));
        pList.add(new PantryIngredient("0000013", "Baking Powder", 100, 100, "grams", "Baking", owner));
        pList.add(new PantryIngredient("0000012", "Salt", 100, 100, "grams", "Staple", owner));
        pList.add(new PantryIngredient("0000013", "Vegetable Oil", 100, 100, "litres", "Oils", owner));
        pList.add(new PantryIngredient("0000014", "Vanilla Essence", 50, 50, "litres", "Baking", owner));

        for(int i = 0; i < pList.size(); i++){
            db.addHandle(pList.get(i));
        }

//        ing1.setInfo("plain flour", 250, "grams");
//        ing2.setInfo("white sugar", 460, "grams");
//        ing3.setInfo("cocoa power", 90, "grams");
//        ing4.setInfo("baking powder", 1, "tsp");
//        ing5.setInfo("salt", 1, "tsp");
//        ing6.setInfo("water", 1, "cup");
//        ing7.setInfo("vegetable oil", 1, "cup");
//        ing8.setInfo("vanilla essence", 1, "tsp");

    }
}