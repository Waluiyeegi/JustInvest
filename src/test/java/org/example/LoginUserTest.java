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

        String testInput = "Kobo\nCaleb!123\nKABOB\n123\nKABOB\nCaleb!123\n2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        LoginUser loginUser = new LoginUser(new Scanner(System.in));
        User user = loginUser.login();

        String output = outputStream.toString();
        assertEquals("KABOB", user.username);
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