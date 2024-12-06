package org.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ACMTests {
    @Test
    public void testAccessControlMechanism() {
        Role tellerRole = new Teller(); // Predefined role with permissions in your codebase
        User user = new User(tellerRole, "BobTheTomato");
        String testInput = "9:00\n1\n2\n3\n4\n5\n6\n7\n8\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        AccessControlMech accessControlMech = new AccessControlMech(user, new Scanner(System.in));
        accessControlMech.run();

        String output = outputStream.toString();

        assertTrue(output.contains("Username:BobTheTomato"));
        assertTrue(output.contains("Role:Teller"));
        assertTrue(output.contains("1:View Balance"));
        assertTrue(output.contains("2:View Portfolio"));
        assertFalse(output.contains("3:Modify Portfolio"));
        assertFalse(output.contains("4:View Advisor Contact"));
        assertFalse(output.contains("5:View Planners Contact"));
        assertFalse(output.contains("6:View Private Consumer Instruments"));
        assertFalse(output.contains(":View Money Market Instruments"));
        assertTrue(output.contains("8: Quit"));

        assertTrue(output.contains("Viewing Balance"));
        assertTrue(output.contains("Viewing Portfolio"));
        assertFalse(output.contains("Modifying Portfolio"));
        assertFalse(output.contains("Viewing Advisor Contact"));
        assertFalse(output.contains("Viewing Planners Contact"));
        assertFalse(output.contains("Viewing Private Consumer Instruments"));
        assertFalse(output.contains("Viewing Money Market Instruments"));

        FinancialPlanner financialPlanner = new FinancialPlanner(); // Predefined role with permissions in your codebase
        user = new User(financialPlanner, "BobTheTomato");
        testInput = "1\n2\n3\n4\n5\n6\n7\n8\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        accessControlMech = new AccessControlMech(user, new Scanner(System.in));
        accessControlMech.run();

        output = outputStream.toString();

        assertTrue(output.contains("Username:BobTheTomato"));
        assertTrue(output.contains("Role:Financial Planner"));
        assertTrue(output.contains("1:View Balance"));
        assertTrue(output.contains("2:View Portfolio"));
        assertTrue(output.contains("3:Modify Portfolio"));
        assertFalse(output.contains("4:View Advisor Contact"));
        assertFalse(output.contains("5:View Planners Contact"));
        assertTrue(output.contains("6:View Private Consumer Instruments"));
        assertTrue(output.contains("7:View Money Market Instruments"));
        assertTrue(output.contains("8: Quit"));

        assertTrue(output.contains("Viewing Balance"));
        assertTrue(output.contains("Viewing Portfolio"));
        assertTrue(output.contains("Modifying Portfolio"));
        assertFalse(output.contains("Viewing Advisor Contact"));
        assertFalse(output.contains("Viewing Planners Contact"));
        assertTrue(output.contains("Viewing Private Consumer Instruments"));
        assertTrue(output.contains("Viewing Money Market Instruments"));


    }

}




//    @Test
//    public void testPasswordFile(){
//        UIInterface ui = new UIInterface();
//        ui.addUser("Caleb", "testPwd", "Client");
//        assertTrue(ui.checkIfUsernamePresent("Caleb"));
//        String str = ui.checkUser("Caleb", "testPwd");
//        assertEquals("Client", str);
//        str = ui.checkUser("Jaleb", "testPwd");
//        assertEquals("error", str);
//        str = ui.checkUser("Caleb", "testPwd1");
//        assertEquals("error", str);
//
//        ui.addUser("Cabeb", "Caleb!123", "Teller");
//        assertTrue(ui.checkIfUsernamePresent("Cabeb"));
//        String str2 = ui.checkUser("Cabeb", "testPwd");
//        assertEquals("Teller", str2);
//    }
//
//
//    @Test
//    public void testWeakPassword() {
//        UIInterface ui = new UIInterface();
//        assertTrue(ui.checkPassword("Caleb", "Caleb!123"));//Valid password
//        assertTrue(ui.checkPassword("Caleb", "Caleb@123"));//Valid password
//        assertTrue(ui.checkPassword("Caleb", "Caleb#123"));//Valid password
//        assertTrue(ui.checkPassword("Caleb", "Caleb$123"));//Valid password
//        assertTrue(ui.checkPassword("Caleb", "Caleb%123"));//Valid password
//        assertTrue(ui.checkPassword("Caleb", "Caleb*123"));//Valid password
//        assertTrue(ui.checkPassword("Caleb", "Caleb&123"));//Valid password
//        assertFalse(ui.checkPassword("Caleb", "caleb!123"));//no cap
//        assertFalse(ui.checkPassword("Caleb", "CALEB!123"));//No lowercase
//        assertFalse(ui.checkPassword("Caleb", "Caleb!abc"));//no numbers
//        assertFalse(ui.checkPassword("Caleb", "Cale!12"));//tests char length
//        assertFalse(ui.checkPassword("Caleb", "Caleb!1233333333"));//tests char length
//        assertFalse(ui.checkPassword("Caleb", "Caleb123"));//tests pwd without special characters
//        assertFalse(ui.checkPassword("Caleb!123", "Caleb!123"));//Tests pwd cant match username
//        assertFalse(ui.checkPassword("Caleb", "Caleb!1234"));//Tests pwd cant be from pwd list(i added this password in it for testing)
//
//    }
//
//    @Test
//    public void testEnrollment() {
//        UIInterface ui = new UIInterface();
//
//    }
//
//    @Test
//    public void testUserLogin() {
//
//    }
//
//    //@Test
//}
