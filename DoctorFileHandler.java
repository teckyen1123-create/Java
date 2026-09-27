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
public class DoctorFileHandler {
    private final String fileName = "doctor.txt";
    
    public void loadDoctors(JComboBox<String> doctorcombo){
        try{
            File file = new File(fileName);
            if (!file.exists()){
                return;
            }

            Scanner input = new Scanner(file);

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                String doctorID = data[0];
                String name = data[1];
                String managerID = data[5];

                if (managerID.equals("Not Assigned")){
                    doctorcombo.addItem(doctorID + " - " + name);

                }else {
                    doctorcombo.addItem(doctorID + " - " + name+ " (Already Assigned)");
                }
            }

            input.close();
        }catch (FileNotFoundException e) {
            System.out.println("doctor.txt not found.");
        }
    }
    
    public String assignManager(String doctorID, String managerID){
        try{
            File file = new File(fileName);

            Scanner input = new Scanner(file);
            String newData = "";
            String oldManagerID = "";

            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] data = line.split("\\|");

                if (data[0].equals(doctorID)){
                    oldManagerID = data[5];
                    data[5] = managerID;
                }
                newData += String.join("|", data) + "\n";
            }

            input.close();

            FileWriter writer = new FileWriter(fileName);
            writer.write(newData);
            writer.close();

            return oldManagerID;

        }catch (IOException e) {
            System.out.println("Error saving doctor file.");
            return null;
        }
    }
    
    // Add new doctor
    public void addDoctor(String userID, String name){
        try{
            FileWriter writer =new FileWriter(fileName, true);
            writer.write("\n"
                    + userID + "|"
                    + name + "|||"
                    + "Not Assigned|Not Assigned");
            writer.close();

        }catch (IOException e) {
            System.out.println("Error saving doctor file.");
        }
    }
    
    public void deleteDoctor(String userID){
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

                if (!data[0].equals(userID)) {
                    newData = newData + line + "\n";
                }
            }

            input.close();

            FileWriter writer =new FileWriter(fileName);
            writer.write(newData);
            writer.close();

        }catch (IOException e) {
            System.out.println("Error deleting doctor profile.");
        }
    }
}
