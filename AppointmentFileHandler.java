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
public class AppointmentFileHandler {

    private final String fileName = "appointment.txt";


    // Get one appointment by Appointment ID
    public Appointment getAppointmentByID(String appointmentID) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 7
                        && data[0].equals(appointmentID)) {

                    Appointment appointment = new Appointment(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            data[6]
                    );

                    input.close();

                    return appointment;
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("appointment.txt not found.");
        }

        return null;
    }


    // Get all appointments belonging to one patient
    public ArrayList<Appointment> getAppointmentsByPatientID(
            String patientID) {

        ArrayList<Appointment> appointments =
                new ArrayList<>();

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 7
                        && data[1].equals(patientID)) {

                    Appointment appointment =
                            new Appointment(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4],
                                    data[5],
                                    data[6]
                            );

                    appointments.add(appointment);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("appointment.txt not found.");
        }

        return appointments;
    }


    // Add a new appointment
    public void addAppointment(Appointment appointment) {

        try {

            FileWriter fileWriter =
                    new FileWriter(fileName, true);

            PrintWriter output =
                    new PrintWriter(fileWriter);

            output.println(
                    appointment.getAppointmentID() + "|" +
                    appointment.getPatientID() + "|" +
                    appointment.getSpecialty() + "|" +
                    appointment.getDoctorID() + "|" +
                    appointment.getDate() + "|" +
                    appointment.getTime() + "|" +
                    appointment.getStatus()
            );

            output.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to add appointment."
            );
        }
    }


    // Update an existing appointment
    public void updateAppointment(Appointment appointment) {

        File inputFile = new File(fileName);
        File tempFile = new File("appointment_temp.txt");

        try {

            Scanner input = new Scanner(inputFile);
            PrintWriter output =
                    new PrintWriter(tempFile);

            while (input.hasNextLine()) {

                String line = input.nextLine();

                if (line.startsWith("#")
                        || line.trim().isEmpty()) {

                    output.println(line);
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 7
                        && data[0].equals(
                                appointment.getAppointmentID())) {

                    output.println(
                            appointment.getAppointmentID() + "|" +
                            appointment.getPatientID() + "|" +
                            appointment.getSpecialty() + "|" +
                            appointment.getDoctorID() + "|" +
                            appointment.getDate() + "|" +
                            appointment.getTime() + "|" +
                            appointment.getStatus()
                    );

                } else {

                    output.println(line);
                }
            }

            input.close();
            output.close();

            if (!inputFile.delete()) {

                System.out.println(
                        "Unable to update appointment.txt."
                );

                return;
            }

            if (!tempFile.renameTo(inputFile)) {

                System.out.println(
                        "Unable to save appointment.txt."
                );
            }

        } catch (FileNotFoundException e) {

            System.out.println(
                    "appointment.txt not found."
            );
        }
    }


    // Cancel an appointment
    public void cancelAppointment(String appointmentID) {

        Appointment appointment =
                getAppointmentByID(appointmentID);

        if (appointment != null) {

            appointment.setStatus("Cancelled");

            updateAppointment(appointment);

        } else {

            System.out.println(
                    "Appointment not found."
            );
        }
    }
    
    public boolean isTimeBooked(String doctorID, String date, String time) {

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

                if (data.length >= 7
                        && data[3].equals(doctorID)
                        && data[4].equals(date)
                        && data[5].equals(time)
                        && data[6].equalsIgnoreCase("Booked")) {

                    input.close();
                    return true;
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("appointment.txt not found.");
        }

        return false;
    }
    
    public boolean isTimeBooked(String doctorID, String date, String time, String excludeAppointmentID) {

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

                if (data.length >= 7
                        && data[3].equals(doctorID)
                        && data[4].equals(date)
                        && data[5].equals(time)
                        && data[6].equalsIgnoreCase("Booked")
                        && !data[0].equals(excludeAppointmentID)) {

                    input.close();
                    return true;
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("appointment.txt not found.");
        }

        return false;
    }

    // Check whether an appointment exists
    public boolean appointmentExists(String appointmentID) {

        return getAppointmentByID(appointmentID) != null;
    }

    // Generate a new Appointment ID
    public String generateAppointmentID() {

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
                        && data[0].startsWith("A")) {

                    try {

                        int currentNumber =
                                Integer.parseInt(
                                        data[0].substring(1)
                                );

                        if (currentNumber >= number) {
                            number = currentNumber + 1;
                        }

                    } catch (NumberFormatException e) {

                        // Ignore invalid appointment ID
                    }
                }
            }

            input.close();

        } catch (FileNotFoundException e) {

            // File does not exist yet
        }

        return String.format("A%03d", number);
    }
}
