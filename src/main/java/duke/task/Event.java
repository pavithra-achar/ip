package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.IllegalParameterException;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String getFileString() {
        return "event, " + this.description + ", " + this.start + ", " + this.end + ", " + this.isDone;
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
        return "[E]"
                + super.toString()
                + "(from: " + this.start.format(formatter)
                + " to: " + this.end.format(formatter) + ")";
    }
}
