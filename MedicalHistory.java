/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author kaishen
 */
public class MedicalHistory {

    private String historyID;
    private String patientID;
    private String doctorID;
    private String date;
    private String diagnosis;
    private String treatment;
    private String notes;

    public MedicalHistory(String historyID,
                          String patientID,
                          String doctorID,
                          String date,
                          String diagnosis,
                          String treatment,
                          String notes) {

        this.historyID = historyID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
    }

    public String getHistoryID() {
        return historyID;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
