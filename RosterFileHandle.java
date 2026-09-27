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
public class RosterFileHandle {
    private final String rosterFileName = "roster.txt";
    
    public boolean addRoster(String rosterID, String doctorID, String shiftDate, String shiftTime){
        try {
            FileWriter writer = new FileWriter(rosterFileName, true);
            writer.write(rosterID + "|" + doctorID + "|" + shiftDate + "|" + shiftTime + "\n");
            writer.close();
            return true;
        } catch (IOException e){
            return false;
        }
    }
    
    public String[][] getAllRosters(){
        String[][] rosterArray = null;
        try{
            File file = new File(rosterFileName);
            if (!file.exists()) return new String[0][0];
            
            Scanner counter = new Scanner(file);
            int lineCount = 0;
            while (counter.hasNextLine()){
                if (!counter.nextLine().trim().isEmpty()) lineCount++;
            } 
            counter.close();
            
            rosterArray = new String[lineCount][4];
            
            Scanner input = new Scanner(file);
            int row = 0;
            while (input.hasNextLine()){
                String line = input.nextLine();
                if(line.trim().isEmpty()) continue;
                
                String[] data = line.split("\\|");
                if (data.length >= 4){
                    rosterArray[row][0] = data[0];
                    rosterArray[row][1] = data[1];
                    rosterArray[row][2] = data[2];
                    rosterArray[row][3] = data[3];
                    row++;
                }
            } 
            input.close();
        } catch (FileNotFoundException e){
            return new String[0][0];
        }
        return rosterArray;
    }
    
    public boolean updateRoster(String rosterID, String newDoctorID, String newShiftDate, String newShiftTime){
        File inputFile = new File(rosterFileName);
        File tempFile = new File("roster_temp.txt");
        boolean updated = false;
        
        try{
            Scanner input = new Scanner(inputFile);
            PrintWriter output = new PrintWriter(new FileWriter(tempFile));
            
            while (input.hasNextLine()){
                String line = input.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] data = line.split("\\|");
                if (data.length >= 4 && data[0].equals(rosterID)){
                    output.println(rosterID +"|" + newDoctorID + "|" + newShiftDate + "|" + newShiftTime);
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
