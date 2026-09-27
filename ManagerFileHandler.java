/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author USER
 */
public class ManagerFileHandle {
    
    private final String managerFileName = "manager.txt";
    
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
