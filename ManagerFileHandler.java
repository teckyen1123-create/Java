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
}
