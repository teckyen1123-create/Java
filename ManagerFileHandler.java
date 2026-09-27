/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.io.*;
import java.util.*;
import javax.swing.JComboBox;
/**
 *
 * @author limte
 */
public class ManagerFileHandler {
    private final String fileName = "manager.txt";
    
    // Load managers
    public void loadManagers(JComboBox<String> managercombo) {

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }

            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] data = line.split("\\|");

                String managerID = data[0];
                String name = data[1];
                managercombo.addItem(managerID + " - " + name);
            }

            input.close();
        }catch (FileNotFoundException e) {
            System.out.println("manager.txt not found.");
        }
    }
    
    // Add new manager
    public void addManager(String userID, String name){
        try{
            FileWriter writer =new FileWriter(fileName, true);
            writer.write("\n"+ userID + "|"+ name + "||");
            writer.close();

        }catch (IOException e) {
            System.out.println("Error saving manager file.");
        }
    }
    
    // Delete manager profile
    public void deleteManager(String userID){
        try{
            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }

            Scanner input = new Scanner(file);
            String newData = "";

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                if (!data[0].equals(userID)){
                    newData = newData + line + "\n";
                }
            }

            input.close();

            FileWriter writer =new FileWriter(fileName);
            writer.write(newData);
            writer.close();

        }catch (IOException e) {
            System.out.println("Error deleting manager profile.");
        }
    }
}
