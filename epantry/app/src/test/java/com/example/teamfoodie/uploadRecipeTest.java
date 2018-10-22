package com.example.teamfoodie;

import com.example.teamfoodie.epantry.UserRecipeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class uploadRecipeTest {

    private UserRecipeActivity upRecipeClass;

    @Before
    public void setUp(){
        upRecipeClass= new UserRecipeActivity();
    }
    @After
    public void tearDown(){

    }

    @Test
    public void testTitleLength(){
        String testTitle="fried beef";
        String testName="Title";
        int length=30;
        assertTrue(upRecipeClass.checkLength(testTitle,testName,length));
    }

    @Test
    public void testLongTitleLength(){
        String testTitle="Deep fried rice with prawn and pork cooking in five minutes";
        String testName="Title";
        int length=30;
        boolean result=upRecipeClass.checkLength(testTitle,testName,length);
        assertFalse(result);
    }

    @Test
    public void testLongIntroduceLength(){
        String testIntroduce="Deep fried rice with prawn and pork cooking in five minutes. \n" +
                "Still screaming at the halal mutton fried rice in the halal shop and worrying about not buying fresh lamb?\n" +
                "Still mad at the barbecue shop with a sizzling pork belly?\n" +
                "just now! This dish of pork fried rice definitely meets your heart!";
        String testName="Introduce";
        int length=50;
        assertEquals(false,upRecipeClass.checkLength(testIntroduce,testName,length));
    }
}
