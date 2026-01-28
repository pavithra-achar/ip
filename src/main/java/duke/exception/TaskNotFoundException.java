package duke.exception;
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("No task is found at the given position.");
    }
}
