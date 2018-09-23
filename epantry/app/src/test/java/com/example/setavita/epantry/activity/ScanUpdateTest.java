package com.example.setavita.activity;

import com.example.setavita.epantry.database.DatabaseHandler;
import com.example.setavita.epantry.models.PantryIngredient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScanUpdateTest {

    private PantryUpdateActivity update;
    private AddIngredientActivity addIngredient;
    private DatabaseHandler db;

    @Before
    public void setUp() {
        //  context = InstrumentationRegistry.getContext();
        update = new PantryUpdateActivity();
        addIngredient = new AddIngredientActivity();
//        db = new DatabaseHandler(this);
    }


    @After
    public void tearDown() {
    }

    @Test
    public void testUpdateofDatabase() {
//        public PantryIngredient(String id, String name, int total, int current, String unitMeasure, int owner){

//        PantryIngredient ingredient = new PantryIngredient("12345", "Ingredient1", 23, 23, "grams", 111);
        PantryIngredient ingredient = new PantryIngredient();
        db = new DatabaseHandler(update);
//        assertFalse(db.addHandle(ingredient));
//        boolean actualReturn = db.addHandle(ingredient);
        assertEquals(false, db.addHandle(ingredient));
//        assertFalse(signupClass.checkEmail(testEmail));
    }

    @Test
    public void testValidEmail() {

        PantryIngredient ingredient = new PantryIngredient("12345", "Ingredient1", 23, 23, "grams", 111);
        db = new DatabaseHandler(update);
        assertTrue(db.addHandle(ingredient));

//        assertTrue(signupClass.checkEmail(testEmail));
    }
//
//    @Test
//    public void testNonMatchingPasswords() {
//
//        String testPass = "abc123";
//        String testConfirmPass = "123abc";
//
//        assertFalse(signupClass.checkPasswords(testPass,testConfirmPass));
//    }
//
//    @Test
//    public void testShortPasswordLength() {
//
//        String testPass = "aaa";
//        String testConfirmPass = "aaa";
//
//        assertFalse(signupClass.checkPasswords(testPass,testConfirmPass));
//    }
//
//    @Test
//    public void testMMatchingPasswords() {
//
//        String testPass = "helloabc";
//        String testConfirmPass = "helloabc";
//
//        assertTrue(signupClass.checkPasswords(testPass,testConfirmPass));
//    }
//
//    @Test
//    public void testValidSignupCreatesUser() {
//
//        String testUser = "testUser";
//        String testEmail = "testEmail@gmail.com";
//        String testPass = "testPass";
//        String testConfirmPass = "testPass";
//
//        Button button = (Button)findViewByid(R.id.Bsignup);
//
//        button.performClick();
//
//        assertNotNull( signupClass.findViewById(R.id.Bsignup) );
//    }
//
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
}