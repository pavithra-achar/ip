package duke.task;

import duke.exception.IllegalParameterException;

/**
 * Represents a general task with a description and completion status.
 * This class serves as a base for specific types of tasks like ToDo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
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

    /**
     * Edits the task based on the specified parameter and new value.
     *
     * @param parameter The field to edit (e.g., "desc" for description).
     * @param newValue The new value to set for the specified field.
     * @throws IllegalParameterException If the parameter is invalid for this task type.
     */
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
