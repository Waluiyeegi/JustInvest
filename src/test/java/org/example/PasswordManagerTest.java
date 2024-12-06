package org.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PasswordManagerTest {
    @Test
    public void testPasswordManager(){
        PasswordManager.addUser("Caleb", "testpwd", "Teller");
        PasswordManager.addUser("Joe", "PWd3", "Client");
        assertEquals("Teller", PasswordManager.checkUser("Caleb", "testpwd"));
        assertEquals("error", PasswordManager.checkUser("Caleb", "boop"));
        assertEquals("error", PasswordManager.checkUser("Cabob", "testpwd"));

        assertEquals("Client", PasswordManager.checkUser("Joe", "PWd3"));
        assertEquals("error", PasswordManager.checkUser("Joe", "boop"));
        assertEquals("error", PasswordManager.checkUser("Job", "PWd3"));

    }

    @Test
    public void testCheckPassword(){
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb!123"));//Valid password
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb@123"));//Valid password
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb#123"));//Valid password
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb$123"));//Valid password
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb%123"));//Valid password
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb*123"));//Valid password
        assertTrue(EnrollUser.checkPassword("Caleb", "Caleb&123"));//Valid password
        assertFalse(EnrollUser.checkPassword("Caleb", "caleb!123"));//no cap
        assertFalse(EnrollUser.checkPassword("Caleb", "CALEB!123"));//No lowercase
        assertFalse(EnrollUser.checkPassword("Caleb", "Caleb!abc"));//no numbers
        assertFalse(EnrollUser.checkPassword("Caleb", "Cale!12"));//tests char length
        assertFalse(EnrollUser.checkPassword("Caleb", "Caleb!1233333333"));//tests char length
        assertFalse(EnrollUser.checkPassword("Caleb", "Caleb123"));//tests pwd without special characters
        assertFalse(EnrollUser.checkPassword("Caleb!123", "Caleb!123"));//Tests pwd cant match username
        assertFalse(EnrollUser.checkPassword("Caleb", "Caleb!1234"));//Tests pwd cant be from pwd list(i added this password in it for testing)
    }
}