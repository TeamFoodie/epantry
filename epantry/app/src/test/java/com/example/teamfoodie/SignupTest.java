package com.example.teamfoodie;

import com.example.teamfoodie.epantry.SignupFormActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignupTest {

    private SignupFormActivity signupClass;

    @Before
    public void setUp() {
        signupClass = new SignupFormActivity();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkEmail method, of class Signup. briefcase has ".co" but not "@"
     */
    @Test
    public void testInvalidEmail() {

        String testEmail = "testEmail.com";

        assertFalse(signupClass.checkEmail(testEmail));
    }

    /**
     * Test of checkEmail method, of class Signup. briefcase fullfils ".co" and "@" requirements
     */
    @Test
    public void testValidEmail() {

        String testEmail = "testingabc@gmail.com";

        assertTrue(signupClass.checkEmail(testEmail));
    }

    /**
     * Test of checkPasswords method, of class Signup. has non-equal values
     */
    @Test
    public void testNonMatchingPasswords() {

        String testPass = "abc123";
        String testConfirmPass = "123abc";

        assertFalse(signupClass.checkPasswords(testPass,testConfirmPass));
    }

    /**
     * Test of checkPasswords method, of class Signup. has matching values
     */
    @Test
    public void testMatchingPasswords() {

        String testPass = "helloabc";
        String testConfirmPass = "helloabc";

        assertTrue(signupClass.checkPasswords(testPass,testConfirmPass));
    }

    /**
     * Test of checkPasswords method, of class Signup. has character length < 6
     */
    @Test
    public void testShortPasswordLength() {

        String testPass = "aaa";
        String testConfirmPass = "aaa";

        assertFalse(signupClass.checkPasswords(testPass,testConfirmPass));
    }
}
