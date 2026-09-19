public class Admin extends User{
    public Admin(String userID, String name,
                     String username, String password) {

        super(userID, name, username, password);
    }

    @Override
    public void openDashboard() {
        AdminFrame admin = new AdminFrame();
        admin.setVisible(true);
    }
}
