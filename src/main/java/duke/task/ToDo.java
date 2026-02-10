package duke.task;

import duke.exception.IllegalParameterException;

public class ToDo extends Task {

    public static final String TODO_ID_SHORT = "T";
    public static final String TODO_ID = "todo";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getFileString() {
        return TODO_ID + "," + this.description + "," + this.isDone;
    }

    @Override
    public void editTask(String parameter, String newValue) throws IllegalParameterException {
        switch(parameter) {
            case "desc":
                this.description = newValue;
                break;
            default:
                throw new IllegalParameterException("Invalid field for ToDo task. Only 'desc' is allowed.");
        }
    }

    @Override
    public String toString() {
        return "[" + TODO_ID_SHORT + "]" + super.toString();
    }


}
