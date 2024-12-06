package org.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LoginUserTest  {
    @Test
    public void testLogin(){

        String testInput = "Calob\nCaleb!123\n2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EnrollUser enrollUser = new EnrollUser(new Scanner(System.in));
        enrollUser.enroll();

        String output = outputStream.toString();

        assertTrue(EnrollUser.checkIfUsernamePresent("Calob"));
        assertEquals("Premium Client", PasswordManager.checkUser("Calob", "Caleb!123"));


        testInput = "Kobo\nCaleb!123\nKABOB\n123\nCalob\nCaleb!123\n2\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        LoginUser loginUser = new LoginUser(new Scanner(System.in));
        User user = loginUser.login();

        output = outputStream.toString();
        assertEquals("Calob", user.username);
        assertEquals("PremiumClient", user.role.getNameOfRole());
        assertTrue(output.contains("Invalid Username/Password"));

        testInput = "Kobo\nCaleb!123\nKABOB\n123\ncal\nCoo\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        loginUser = new LoginUser(new Scanner(System.in));
        user = loginUser.login();

        output = outputStream.toString();

        assertTrue(output.contains("Too many attempts, exiting now"));
    }
}