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
