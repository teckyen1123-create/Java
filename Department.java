/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author USER
 */
public class Department {
    private String deptID;
    private String name;
    private String description;
    
    public Department(String deptID, String name, String description){
        this.deptID = deptID;
        this.name = name;
        this.description = description;
    }
    
    public String getDeptID() {
        return deptID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return deptID + "|" + name + "|" + description;
    }
}
