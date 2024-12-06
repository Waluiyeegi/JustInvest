package org.example;

import java.io.*;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

public class UIInterface {
    static final String Operations =
            "Operations available on the system:\n"
                    + "1: View Account Balance\n"
                    + "2: View Investment Portfolio\n"
                    + "3: Modify Investment Portfolio\n"
                    + "4: View FInancial Advisor Contact Info\n"
                    + "5: View Financial Planner Contact Info\n"
                    + "6: View Money Market Instruments\n"
                    + "7: View Private Consumer Instruments\n";


    /***
     * Uses this interfce to connect all of the other 4 classes and gives the output of login, create account, and quit
     * @param inputStream
     * @throws Exception
     */
    private void run(InputStream inputStream) throws Exception {
        User user = null;
        AccessControlMech accessControlMech = null;
        EnrollUser enrollUser = null;
        LoginUser loginUser = null;

        Scanner scanner = new Scanner(inputStream);
        do {
            System.out.println(Operations);
            System.out.println("Just Invest System");
            System.out.println("-------------------------------------");
            System.out.println("1: Login");
            System.out.println("2: Create Account");
            System.out.println("3: Quit");
            String answer = scanner.nextLine();
            if (answer.equals("1")) {
                loginUser= new LoginUser(scanner);
                User tempUser = loginUser.login();
                if (tempUser == null) {
                    System.out.println("Login failed");
                    continue;
                }
                user = tempUser;
                System.out.println("Login successful");
            } else if (answer.equals("2")) {
                enrollUser = new EnrollUser(scanner);
                enrollUser.enroll();
            } else if (answer.equals("3")) {
                System.exit(0);
            }
            else{
                System.out.println("Invalid Command");
            }

        } while (user == null);

        accessControlMech = new AccessControlMech(user, scanner);
        accessControlMech.run();

        scanner.close();
    }














    public static void main(String[] args) throws Exception {
        UIInterface ui = new UIInterface();
        ui.run(System.in);

    }
}