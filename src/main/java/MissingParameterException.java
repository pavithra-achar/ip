public class MissingParameterException extends DukeException {
    public MissingParameterException(String message) {
        super("Thy request is incomplete. Please provide all the required information. " + message);
    }
}
