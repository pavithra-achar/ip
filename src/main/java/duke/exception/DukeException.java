package duke.exception;

/**
 * General exception class for chatbot errors.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
