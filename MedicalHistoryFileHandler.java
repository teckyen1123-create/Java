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
public class MedicalHistoryFileHandler {

    private final String fileName = "medicalHistory.txt";

    public ArrayList<MedicalHistory> getMedicalHistoryByPatientID(String patientID) {

        ArrayList<MedicalHistory> histories = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 7
                        && data[1].equals(patientID)) {

                    MedicalHistory history =
                            new MedicalHistory(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4],
                                    data[5],
                                    data[6]
                            );

                    histories.add(history);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println(
                    "medicalHistory.txt not found."
            );
        }

        return histories;
    }
}