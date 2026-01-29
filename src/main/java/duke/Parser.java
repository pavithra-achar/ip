package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.MissingParameterException;

/**
 * Parses user input and date/time strings.
 */
public class Parser {

    /**
     * Parses a date/time string in the format of dd-MM-yyyy HH:mm or dd-MM-yyyy 
     * into a LocalDateTime object.
     * 
     * @param dateTimeString The date/time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws MissingParameterException If the date/time string is in an invalid format.
     */
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

    /** 
     * Parses the command from the user input string into a Command enum value.
     * 
     * @param input The user input string.
     * @return The Command enum corresponding to the parsed command.
     * @throws IllegalArgumentException If the command is not from the Command enum.
     */
    public Command parseCommand(String input) throws IllegalArgumentException {
        String[] parts = input.split(" ", 2);
        return Command.valueOf(parts[0].toUpperCase());
    }

    /** 
     * Extracts the details part of the user input string after the command.
     * 
     * @param input The user input string.
     * @return The string containing the details after the command.
     */
    public String getDetails(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    /** 
     * Parses the description and date/time from the Deadline task input string.
     * 
     * @param desc the description and date/time of the Deadline task.
     * @return A string array where the first element is the description 
     * and the second is the deadline.
     * @throws MissingParameterException if the description or deadline is missing.
     */
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

    /** 
     * Parses the description, start time, and end time from the Event task input string.
     * 
     * @param desc the description, start time, and end time of the Event task.
     * @return A string array where the first element is the description, 
     * the second is the start time, and the third is the end time.
     * @throws MissingParameterException if the description, start time, or end time is missing.
     */
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
