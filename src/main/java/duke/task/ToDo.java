package duke.task;
public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    public String fileString() {
        return "todo, " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
