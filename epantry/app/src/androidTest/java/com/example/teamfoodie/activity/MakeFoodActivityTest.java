package com.example.teamfoodie.activity;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.teamfoodie.database.SQLiteDatabaseDao;
import com.example.teamfoodie.epantry.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 */
public class MakeFoodActivityTest {
    private static final String TAG = "MakeFoodActivityTest";

    @Rule
    public ActivityTestRule<MakeFoodActivity> mActivityTestRule = new ActivityTestRule<>(MakeFoodActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void isMakeFoodActivity(){
        Log.d(TAG, "isMakeFoodActivity: 是否是makefoodActivity==" + mActivityTestRule.getActivity().getClass().getSimpleName());
        assertEquals("MakeFoodActivity", mActivityTestRule.getActivity().getClass().getSimpleName());
    }

    @Test
    public void addFoodMaterial(){
        SQLiteDatabaseDao sqLiteDatabaseDao = SQLiteDatabaseDao.getInstance();
        sqLiteDatabaseDao.init(mActivityTestRule.getActivity());
        mActivityTestRule.getActivity().addFoodMaterial(sqLiteDatabaseDao);
        Log.d(TAG, "addFoodMaterial: 添加了几条数据==" + sqLiteDatabaseDao.queryFoodMaterial().toString());
        assertEquals(3, sqLiteDatabaseDao.queryFoodMaterial().size());
    }

    @Test
    public void ingredientsTest() throws Exception {
        Log.d(TAG, "ingredientsTest: name==" + withText("ingredients").toString());
        onView(withId(R.id.ingredients))
                .check(matches(withText("ingredients11")));
    }


}