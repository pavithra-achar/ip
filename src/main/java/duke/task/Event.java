package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.IllegalParameterException;

/**
 * Represents an Event task which has a description, a start date/time, and an end date/time.
 */
public class Event extends Task {
    public static final String EVENT_ID_SHORT = "E";
    public static final String EVENT_ID = "event";
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task with the given description, start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with the given description, start date/time, end date/time, and completion status.
     *
     * @param description The description of the event task.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     * @param isDone The completion status of the event task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task for file storage.
     *
     * @return A string in the format "event,description,start,end,isDone".
     */
    public String getFileString() {
        return EVENT_ID + "," + this.description + "," + this.start + "," + this.end + "," + this.isDone;
    }

    @Override
    public void editTask(String parameter, String newValue) throws IllegalParameterException {
        switch (parameter) {
        case "desc":
            this.description = newValue;
            break;
        case "start":
            this.start = LocalDateTime.parse(newValue);
            break;
        case "end":
            this.end = LocalDateTime.parse(newValue);
            break;
        default:
            throw new IllegalParameterException("Invalid field for Event task. Only 'desc', 'start', and 'end' are allowed.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[" + EVENT_ID_SHORT + "]"
                + super.toString()
                + "(from: " + this.start.format(formatter)
                + " to: " + this.end.format(formatter) + ")";
    }
}
