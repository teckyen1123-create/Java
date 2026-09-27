/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author kaishen
 */
public class Appointment {
    private String appointmentID;
    private String patientID;
    private String specialty;
    private String doctorID;
    private String date;
    private String time;
    private String status;
    
    public Appointment(String appointmentID, String patientID, String specialty, String doctorID, String date, String time, String status) {

        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.specialty = specialty;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    
    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
    
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
