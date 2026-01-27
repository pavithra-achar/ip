import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event (String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String fileString() {
        return "event, " + this.description + ", " + this.start + ", " + this.end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + "(from: " + this.start.format(formatter) + " to: " + this.end.format(formatter) + ")";
    }
}
