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
public class DepartmentFileHandle {
    private final String deptFileName = "departments.txt";
    
    public boolean addDepartment(String deptID, String name, String description){
        try{
            FileWriter writer = new FileWriter(deptFileName, true);
            writer.write(deptID + "|" + name + "|" + description + "\n");
            writer.close();
            return true;
        } catch (IOException e){
            System.out.println("Error writing to " + deptFileName);
            return false;
        }
    }
    
    public String[][] getAllDepartments(){
        String[][] deptArray = null;
        try{
            File file = new File(deptFileName);
            if (!file.exists()) return new String[0][0];
            
            Scanner counter = new Scanner(file);
            int lineCount = 0;
            while (counter.hasNextLine()){
                if (!counter.nextLine().trim().isEmpty()) lineCount++;
            } 
            counter.close();
            
            deptArray = new String[lineCount][3];
            
            Scanner input = new Scanner(file);
            int row = 0;
            while (input.hasNextLine()){
                String line = input.nextLine();
                if(line.trim().isEmpty()) continue;
                
                String[] data = line.split("\\|");
                if (data.length >= 3){
                    deptArray[row][0] = data[0];
                    deptArray[row][1] = data[1];
                    deptArray[row][2] = data[2];
                    row++;
                }
            } 
            input.close();
        } catch (FileNotFoundException e){
            return new String[0][0];
        }
        return deptArray;
    }
    
    public boolean updateDepartment(String deptID, String newName, String newDescription){
        File inputFile = new File(deptFileName);
        File tempFile = new File("departments_temp.txt");
        boolean updated = false;
        
        try{
            Scanner input = new Scanner(inputFile);
            PrintWriter output = new PrintWriter(new FileWriter(tempFile));
            
            while (input.hasNextLine()){
                String line = input.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] data = line.split("\\|");
                if (data.length >= 3 && data[0].equals(deptID)){
                    output.println(deptID +"|" + newName + "|" + newDescription);
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
