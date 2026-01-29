package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dateTime;

    public Deadline (String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String fileString() {
        return "deadline, " + this.description + ", " + this.dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

        return "[D]" + super.toString()
                + " (by: " + dateTime.format(formatter) + ")";
    }
}
