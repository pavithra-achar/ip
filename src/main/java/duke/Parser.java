package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.MissingParameterException;

public class Parser {

    LocalDateTime parseDateTime(String dateTimeString) throws MissingParameterException {
        LocalDateTime dateTime;

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter dFormatter  = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            // Try full datetime first
            dateTime = LocalDateTime.parse(dateTimeString, dtFormatter);
        } catch (DateTimeParseException e1) {
            try {
                // fallback to date only → default time 23:59
                LocalDate date = LocalDate.parse(dateTimeString, dFormatter);
                dateTime = date.atTime(23, 59);
            } catch (DateTimeParseException e2) {
                throw new MissingParameterException(
                    "Thy time must follow dd-mm-yyyy or dd-mm-yyyy HH:mm format."
                );
            }
        }

        return dateTime;
    }

    public Command parseCommand(String input) throws IllegalArgumentException {
        String[] parts = input.split(" ", 2);
        return Command.valueOf(parts[0].toUpperCase());
    }

    public String getDetails(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    public String[] parseDeadline(String desc) throws MissingParameterException {
        String[] details = desc.split("/");

        //If description is empty
        if (details[0].isEmpty())
            throw new MissingParameterException("Thy deadline description shall not be empty.");
            //If date/time is empty
        else if (details.length == 1)
            throw new MissingParameterException("Thy date and time shall not be empty.");

        details[0] = details[0].trim();
        details[1] = details[1].substring(2).trim(); 
        return details;
    }

    public String[] parseEvent(String desc) throws MissingParameterException {
        String[] details = desc.split("/");

        //If description is empty
        if (details[0].isEmpty())
            throw new MissingParameterException("Thy event description shall not be empty.");
            //If start date/time is empty
        else if (details.length == 1)
            throw new MissingParameterException("Thy starting date and time shall not be empty.");
            //If end date/time is empty
        else if (details.length == 2)
            throw new MissingParameterException("Thy ending date and time shall not be empty.");

        details[0] = details[0].trim();
        details[1] = details[1].substring(5).trim();
        details[2] = details[2].substring(3).trim();
        return details;
    }
}
