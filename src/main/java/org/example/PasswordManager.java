package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.regex.Pattern;

public class PasswordManager {

    /***
     * Uses Bcrypt library to hash the pasword and generate the salt
     * Adds the user into the passwd.txt file on the first free line
     * Also makes the user, password, and role separated by strings in the passwd.txt file
     * @param user
     * @param password
     * @param role
     */
    public static void addUser(String user, String password, String role) {
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

    /***
     * Checks if the user is in the passwd.txt file and if he is Check if the password is in the file, and if it is
     * return the role of the user
     * @param user
     * @param password
     * @return
     */
    public static String checkUser(String user, String password) {
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

}
