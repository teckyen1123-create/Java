/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author USER
 */
public class Manager extends User{
    public Manager (String userID, String name, String username, String password){
        super (userID, name, username, password);
    }
    
    @Override
    public void openDashboard(){
        ManagerFrame manager = new ManagerFrame(this);
        manager.setVisible(true);
    }
}
