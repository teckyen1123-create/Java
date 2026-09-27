/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.io.*;
import java.util.*;
/**
 *
 * @author kaishen
 */
public class PrescriptionFileHandler {

    private final String fileName = "prescription.txt";

    public ArrayList<Prescription> getPrescriptionsByPatientID(
            String patientID) {

        ArrayList<Prescription> prescriptions =
                new ArrayList<>();

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#")
                        || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 7
                        && data[1].equals(patientID)) {

                    Prescription prescription =
                            new Prescription(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4],
                                    data[5],
                                    data[6]
                            );

                    prescriptions.add(prescription);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "prescription.txt not found."
            );
        }

        return prescriptions;
    }
}