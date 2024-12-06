package org.example;

import java.util.Objects;
import java.util.Scanner;

public class AccessControlMech {
    User user;
    Scanner scanner;
    public AccessControlMech(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    //Deteremines what actions the user can do depending on what role they have as well as
    //lists out the User information (role and username) and allows you to execute the actions
    public void run(){
        boolean quit = false;
        boolean doneTime = false;
        while (!quit) {
            if (Objects.equals(user.getRole().getNameOfRole(), "Teller")) {
                if (!doneTime) {
                    System.out.println("Please enter the time in the form of 00:00 in 24 hour time");
                    String time = scanner.nextLine();
                    try {
                        String[] pairs = time.split(":");
                        int hour = Integer.parseInt(pairs[0]);

                        if (hour < 9 || hour > 17) {
                            System.out.println("Teller can only access system from 9:00 to 17:00");
                            return;
                        }
                        doneTime = true;
                    } catch (Exception e) {
                        System.out.println("Invalid Time. Please enter a valid time.");
                        return;
                    }
                }
            }
            System.out.println("Username:" + user.getUsername());
            System.out.println("Role:" + user.getRole().getNameOfRole());
            System.out.println("-------------------------------------");
            System.out.println("Your authorized operations are:");
            System.out.println("-------------------------------------");

            for (Permissions permissions : user.getRole().getPermissions()) {
                String[] perm = permissions.getName().split(":");
                if (permissions != Permissions.TIME_RESTRICTIONS) {
                    System.out.println(perm[0] + ":" + perm[1]);
                }
            }
            System.out.println("8: Quit");
            System.out.println("Please enter your desired operation");
            String operation = scanner.nextLine();
            boolean check = false;
            for (Permissions permissions : user.getRole().getPermissions()) {
                String[] perm = permissions.getName().split(":");
                if (operation.equals(String.valueOf(perm[0]))) {
                    System.out.println(perm[2] + "\n");
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
                break;
            }
        }
    }
}
