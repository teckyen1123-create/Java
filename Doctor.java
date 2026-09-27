public class Doctor extends User{
    public Doctor(String userID, String name, String username, String password) {
        super(userID, name, username, password);
    }

    @Override
    public void openDashboard() {
        DoctorFrame doctor = new DoctorFrame();
        doctor.setVisible(true);
    }
}

-----------------------------------------------------------------------------------------------------------
package assignment;

public class Doctor extends User {

    private String email;
    private String phone;
    private String specialty;
    private String managerID;

    public Doctor(String userID, String name, String username, String password) {
        super(userID, name, username, password);
        // default value for admin
        this.email = "Not Set";
        this.phone = "Not Set";
        this.specialty = "General"; 
        this.managerID = "Not Set";
        
        try {
            java.io.File file = new java.io.File("doctor.txt");
            java.util.Scanner input = new java.util.Scanner(file);
            
            while (input.hasNextLine()) {
                String line = input.nextLine();
                
                String[] parts = line.split("\\|");
                if (parts[0].equals(userID) && parts.length >= 6) {
                    this.email = parts[2];
                    this.phone = parts[3];
                    this.specialty = parts[4];
                    this.managerID = parts[5];
                    break;
                }
            }
            input.close();
            
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Warning: doctor.txt not found for " + userID);
        }
    }
    
    public Doctor(String userID, String name, String username, String password, 
                  String email, String phone, String specialty, String managerID) {
        super(userID, name, username, password);
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
        this.managerID = managerID;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    @Override
    public void openDashboard() {
        DoctorFrame doctorFrame = new DoctorFrame(this);
        doctorFrame.setVisible(true);
    }

    public boolean editProfile(String newName, String newUsername, String newPassword, String newEmail, String newPhone, String newSpecialty) {
        this.setEmail(newEmail);
        this.setPhone(newPhone);
        this.setSpecialty(newSpecialty);

        Doctor tempUpdatedDoctor = new Doctor(
                this.getUserID(), 
                newName,               
                newUsername,          
                newPassword,           
                newEmail, 
                newPhone, 
                newSpecialty,          
                this.getManagerID()
        );

        String newDoctorLine = this.getUserID() + "|" + newName + "|" + newEmail + "|" + newPhone + "|" + newSpecialty + "|" + this.getManagerID();

        DoctorFileHandler handler = new DoctorFileHandler();
        return handler.updateDoctorProfile(tempUpdatedDoctor, newDoctorLine);
    }

    public boolean logVitals(String patientID, String vitals, String notes) {
        String recordLine = "C" + System.currentTimeMillis() + "|" + this.getUserID() + "|" + patientID + "|" + vitals + "|" + notes + "|" + java.time.LocalDate.now().toString();
        
        ConsultationFileHandler handler = new ConsultationFileHandler();
        return handler.save(recordLine);
    }

    public boolean issuePrescription(String patientID, String medication, String dosage, String instructions) {
        String rxID = "RX" + System.currentTimeMillis();
        
        String recordLine = rxID + "|" + this.getUserID() + "|" + patientID + "|" + medication + "|" + dosage + "|" + instructions;

        PrescriptionFileHandler handler = new PrescriptionFileHandler();
        return handler.save(recordLine);
    }

    public boolean requestTest(String patientID, String testType) {
        String reqID = "TR" + System.currentTimeMillis();
        String recordLine = reqID + "|" + this.getUserID() + "|" + patientID + "|" + testType + "|Pending";

        TestRequestFileHandler handler = new TestRequestFileHandler();
        return handler.save(recordLine);
    }
}
