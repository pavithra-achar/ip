package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    public static final String DEADLINE_ID_SHORT = "D";
    public static final String DEADLINE_ID = "deadline";

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Deadline(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public String getFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return DEADLINE_ID + "," + this.description + "," + this.dateTime.format(formatter) + "," + this.isDone;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

        return "[" + DEADLINE_ID_SHORT + "]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }
}
