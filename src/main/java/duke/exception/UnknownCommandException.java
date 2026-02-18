package duke.exception;

/**
 * Exception thrown when the user inputs a command
 * that is not in the list of supported commands.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Alas, I know not what thou mean'st.");
    }
}
