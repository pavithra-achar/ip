package duke;
import java.util.ArrayList;

import duke.exception.IllegalParameterException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

/**
 * Represents a list of tasks in the Verse application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param index The 0-based index of the task to remove.
     * @return The removed task.
     * @throws TaskNotFoundException If the index is out of bounds.
     */
    public Task remove(int index) throws TaskNotFoundException {
        if (index < 0 || index > tasks.size()) {
            throw new TaskNotFoundException();
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws TaskNotFoundException If the index is out of bounds.
     */
    public Task get(int index) throws TaskNotFoundException {
        if (index < 0 || index > tasks.size()) {
            throw new TaskNotFoundException();
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

    public void markDone(int index) throws TaskNotFoundException {
        get(index).setDoneStatus(true);
    }

    public void unmarkDone(int index) throws TaskNotFoundException {
        get(index).setDoneStatus(false);
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A new TaskList containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        tasks.stream().filter(task -> task.getDescription().contains(keyword)).forEach(foundTasks::add);
        return new TaskList(foundTasks);
    }

    /**
     * Edits a task's field with a new value.
     *
     * @param index The 1-based index of the task to edit.
     * @param field The field to edit (e.g., "desc" for description).
     * @param newValue The new value to set for the specified field.
     * @return The edited task.
     * @throws IllegalParameterException If the parameter is invalid for this task type.
     * @throws TaskNotFoundException If the index is out of bounds.
     */
    public Task editTask(String index, String field, String newValue) throws IllegalParameterException, TaskNotFoundException {
        Task task = get(Integer.parseInt(index) - 1);
        try {
            task.editTask(field, newValue);
        } catch (IllegalParameterException e) {
            throw new IllegalParameterException(e.getMessage());
        }
        return task;
    }
}
