/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.io.*;
import java.util.*;

/**
 *
 * @author kaishen
 */
public class FeedbackFileHandler {

    private final String fileName = "feedback.txt";

    public ArrayList<Feedback> getFeedbackByPatientID(
            String patientID) {

        ArrayList<Feedback> feedbackList =
                new ArrayList<>();

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#")
                        || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 6
                        && data[1].equals(patientID)) {

                    Feedback feedback =
                            new Feedback(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4],
                                    data[5]
                            );

                    feedbackList.add(feedback);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "feedback.txt not found."
            );
        }

        return feedbackList;
    }

    public void addFeedback(Feedback feedback) {

        try {

            FileWriter fileWriter =
                    new FileWriter(fileName, true);

            PrintWriter output =
                    new PrintWriter(fileWriter);

            output.println(
                    feedback.getFeedbackID() + "|" +
                    feedback.getPatientID() + "|" +
                    feedback.getDoctorID() + "|" +
                    feedback.getDate() + "|" +
                    feedback.getRating() + "|" +
                    feedback.getComment()
            );

            output.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to add feedback."
            );
        }
    }

    public String generateFeedbackID() {

        int number = 1;

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#")
                        || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 1
                        && data[0].startsWith("F")) {

                    try {

                        int currentNumber =
                                Integer.parseInt(
                                        data[0].substring(1)
                                );

                        if (currentNumber >= number) {
                            number = currentNumber + 1;
                        }

                    } catch (NumberFormatException e) {
                        // Ignore invalid ID
                    }
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            // File does not exist yet
        }

        return String.format("F%03d", number);
    }
}