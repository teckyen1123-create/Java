public class Patient extends User{
    public Patient(String userID, String name, String username, String password) {
        super(userID, name, username, password);
    }

    @Override
    public void openDashboard() {
        PatientFrame patient = new PatientFrame(this);
        patient.setVisible(true);
    }
}
