/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author limte
 */
import java.io.*;
import java.util.*;
import javax.swing.JComboBox;

public class Userfilehandler {
    private final String fileName = "users.txt";
    
    public void loadDoctorsAndManagers(JComboBox<String> usercombo){
        try{
            File file = new File(fileName);
            if (!file.exists()){
                return;
            }

            Scanner input = new Scanner(file);

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                if (data[2].equals("Doctor") || data[2].equals("Manager")){
                    usercombo.addItem(data[0] + " - " + data[1]);
                }
            }

            input.close();

        }catch (FileNotFoundException e) {
            System.out.println("users.txt not found.");
        }
    }
    
    // Generate next User ID
    public String generateUserID() {
        int maxID = 0;
        try{
            File file = new File(fileName);

            if (file.exists()){

                Scanner input = new Scanner(file);

                while (input.hasNextLine()){
                    String line = input.nextLine();
                    String[] data = line.split("\\|");
                    String currentID = data[0];

                    int number = Integer.parseInt(currentID.substring(1));

                    if (number > maxID){
                        maxID = number;
                    }
                }

                input.close();
            }

        }catch (FileNotFoundException e) {
            System.out.println("users.txt not found.");
        }catch (NumberFormatException e) {
            System.out.println("Invalid User ID.");
        }

        maxID++;

        if (maxID < 10){
            return "U00" + maxID;
        }else if (maxID < 100) {
            return "U0" + maxID;
        }else {
            return "U" + maxID;
        }
    }
    
    // Check whether username already exists
    public boolean usernameExists(String username) {
        try{

            File file = new File(fileName);
            if (file.exists()){
                Scanner input = new Scanner(file);

                while (input.hasNextLine()){

                    String line = input.nextLine();
                    String[] data = line.split("\\|");

                    if (data[3].equals(username)){
                        input.close();
                        return true;
                    }
                }

                input.close();
            }
        }catch (FileNotFoundException e) {
            System.out.println("users.txt not found.");
        }
        return false;
    }
    
    // Add new user
    public void addUser(User user, String role) {
        try {
            FileWriter writer =new FileWriter(fileName, true);

            writer.write("\n"
                    + user.getUserID() + "|"
                    + user.getName() + "|"
                    + role + "|"
                    + user.getUsername() + "|"
                    + user.getPassword());
            writer.close();

        }catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
    
    public String updateUser(String userID, String name, String role, String username, String password){
        try{
            File file = new File(fileName);

            if (!file.exists()){
                return "NOT_FOUND";
            }

            Scanner input = new Scanner(file);

            String allUsers = "";
            boolean found = false;

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                // Check username
                if (data[3].equals(username) && !data[0].equals(userID)) {
                    input.close();
                    return "USERNAME_EXISTS";
                }

                if (data[0].equals(userID)){
                    String newLine =
                            userID + "|"
                            + name + "|"
                            + role + "|"
                            + username + "|"
                            + password;
                    allUsers = allUsers + newLine + "\n";
                    found = true;
                }else {
                    allUsers = allUsers + line + "\n";
                }
            }

            input.close();
            if (!found){
                return "NOT_FOUND";
            }

            FileWriter writer = new FileWriter(fileName);
            writer.write(allUsers);
            writer.close();

            return "SUCCESS";
        }catch (IOException e) {
            System.out.println("Error updating user.");
            return "ERROR";
        }
    }
    
    public String getRoleByID(String userID){
        try{
            File file = new File(fileName);

            if (!file.exists()) {
                return null;
            }

            Scanner input = new Scanner(file);

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                if (data[0].equals(userID)){
                    String role = data[2];
                    input.close();
                    return role;
                }
            }

            input.close();

        }catch (FileNotFoundException e) {
            System.out.println("users.txt not found.");
        }
        return null;
    }
    
    // Delete user
    public String deleteUser(String userID){
        try{
            File file = new File(fileName);

            if (!file.exists()){
                return "FILE_NOT_FOUND";
            }

            Scanner input = new Scanner(file);

            String allUsers = "";
            boolean found = false;

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                if (data[0].equals(userID)){
                    found = true;
                }else {
                    allUsers = allUsers + line + "\n";
                }
            }

            input.close();

            if (!found){
                return "NOT_FOUND";
            }
            
            FileWriter writer =new FileWriter(fileName);
            writer.write(allUsers);
            writer.close();

            return "SUCCESS";

        }catch (IOException e) {
            System.out.println("Error deleting user.");
            return "ERROR";
        }
    }
    
    // Validate username and password
    public User validateLogin(String username, String password){
        try{
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("users.txt not found.");
                return null;
            }

            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                String userID = data[0];
                String name = data[1];
                String role = data[2];
                String fileUsername = data[3];
                String filePassword = data[4];

                if (fileUsername.equals(username) && filePassword.equals(password)){
                    input.close();

                    if (role.equals("Admin")){
                        return new Admin(
                                userID,
                                name,
                                username,
                                password
                        );

                    }else if (role.equals("Manager")) {
                        return new Manager(
                                userID,
                                name,
                                username,
                                password
                        );

                    }else if (role.equals("Doctor")) {
                        return new Doctor(
                                userID,
                                name,
                                username,
                                password
                        );

                    }else if (role.equals("Patient")) {
                        return new Patient(
                                userID,
                                name,
                                username,
                                password
                        );
                    }
                }
            }

            input.close();

        }catch (FileNotFoundException e) {
            System.out.println("users.txt not found.");
        }
        return null;
    }
    public User getUserByID(String userID) {

        try {
    
            File file = new File(fileName);
    
            if (!file.exists()) {
                return null;
            }
    
            Scanner input = new Scanner(file);
    
            while (input.hasNextLine()) {
    
                String line = input.nextLine();
    
                if (line.trim().isEmpty()) {
                    continue;
                }
    
                String[] data = line.split("\\|");
    
                if (data.length < 5) {
                    continue;
                }
    
                if (data[0].equals(userID)) {
    
                    String id = data[0];
                    String name = data[1];
                    String role = data[2];
                    String username = data[3];
                    String password = data[4];
    
                    input.close();
    
                    if (role.equals("Admin")) {
    
                        return new Admin(
                                id,
                                name,
                                username,
                                password
                        );
    
                    } else if (role.equals("Manager")) {
    
                        return new Manager(
                                id,
                                name,
                                username,
                                password
                        );
    
                    } else if (role.equals("Doctor")) {
    
                        return new Doctor(
                                id,
                                name,
                                username,
                                password
                        );
    
                    } else if (role.equals("Patient")) {
    
                        return new Patient(
                                id,
                                name,
                                username,
                                password
                        );
                    }
                }
            }
    
            input.close();
    
        } catch (FileNotFoundException e) {
    
            System.out.println("users.txt not found.");
        }
    
        return null;
    }

    public String[] getUserAccount(String userID){
        try{
            File file = new File(fileName);

            if (!file.exists()) {
                return null;
            }
            Scanner input = new Scanner(file);

            while (input.hasNextLine()){
                String line = input.nextLine();
                if (line.isEmpty()){
                    continue;
                }
                String[] data = line.split("\\|");
                if (data[0].equals(userID)){
                    input.close();
                    return data;
                }
            }

            input.close();

        }catch (FileNotFoundException e) {
            System.out.println("users.txt not found.");
        }
        return null;
    }
}
