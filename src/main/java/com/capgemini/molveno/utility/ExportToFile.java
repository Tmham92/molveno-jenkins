package com.capgemini.molveno.utility;

import com.capgemini.molveno.model.Booking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class ExportToFile {

    public File createNewFile () {
        try {
            File exportFile = new File("exportAllCurrentGuests.txt");
            if (exportFile.createNewFile()) {
                System.out.println("File created: " + exportFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            return exportFile;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public void writeToFile(Iterable<Booking> exportedBookings) {
        File exportFile = createNewFile();
        LocalDate currentDate = LocalDate.now();
        try {
            FileWriter writeExportFile = new FileWriter(exportFile);
            for (Booking booking : exportedBookings) {
                if (booking.getBeginDate().compareTo(currentDate) * currentDate.compareTo(booking.getEndDate()) >= 0) {
                    writeExportFile.write(booking.getGuest().getFirstName() + " " + booking.getGuest().getLastName() +
                            " booked from " + FormDateParser.convertStringToDateFormat(booking.getBeginDate()) +
                            " till " + FormDateParser.convertStringToDateFormat(booking.getEndDate())+ "\n") ;
                }
            }
            System.out.println("Successfully wrote to file");
            writeExportFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
