public class Manager extends User{
    public Manager(String userID, String name, String username, String password) {
        super(userID, name, username, password);
    }

    @Override
    public void openDashboard() {
        ManagerFrame manager = new ManagerFrame();
        manager.setVisible(true);
    }
}
