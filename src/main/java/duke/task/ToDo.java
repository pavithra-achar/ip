package duke.task;
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
    public String toString() {
        return "[" + TODO_ID_SHORT + "]" + super.toString();
    }
}
