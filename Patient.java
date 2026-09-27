/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author kaishen
 */
public class Patient extends User{
    
    public Patient(String userID, String name, String username, String password) {
        super(userID, name, username, password);
    }
    
    private String email;
    private String phone;
    private String bloodType;
    
    public Patient(String userID, String name, String username, String password, String email, String phone, String bloodType) {
        super(userID, name, username, password);
        
        this.email = email;
        this.phone = phone;
        this.bloodType = bloodType; 
    }
    
        public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public void openDashboard() {
        PatientFrame patient = new PatientFrame(this);
        patient.setVisible(true);
    }
}
