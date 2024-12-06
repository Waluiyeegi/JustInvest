package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class LoginUser {
    Scanner scanner;
    public LoginUser(Scanner scanner){
        this.scanner = scanner;
    }

    /***
     * Checks the username and Password against the passwd.txt to see if the username is inside, and if it is and the pwd
     * is correct, it creates a new User using the username and generated role depeneding on what role was in the passwd.txt
     *
     * @return User
     */
    public User login(){
        for (int i=3; i>0; i--) {
            System.out.println("You have " + i + " login atttempts remaining");
            System.out.println("Enter Username for Login: ");
            String username = scanner.nextLine();
            System.out.println("Enter Password for Login: ");
            String password = scanner.nextLine();
            String checkedUser = PasswordManager.checkUser(username, password);
            Role role = null;
            //System.out.println("Enter current time in the form of 00:00");
            if (!Objects.equals(checkedUser, "error")) {
                switch (checkedUser) {
                    case "Client":
                        role = new Client();
                        break;
                    case "Premium Client":
                        role = new PremiumClient();
                        break;
                    case "Financial Advisor":
                        role = new FinancialAdvisor();
                        break;
                    case "Financial Planner":
                        role = new FinancialPlanner();
                        break;
                    case "Teller":
                        role = new Teller();
                        break;
                    case "error":
                        System.out.println("Invalid Username/Password");
                        break;
                    default:
                        System.out.println("Some other error");
                        break;
                }

            }
            if(role != null){
                return new User(role, username);
            }
            else{
                System.out.println("Invalid Username/Password");
            }
        }
        System.out.println("Too many attempts, exiting now");
        return null;
    }

}
