package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    public static final String EVENT_ID_SHORT = "E";
    public static final String EVENT_ID = "event";

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
        return EVENT_ID + "," + this.description + "," + this.start + "," + this.end + "," + this.isDone;
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
