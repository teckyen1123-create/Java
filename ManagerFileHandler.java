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

    public String[] getManagerProfile(String managerID){
        try{
            File file = new File(managerFileName);
            if (!file.exists()) return null;
            
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                String line = input.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] data = line.split("\\|");
                if (data.length >= 4 && data[0].equals(managerID)){
                    input.close();
                    return data;
                }
            } 
            input.close();
        } catch (FileNotFoundException e){
            System.out.println("manager.txt not found.");
        }
        return null;
    }
    
    public boolean updateManagerProfile(String managerID, String newName, String newEmail, String newPhone){
        File inputFile = new File(managerFileName);
        File tempFile = new File("manager_temp.txt");
        boolean updated = false;
        
        try{
            Scanner input = new Scanner(inputFile);
            PrintWriter output = new PrintWriter(new FileWriter(tempFile));
            
            while (input.hasNextLine()){
                String line = input.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] data = line.split("\\|");
                if (data.length >= 4 && data[0].equals(managerID)){
                    output.println(managerID + "|" + newName + "|" + newEmail + "|" + newPhone);
                    updated = true;
                } else {
                    output.println(line);
                }
            }
            
            input.close();
            output.close();
        
            inputFile.delete();
            tempFile.renameTo(inputFile);
            return updated;            
        } catch (IOException e){            
            return false;
        }
    }
}
