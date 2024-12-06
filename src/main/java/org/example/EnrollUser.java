package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EnrollUser {
    Scanner scanner;
    public EnrollUser(Scanner scanner){
        this.scanner = scanner;
    }

    /***
     * Puts the user info(username, password, role) into the passwd.txt file
     * Ensures that user info is valid
     *
     */
    public void enroll(){
        while (true) {
            System.out.println("Enter Username for account creation: ");
            String username = scanner.nextLine();
            if (checkIfUsernamePresent(username)) {
                System.out.println("Username taken please choose another one");
                continue;
            }
            System.out.println("Enter Password for account creation: ");
            String password = scanner.nextLine();
            if (checkPassword(username, password)) {
                System.out.println("Enter Role for account creation");
                System.out.println("1:Client\n"
                        + "2:Premium Client\n"
                        + "3:Financial Advisor\n"
                        + "4:Financial Planner\n"
                        + "5:Teller");
                String role = scanner.nextLine();
                switch (role) {
                    case "1":
                        String client = "Client";
                        PasswordManager.addUser(username, password, client);
                        break;
                    case "2":
                        String premiumClient = "Premium Client";
                        PasswordManager.addUser(username, password, premiumClient);
                        break;
                    case "3":
                        String financialAdvisor = "Financial Advisor";
                        PasswordManager.addUser(username, password, financialAdvisor);
                        break;
                    case "4":
                        String financialPlanner = "Financial Planner";
                        PasswordManager.addUser(username, password, financialPlanner);
                        break;
                    case "5":
                        String teller = "Teller";
                        PasswordManager.addUser(username, password, teller);
                        break;
                    default:
                        System.out.println("Invalid Role");
                        break;
                }
                return;
            }
            else{
                System.out.println("Invalid Password");
            }
        }
    }

    /***
     * Makes sure the password in the given username and password is valid
     * accesses the weakpwd.txt list that is a list of weak passwords
     *
     * @param username
     * @param password
     * @return boolean
     */
    public static boolean checkPassword(String username, String password) {

        if (password.length() < 8 || password.length() > 12) {
            System.out.println("Password length must be between 8 and 12");
            return false;
        }
        if (password.equals(username)) {
            System.out.println("Password cannot be your username");
            return false;
        }
        String mustInclude = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%&*]).+";
        if (!Pattern.matches(mustInclude, password)) {
            System.out.println("Password doesnt include one of these symbols: !, @, #, $, %, *, &");
            return false;
        }

        HashSet<String> weakPasswords = new HashSet<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/main/java/org/example/weakpwd.txt"))) {
            String line;
            while ((line = buffRead.readLine()) != null) {
                weakPasswords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (weakPasswords.contains(password)) {
            System.out.println("Weak Password");
            return false;
        }
        return true;
    }

    /***
     * Checks the username is present in the passwd.txt file
     *
     * @param userName
     * @return boolean
     */
    public static boolean checkIfUsernamePresent(String userName){
        String[] csvValues;
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/main/java/org/example/passwd.txt"))) {
            String line;
            while ((line = buffRead.readLine()) != null) {
                if (!line.isEmpty()) {
                    csvValues = line.split(",");
                    if (Objects.equals(csvValues[0], userName)) {
                        return true;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
        return false;
    }






}
