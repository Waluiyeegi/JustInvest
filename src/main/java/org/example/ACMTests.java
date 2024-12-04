package org.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ACMTests {
    @Test
    public void testAccessControlMechanism(){
        //UIInterface ui = new UIInterface();
        String testUserInput = "Bob\nTheTomato\n3\n1\n8";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testUserInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }


    @Test
    public void testPasswordFile(){
        UIInterface ui = new UIInterface();
        ui.addUser("Caleb", "testPwd", "Client");
        assertTrue(ui.checkIfUsernamePresent("Caleb"));
        String str = ui.checkUser("Caleb", "testPwd");
        assertEquals("Client", str);
        str = ui.checkUser("Jaleb", "testPwd");
        assertEquals("error", str);
        str = ui.checkUser("Caleb", "testPwd1");
        assertEquals("error", str);
    }


    @Test
    public void testWeakPassword() {
        UIInterface ui = new UIInterface();
        assertTrue(ui.checkPassword("Caleb", "Caleb!123"));//Valid password
        assertTrue(ui.checkPassword("Caleb", "Caleb@123"));//Valid password
        assertTrue(ui.checkPassword("Caleb", "Caleb#123"));//Valid password
        assertTrue(ui.checkPassword("Caleb", "Caleb$123"));//Valid password
        assertTrue(ui.checkPassword("Caleb", "Caleb%123"));//Valid password
        assertTrue(ui.checkPassword("Caleb", "Caleb*123"));//Valid password
        assertTrue(ui.checkPassword("Caleb", "Caleb&123"));//Valid password
        assertFalse(ui.checkPassword("Caleb", "caleb!123"));//no cap
        assertFalse(ui.checkPassword("Caleb", "CALEB!123"));//No lowercase
        assertFalse(ui.checkPassword("Caleb", "Caleb!abc"));//no numbers
        assertFalse(ui.checkPassword("Caleb", "Cale!12"));//tests char length
        assertFalse(ui.checkPassword("Caleb", "Caleb!1233333333"));//tests char length
        assertFalse(ui.checkPassword("Caleb", "Caleb123"));//tests pwd without special characters
        assertFalse(ui.checkPassword("Caleb!123", "Caleb!123"));//Tests pwd cant match username
        assertFalse(ui.checkPassword("Caleb", "Caleb!1234"));//Tests pwd cant be from pwd list(i added this password in it for testing)

    }

    @Test
    public void testUserLogin() {

    }

    //@Test
}
