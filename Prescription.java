/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author kaishen
 */
public class Prescription {

    private String prescriptionID;
    private String patientID;
    private String doctorID;
    private String date;
    private String medicine;
    private String dosage;
    private String instructions;

    public Prescription(String prescriptionID,
                        String patientID,
                        String doctorID,
                        String date,
                        String medicine,
                        String dosage,
                        String instructions) {

        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.medicine = medicine;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDate() {
        return date;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }
}