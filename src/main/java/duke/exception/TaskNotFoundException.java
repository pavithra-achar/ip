package duke.exception;

/**
 * Exception thrown when a task is not found at the specified position
 * or when the position is invalid.
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("No task is found at the given position.");
    }
}
