/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author kaishen
 */
public class PatientFileHandle {
    
    private final String fileName = "patient.txt";

    public Patient getPatientByID(String userID) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data[0].equals(userID)) {

                    String id = data[0];
                    String name = data[1];
                    String email = data[2];
                    String phone = data[3];
                    String bloodType = data[4];

                    input.close();

                    return new Patient(
                            id,
                            name,
                            "",
                            "",
                            email,
                            phone,
                            bloodType
                    );
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("patient.txt not found.");
        }

        return null;
    }

    
    public void updatePatient(Patient patient) {

    File inputFile = new File(fileName);
    File tempFile = new File("patient_temp.txt");

    try {

        Scanner input = new Scanner(inputFile);
        java.io.PrintWriter output =
                new java.io.PrintWriter(tempFile);

        while (input.hasNextLine()) {

            String line = input.nextLine();

            if (line.startsWith("#") || line.trim().isEmpty()) {
                output.println(line);
                continue;
            }

            String[] data = line.split("\\|");

            if (data[0].equals(patient.getUserID())) {

                output.println(
                        patient.getUserID() + "|" +
                        patient.getName() + "|" +
                        patient.getEmail() + "|" +
                        patient.getPhone() + "|" +
                        patient.getBloodType()
                );

            } else {

                output.println(line);
            }
        }

        input.close();
        output.close();

        if (!inputFile.delete()) {
            System.out.println("Unable to update patient.txt.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Unable to save patient.txt.");
        }

    } catch (FileNotFoundException e) {

        System.out.println("patient.txt not found.");
    }
}
    
    public void addPatient(Patient patient) {

    try {

        java.io.PrintWriter output =
                new java.io.PrintWriter(
                        new java.io.FileWriter(fileName, true)
                );

        output.println(
                patient.getUserID() + "|" +
                patient.getName() + "|" +
                patient.getEmail() + "|" +
                patient.getPhone() + "|" +
                patient.getBloodType()
        );

        output.close();

    } catch (java.io.IOException e) {

        System.out.println("Unable to add patient.");
    }
}
    
    public boolean patientExists(String userID) {

        return getPatientByID(userID) != null;
}

    // Add new patient
    public void fileaddPatient(String userID, String name){
        try{
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("\n"
                    + userID + "|"
                    + name + "|||Not Assigned");
            writer.close();
        }catch (IOException e) {
            System.out.println("Error saving patient file.");
        }
    }
    
    // Delete patient profile
    public void deletePatient(String userID){
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

            FileWriter writer = new FileWriter(fileName);
            writer.write(newData);
            writer.close();

    public static void main(String[] args) {
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author kaishen
 */
public class PatientFileHandler {
    
    private final String fileName = "patient.txt";

    public Patient getPatientByID(String userID) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data[0].equals(userID)) {

                    String id = data[0];
                    String name = data[1];
                    String email = data[2];
                    String phone = data[3];
                    String bloodType = data[4];

                    input.close();

                    return new Patient(
                            id,
                            name,
                            "",
                            "",
                            email,
                            phone,
                            bloodType
                    );
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("patient.txt not found.");
        }

        return null;
    }

    
    public void updatePatient(Patient patient) {

    File inputFile = new File(fileName);
    File tempFile = new File("patient_temp.txt");

    try {

        Scanner input = new Scanner(inputFile);
        java.io.PrintWriter output =
                new java.io.PrintWriter(tempFile);

        while (input.hasNextLine()) {

            String line = input.nextLine();

            if (line.startsWith("#") || line.trim().isEmpty()) {
                output.println(line);
                continue;
            }

            String[] data = line.split("\\|");

            if (data[0].equals(patient.getUserID())) {

                output.println(
                        patient.getUserID() + "|" +
                        patient.getName() + "|" +
                        patient.getEmail() + "|" +
                        patient.getPhone() + "|" +
                        patient.getBloodType()
                );

            } else {

                output.println(line);
            }
        }

        input.close();
        output.close();

        if (!inputFile.delete()) {
            System.out.println("Unable to update patient.txt.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Unable to save patient.txt.");
        }

    } catch (FileNotFoundException e) {

        System.out.println("patient.txt not found.");
    }
}
    
    public void addPatient(Patient patient) {

    try {

        java.io.PrintWriter output =
                new java.io.PrintWriter(
                        new java.io.FileWriter(fileName, true)
                );

        output.println(
                patient.getUserID() + "|" +
                patient.getName() + "|" +
                patient.getEmail() + "|" +
                patient.getPhone() + "|" +
                patient.getBloodType()
        );

        output.close();

        } catch (java.io.IOException e) {

            System.out.println("Unable to add patient.");
        }
    }

        public boolean patientExists(String userID) {

            return getPatientByID(userID) != null;
    }

}
