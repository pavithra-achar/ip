package duke.exception;

/**
 * Exception thrown when a parameter provided by the user is
 * invalid or does not meet the expected format.
 */
public class IllegalParameterException extends DukeException {
    public IllegalParameterException(String message) {
        super(message);
    }
}
