package org.example;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

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


    private void run(InputStream inputStream) throws Exception {
        User user = null;

        Scanner scanner = new Scanner(inputStream);
        int passwordCount = 3;
        do {
            System.out.println(Operations);
            System.out.println("Just Invest System");
            System.out.println("-------------------------------------");
            System.out.println("1: Login");
            System.out.println("2: Create Account");
            System.out.println("3: Quit");
            String answer = scanner.nextLine();
            if (answer.equals("1")) {

                System.out.println("Enter Username for Login: ");
                String username = scanner.nextLine();
                System.out.println("Enter Password for Login: ");
                String password = scanner.nextLine();
                //System.out.println("Enter current time in the form of 00:00");
                String role = checkUser(username, password);
                if (!Objects.equals(role, "error")) {
                    switch (role){
                        case "Client":
                            Client client = new Client();
                            user = new User(client, username);
                            break;
                        case "Premium Client":
                            PremiumClient premiumClient = new PremiumClient();
                            user = new User(premiumClient, username);
                            break;
                        case "Financial Advisor":
                            FinancialAdvisor financialAdvisor = new FinancialAdvisor();
                            user = new User(financialAdvisor, username);
                            break;
                        case "Financial Planner":
                            FinancialPlanner financialPlanner = new FinancialPlanner();
                            user = new User(financialPlanner, username);
                            break;
                        case "Teller":
                            Teller teller = new Teller();
                            user = new User(teller, username);
                            break;
                        case "error":
                            System.out.println("Invalid Username/Password");
                            break;
                        default:
                            System.out.println("Some other error");
                            break;
                    }
                    assert user != null;
                    System.out.println("Login Successful as a " + user.getRole().getNameOfRole());
                } else {
                    System.out.println("Invalid Username and/or Password");
                }
            } else if (answer.equals("2")) {
                System.out.println("Enter Username for account creation: ");
                String username = scanner.nextLine();
                if (checkIfUsernamePresent(username)){
                    System.out.println("Username taken please choose antoher one");
                    continue;
                }
                System.out.println("Enter Password for account creation: ");
                String password = scanner.nextLine();
                if(this.checkPassword(username, password)){
                    System.out.println("Enter Role for account creation");
                    System.out.println("1:Client\n"
                            + "2:Premium Client\n"
                            + "3:Financial Advisor\n"
                            + "4:Financial Planner\n"
                            + "5:Teller");
                    String role = scanner.nextLine();
                    switch (role){
                        case "1":
                            String client = "Client";
                            addUser(username, password, client);
                            break;
                        case "2":
                            String premiumClient = "Premium Client";
                            addUser(username, password, premiumClient);
                            break;
                        case "3":
                            String financialAdvisor = "Financial Advisor";
                            addUser(username, password, financialAdvisor);
                            break;
                        case "4":
                            String financialPlanner = "Financial Planner";
                            addUser(username, password, financialPlanner);
                            break;
                        case "5":
                            String teller = "Teller";
                            addUser(username, password, teller);
                            break;
                        default:
                            System.out.println("Invalid Role");
                            break;
                    }

                }
                else {
                    passwordCount -= 1;
                    System.out.println("Invalid Password, you have " + passwordCount + " attempts remaining");
                    if (passwordCount == 0){
                        System.out.println("The maximum of 3 attempts has been used, exiting program");
                        System.exit(0);
                    }
                }
            } else if (answer.equals("3")) {
                System.exit(0);
            }
            else{
                System.out.println("Invalid Command");
            }

        } while (user == null);
        boolean quit = false;
        boolean doneTime = false;
        while (!quit) {
            if(Objects.equals(user.getRole().getNameOfRole(), "Teller")) {
                if (!doneTime){
                    System.out.println("Please enter the time in the form of 00:00 in 24 hour time");
                    String time = scanner.nextLine();
                    String[] pairs = time.split(":");
                    if(Integer.parseInt(pairs[0])<0 || Integer.parseInt(pairs[0])>23){
                        System.out.println("Invalid Time please enter another time");
                        continue;
                    }
                    if(((Integer.parseInt(pairs[0])<9 || Integer.parseInt(pairs[0])>17) && Objects.equals(user.getRole().getNameOfRole(), "Teller"))){
                        System.out.println("Teller can only access system from 9:00 to 17:00");
                        System.exit(0);
                    }
                    doneTime = true;
                }
            }
            System.out.println("Username:" + user.getUsername());
            System.out.println("Role:" + user.getRole().getNameOfRole());
            System.out.println("-------------------------------------");
            System.out.println("Your authorized operations are:");
            System.out.println("-------------------------------------");

            for (Permissions permissions : user.getRole().getPermissions()) {
                String[] perm = permissions.getName().split(":");
                if(permissions != Permissions.TIME_RESTRICTIONS){
                    System.out.println(perm[0]+ ":" + perm[1]);
                }
            }
            System.out.println("8: Quit");
            System.out.println("Please enter your desired operation");
            String operation = scanner.nextLine();
            boolean check = false;
            for (Permissions permissions : user.getRole().getPermissions()) {
                String[] perm = permissions.getName().split(":");
                if (operation.equals(String.valueOf(perm[0]))) {
                    System.out.println(perm[2]+ "\n");
                    check = true;
                    break;
                }
                if (Objects.equals(operation, "8")) {
                    quit = true;
                    check = true;
                }

            }
            if (!check) {
                System.out.println("Invalid Operation");
            }
            if (quit) {
                System.exit(0);
            }

        }
        scanner.close();
    }

    public void addUser(String user, String password, String role) {
        String salt = BCrypt.gensalt(12);
        String hash = BCrypt.hashpw(password, salt);

        File file = new File("src/main/java/org/example/passwd.txt");

        StringBuilder stringBuilder = new StringBuilder();
        boolean addedUser = false;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty() && !addedUser) {
                    stringBuilder.append(user).append(",").append(hash).append(",").append(role).append(System.lineSeparator());
                    addedUser = true;
                } else {
                    stringBuilder.append(line).append(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!addedUser){
            stringBuilder.append(user).append(",").append(hash).append(",").append(role).append(System.lineSeparator());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public String checkUser(String user, String password) {
        String[] csvValues;
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/main/java/org/example/passwd.txt"))) {
            String line;
            while ((line = buffRead.readLine()) != null) {
                if (!line.isEmpty()) {
                    csvValues = line.split(",");
                    if (Objects.equals(csvValues[0], user)) {
                        if(BCrypt.checkpw(password, csvValues[1])){
                            return csvValues[2];
                        }
                        else {
                            return "error";
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
        return "error";
    }

    public boolean checkIfUsernamePresent(String user){
        String[] csvValues;
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/main/java/org/example/passwd.txt"))) {
            String line;
            while ((line = buffRead.readLine()) != null) {
                if (!line.isEmpty()) {
                    csvValues = line.split(",");
                    if (Objects.equals(csvValues[0], user)) {
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

    public Boolean checkPassword(String username, String password) {

        if (password.length() < 8 || password.length() > 12) {
            System.out.println("len bad");
            return false;
        }
        if (password.equals(username)) {
            System.out.println("pswd is username");
            return false;
        }
        String mustInclude = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%&*]).+";
        if (!Pattern.matches(mustInclude, password)) {
            System.out.println("doesnt have symbols");
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
            System.out.println("weak pwd");
            return false;
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        UIInterface ui = new UIInterface();
        ui.run(System.in);

    }
}