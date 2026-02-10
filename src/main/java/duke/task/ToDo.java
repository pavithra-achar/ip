package duke.task;
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getFileString() {
        return "todo, " + this.description + ", " + this.isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
