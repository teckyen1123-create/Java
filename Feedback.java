/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author kaishen
 */
public class Feedback {

    private String feedbackID;
    private String patientID;
    private String doctorID;
    private String date;
    private String rating;
    private String comment;

    public Feedback(String feedbackID,
                    String patientID,
                    String doctorID,
                    String date,
                    String rating,
                    String comment) {

        this.feedbackID = feedbackID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    public String getFeedbackID() {
        return feedbackID;
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

    public String getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}