package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.IllegalParameterException;

/**
 * Represents a Deadline task which has a description and a date/time by which it should be completed.
 */
public class Deadline extends Task {
    public static final String DEADLINE_ID_SHORT = "D";
    public static final String DEADLINE_ID = "deadline";
    protected LocalDateTime dateTime;

    /**
     * Constructs a Deadline task with the given description and date/time.
     *
     * @param description The description of the deadline task.
     * @param dateTime The date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a Deadline task with the given description, date/time, and completion status.
     *
     * @param description The description of the deadline task.
     * @param dateTime The date and time by which the task should be completed.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns a string representation of the deadline task for file storage.
     *
     * @return A string in the format "deadline,description,dateTime,isDone".
     */
    public String getFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return DEADLINE_ID + "," + this.description + "," + this.dateTime.format(formatter) + "," + this.isDone;
    }

    @Override
    public void editTask(String parameter, String newValue) throws IllegalParameterException {
        switch (parameter) {
        case "desc":
            this.description = newValue;
            break;
        case "by":
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.dateTime = LocalDateTime.parse(newValue, formatter);
            break;
        default:
            throw new IllegalParameterException("Invalid field for Deadline task. Only 'desc' and 'by' are allowed.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

        return "[" + DEADLINE_ID_SHORT + "]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }
}
