package duke.task;

import duke.exception.IllegalParameterException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDoneStatus(boolean done) {
        isDone = done;
    }

    public String getFileString() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public void editTask(String parameter, String newValue) throws IllegalParameterException {
        if (parameter.equals("desc")) {
            this.description = newValue;
        } else {
            throw new IllegalParameterException("Invalid field for Task. Only 'desc' is allowed.");
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
