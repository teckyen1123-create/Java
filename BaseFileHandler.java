package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFileHandler {
    
    /**get file name*/
    protected abstract String getFileName();
    
    /**write new record*/
    public boolean save(String record) {
        String fileName = getFileName();
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(record + System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.out.println("Write error in " + fileName + ": " + e.getMessage());
            return false;
        }
    }
    /**read all lines and return as a single string*/
    public String readAll() {
        String fileName = getFileName();
        StringBuilder allData = new StringBuilder(); // 用 StringBuilder 拼接字符串效率最高
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    allData.append(line).append("\n"); // 每读完一行，加一个换行符
                }
            }
        } catch (IOException e) {
            System.out.println("Read error in " + fileName + ": " + e.getMessage());
        }
        
        return allData.toString();
    }
}
