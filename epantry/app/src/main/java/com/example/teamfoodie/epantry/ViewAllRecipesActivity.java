package com.example.teamfoodie.epantry;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ViewAllRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_recipes);

        List<Recipe> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomRecipeListAdapter(this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Recipe recipe = (Recipe) o;
                Toast.makeText(ViewAllRecipesActivity.this, "Selected :" + " " + recipe, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(recipe.getUrl()));
                startActivity(i);

            }
        });
    }

    private List<Recipe> getListData() {
        List<Recipe> list = new ArrayList<Recipe>();

        Recipe afghans = new Recipe("Afghans", "afghans", "Afghans are a kiwi classic","https://edmondscooking.co.nz/recipes/biscuits/afghans/");
        Recipe anzac_biscuits = new Recipe("Anzac Biscuits", "anzac_biscuits", "These are a soft chewy","https://edmondscooking.co.nz/recipes/biscuits/anzac-biscuits/" );
        Recipe apricot_balls = new Recipe("Apricot Balls", "apricot_balls", "These are quickly","https://edmondscooking.co.nz/recipes/bliss-balls/apricot-bliss-balls/");
        Recipe apricot_jam= new Recipe("Apricot Jam", "apricot_jam", "Simple Jam","https://edmondscooking.co.nz/recipes/jams-jellies/apricot-jam/");
        Recipe basic_biscuits = new Recipe("Basic Biscuits", "basic_biscuits", "Make one simple dough","https://edmondscooking.co.nz/recipes/biscuits/basic-refrigerator-biscuits/");
        Recipe bliss_balls= new Recipe("Bliss Balls", "bliss_balls", "Bliss balls","https://edmondscooking.co.nz/recipes/bliss-balls/apricot-cashew-bliss-balls/");
        Recipe chelsea_buns= new Recipe("Chelsea Buns", "chelsea_buns", "Chelsea Buns","https://edmondscooking.co.nz/recipes/breads-and-buns/chelsea-buns/");
        Recipe chocolate_cake= new Recipe("Chocolate Cake", "chocolate_cake", "Chocolate Cake ","https://edmondscooking.co.nz/recipes/cakes/chocolate-cake/");
        Recipe chocolate_gateau= new Recipe("Chocolate Gateau", "chocolate_gateau", "MMMMMM Gateau","https://edmondscooking.co.nz/recipes/cakes/christmas-chocolate-gateau/");
        Recipe chocolate_meringue= new Recipe("Chocolate Meringue", "chocolate_meringue_cake", "Meringue what?","https://edmondscooking.co.nz/recipes/cakes/chocolate-meringue-cake/");
        Recipe chorizo_and_tomato_salad= new Recipe("Chorizo and Tomato Salad", "chorizo_and_tomato_salad", "Chorizo what a lovely sausage","https://edmondscooking.co.nz/recipes/salad/crisp-chorizo-tomato-salad-with-french-vinaigrette/");
        Recipe crumpets= new Recipe("Crumpets", "crumpets", "Ahhh the breakfast classic","https://edmondscooking.co.nz/recipes/breads-and-buns/crumpets/");
        Recipe lamb_and_feta_sliders= new Recipe("Lamb and Feta Sliders", "lamb_and_feta_sliders", "Sliders?! Miniture burgers!!","https://edmondscooking.co.nz/recipes/beef-pork-and-lamb/succulent-lamb-and-feta-sliders-with-minted-aioli/");
        Recipe potato_slad= new Recipe("Potato Salad", "potato_salad", "Summer BBQ classic","https://edmondscooking.co.nz/recipes/salad/potato-salad/");
        Recipe raspberry_jam= new Recipe("Raspberry Jam", "raspberry_jam", "Is the 'p' silent?","https://edmondscooking.co.nz/recipes/jams-jellies/raspberry-jam/");
        Recipe soft_white_loaf= new Recipe("Soft White Loaf", "soft_white_loaf", "Hot bread and the paper","https://edmondscooking.co.nz/recipes/breads-and-buns/edmonds-soft-white-loaf/");
        Recipe wagyu_burger= new Recipe("Wagyu Burger", "wagyu_burgers", "What on earth is a Wagyu?","https://edmondscooking.co.nz/recipes/burgers-and-pizzas/wagyu-beef-burger-with-caramelised-onion-mayo/");

        list.add(afghans);
        list.add(anzac_biscuits);
        list.add(apricot_balls);
        list.add(apricot_jam);
        list.add(basic_biscuits);
        list.add(bliss_balls);
        list.add(chelsea_buns);
        list.add(chocolate_cake);
        list.add(chocolate_gateau);
        list.add(chocolate_meringue);
        list.add(chorizo_and_tomato_salad);
        list.add(crumpets);
        list.add(lamb_and_feta_sliders);
        list.add(potato_slad);
        list.add(raspberry_jam);
        list.add(soft_white_loaf);
        list.add(wagyu_burger);

        return list;

    }
}
