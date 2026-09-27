/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author USER
 */
public class Roster {
    private String rosterID;
    private String doctorID;
    private String shiftDate;
    private String shiftTime;
            
    private Roster(String rosterID, String doctorID, String shiftDate, String shiftTime){
        this.rosterID = rosterID;
        this.doctorID = doctorID;
        this.shiftDate = shiftDate;
        this.shiftTime = shiftTime;
    }
    
    public String getRosterID(){
        return rosterID;
    }
    
    public String getDoctorID(){
        return doctorID;
    }
    
    public String getShiftDate(){
        return shiftDate;
    }
    
    public String getShiftTime(){
        return shiftTime;
    }
    
    public void setRosterID(String rosterID) {
        this.rosterID = rosterID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }
    
    @Override
    public String toString(){
        return rosterID + "|" + doctorID + "|" + shiftDate + "|" + shiftTime;
    }
}
