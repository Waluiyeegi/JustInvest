package org.example;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class EnrollUserTest{
    @Test
    public void testEnroll(){


        String testInput = "KABOB\nCaleb!123\n2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EnrollUser enrollUser = new EnrollUser(new Scanner(System.in));
        enrollUser.enroll();

        String output = outputStream.toString();

        assertTrue(EnrollUser.checkIfUsernamePresent("KABOB"));
        assertEquals("Premium Client", PasswordManager.checkUser("KABOB", "Caleb!123"));


        testInput = "Coble\nCaleb#133\n3\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        enrollUser = new EnrollUser(new Scanner(System.in));
        enrollUser.enroll();

        output = outputStream.toString();

        assertTrue(EnrollUser.checkIfUsernamePresent("Coble"));
        assertEquals("Financial Advisor", PasswordManager.checkUser("Coble", "Caleb#133"));


        testInput = "Coble\nCole\nCal\nCooble\nCaleb$133\n3\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        enrollUser = new EnrollUser(new Scanner(System.in));
        enrollUser.enroll();

        output = outputStream.toString();

        assertTrue(output.contains("Username taken please choose another one"));
        assertTrue(output.contains("Invalid Password"));

        assertTrue(EnrollUser.checkIfUsernamePresent("Cooble"));
        assertEquals("Financial Advisor", PasswordManager.checkUser("Cooble", "Caleb$133"));

    }
}