/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.File;
import java.util.Scanner;
/**
 *
 * @author USER
 */
public class ReportFileHandle {
    public int countRecords(String fileName){
        int count = 0;
        try{
            File file = new File(fileName);
            if(!file.exists()) return 0;
            
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (!line.isEmpty() && !line.startsWith("#")){
                    count++;
                }
            }
            input.close();
        } catch (Exception e){
            System.out.println(fileName + "not found");
        }
        return count;
    }
    
    public double getConsultationRate(){
        double rate = 0.0;
        try {
            File file = new File("consultation_rate.txt");
            if (!file.exists()) return 0.0;
            
            Scanner input = new Scanner(file);
            if (input.hasNextLine()){
                String line = input.nextLine().trim();
                if (!line.isEmpty()){
                    rate = Double.parseDouble(line);
                }
            }
            input.close();
        } catch (Exception e){
            System.out.println("consultation_rate.txt not found");
        }
        return rate;
    }
}
