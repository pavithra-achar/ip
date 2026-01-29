package duke;
import java.util.ArrayList;

import duke.exception.TaskNotFoundException;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws TaskNotFoundException {
        if (index <= 0 || index > tasks.size()) throw new TaskNotFoundException();
        return tasks.remove(index - 1);
    }

    public Task get(int index) throws TaskNotFoundException {
        if (index <= 0 || index > tasks.size()) throw new TaskNotFoundException();
        return tasks.get(index - 1);
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

    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
