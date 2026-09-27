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

---------------------------------------------------------------------------------------------------------
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.*;
import java.util.*;

/**
 *
 * @author kaishen & you (Merged Version)
 */
public class PrescriptionFileHandler extends BaseFileHandler {

    private final String fileName = "prescription.txt";

    @Override
    protected String getFileName() {
        return fileName;
    }

    public String generatePrescriptionID() {
        int maxID = 0;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Scanner input = new Scanner(file);
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    if (line.trim().isEmpty() || line.startsWith("#")) {
                        continue;
                    }
                    
                    String[] data = line.split("\\|");
                    if (data.length > 0) {
                        String currentID = data[0]; // 例如 P001
                        // 提取数字部分并找出最大值
                        if (currentID.startsWith("P") && currentID.length() > 1) {
                            try {
                                int number = Integer.parseInt(currentID.substring(1));
                                if (number > maxID) {
                                    maxID = number;
                                }
                            } catch (NumberFormatException e) {
                                // 忽略格式不正确的 ID
                            }
                        }
                    }
                }
                input.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("prescription.txt not found. Starting from P001.");
        }

        maxID++;

        // 格式化输出 P001, P020 等
        if (maxID < 10) {
            return "P00" + maxID;
        } else if (maxID < 100) {
            return "P0" + maxID;
        } else {
            return "P" + maxID;
        }
    }

    public ArrayList<Prescription> getPrescriptionsByPatientID(String patientID) {
        ArrayList<Prescription> prescriptions = new ArrayList<>();

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return prescriptions; // 如果文件不存在，返回空的列表
            }
            
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                // 严格匹配 7 个字段，并且第二个字段（索引 1）是当前病人的 ID
                if (data.length >= 7 && data[1].equals(patientID)) {
                    Prescription prescription = new Prescription(
                            data[0], // Prescription ID
                            data[1], // Patient ID
                            data[2], // Doctor ID
                            data[3], // Date
                            data[4], // Medication
                            data[5], // Dosage
                            data[6]  // Instructions
                    );

                    prescriptions.add(prescription);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("prescription.txt not found.");
        }

        return prescriptions;
    }
}
