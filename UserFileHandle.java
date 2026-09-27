/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.util.*;
import java.io.*;

/**
 *
 * @author kaishen
 */
public class UserFileHandle {

    private final String fileName = "users.txt";

    // Find user by User ID
    public User getUserByID(String userID) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5 && data[0].equals(userID)) {

                    String id = data[0];
                    String name = data[1];
                    String role = data[2];
                    String username = data[3];
                    String password = data[4];

                    input.close();

                    if (role.equals("Patient")) {

                        return new Patient(
                                id,
                                name,
                                username,
                                password,
                                "",
                                "",
                                ""
                        );

                    } else if (role.equals("Doctor")) {

                        return new Doctor(
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

                    } else if (role.equals("Admin")) {

                        return new Admin(
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


    // Check username and password for Login
    public User validateLogin(String username, String password) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5) {

                    String userID = data[0];
                    String name = data[1];
                    String role = data[2];
                    String fileUsername = data[3];
                    String filePassword = data[4];

                    if (fileUsername.equals(username)
                            && filePassword.equals(password)) {

                        input.close();

                        if (role.equals("Patient")) {

                            return new Patient(
                                    userID,
                                    name,
                                    username,
                                    password,
                                    "",
                                    "",
                                    ""
                            );

                        } else if (role.equals("Doctor")) {

                            return new Doctor(
                                    userID,
                                    name,
                                    username,
                                    password
                            );

                        } else if (role.equals("Manager")) {

                            return new Manager(
                                    userID,
                                    name,
                                    username,
                                    password
                            );

                        } else if (role.equals("Admin")) {

                            return new Admin(
                                    userID,
                                    name,
                                    username,
                                    password
                            );
                        }
                    }
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("users.txt not found.");
        }

        return null;
    }


    // Check whether a username already exists
    public boolean usernameExists(String username) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5 && data[3].equals(username)) {

                    input.close();
                    return true;
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("users.txt not found.");
        }

        return false;
    }


    // Update username and password in users.txt
    public void updateUser(User user) {

        File inputFile = new File(fileName);
        File tempFile = new File("users_temp.txt");

        try {

            Scanner input = new Scanner(inputFile);
            PrintWriter output = new PrintWriter(tempFile);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {

                    output.println(line);
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5
                        && data[0].equals(user.getUserID())) {

                    output.println(
                            user.getUserID() + "|" +
                            user.getName() + "|" +
                            data[2] + "|" +
                            user.getUsername() + "|" +
                            user.getPassword()
                    );

                } else {

                    output.println(line);
                }
            }

            input.close();
            output.close();

            if (!inputFile.delete()) {

                System.out.println("Unable to update users.txt.");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {

                System.out.println("Unable to save users.txt.");
            }

        } catch (FileNotFoundException e) {

            System.out.println("users.txt not found.");
        }
    }
}
