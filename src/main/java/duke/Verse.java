package duke;

import java.io.IOException;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Main class of the Verse application.
 */
public class Verse {
    private boolean canProceed = true;
    private TaskList list;
    private Ui ui = new Ui();
    private Storage storage;
    private Parser parser = new Parser();

    public Verse() {
        try {
            storage = new Storage("./data", "Verse.txt");
            list = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            DukeException dukeException = new DukeException();
            ui.showMessage(dukeException.getMessage());
        }
    }

    String handleInput(String input) {
        try {
            Command c = parser.parseCommand(input);
            String details = parser.getDetails(input);
            return c.execute(this, details);
        } catch (DukeException e) {
            return ui.showMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            DukeException dE = new DukeException();
            return ui.showMessage(dE.getMessage());
        }
    }

    /**
     * Exits the program after saving tasks to storage.
     */
    String exitProgram() {
        storage.saveTasks(list.getAll());
        canProceed = false;
        return ui.showFarewell();

    }

    /** 
     * Lists all tasks in the task list.
     */

    String listTasks() {
        return ui.showList(list.getAll(), "Here lies all that is noted:\n");
    }

    /**
     * Marks a task as done based on the given index.
     * @param index the index of the task to be marked as done.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    String markTaskAsDone(int index) throws TaskNotFoundException {
        list.markDone(index - 1);

        return ui.showMessage("Task is now complete.");
    }

    /**
     * Unmarks a task as done based on the given index.
     * @param index the index of the task to be unmarked as done.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    String unmarkTaskAsDone(int index) throws TaskNotFoundException {
       list.unmarkDone(index - 1);

        return ui.showMessage("Task is no longer complete.");
    }

    /** 
     * Creates a ToDo task and adds it to the task list.
     * 
     * @param desc the description of the ToDo task.
     * @throws MissingParameterException if the description is missing.
     */
    String createTodoTask(String desc) throws MissingParameterException {
        if(desc.isEmpty())
            throw new MissingParameterException("Thy todo description shall not be empty.");

        Task toDo = new ToDo(desc);
        list.add(toDo);

        String message = ui.showMessage(desc + " hath been added to list.");

        //Display number of tasks
        return message + "\n" + ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    /** 
     * Creates a Deadline task and adds it to the task list.
     * 
     * @param desc the description and deadline of the Deadline task.
     * @throws MissingParameterException if the description or deadline is missing.
     */
    String createDeadlineTask(String desc) throws MissingParameterException {
        String[] details = parser.parseDeadline(desc);

        Task deadline = new Deadline(details[0],
                parser.parseDateTime(details[1]));
        list.add(deadline);
        String message = ui.showMessage(details[0] + " hath been added to list.");

        //Display number of tasks
        return message + "\n" + ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    /** 
     * Creates an Event task and adds it to the task list.
     * 
     * @param desc the description, start time, and end time of the Event task.
     * @throws MissingParameterException if the description, start time, or end time is missing.
     */
    String createEventTask(String desc) throws MissingParameterException {
        String[] details = parser.parseEvent(desc);

        Task event = new Event(details[0],
                parser.parseDateTime(details[1]),
                parser.parseDateTime(details[2]));
        list.add(event);
        String message = ui.showMessage(details[0] + " hath been added to list.");

        //Display number of tasks
        return message + "\n" + ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    /** 
     * Deletes a task from the task list based on the given index.
     * 
     * @param index the index of the task to be deleted.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    String deleteTask(int index) throws TaskNotFoundException {
        if(index > list.size())
            throw new TaskNotFoundException();

        Task task = list.remove(index - 1);

        return ui.showMessage("Duly noted. The following task is no longer in the list: \n" + task);
    }

    String findTasks(String keyword) throws MissingParameterException {
        if (keyword.isEmpty()) {
            throw new MissingParameterException("Thy search keyword shall not be empty.");
        }

        TaskList foundTasks = list.findTasks(keyword);
        return ui.showList(foundTasks.getAll(), "Here are the tasks that match thy search:\n");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String greet() {
        return ui.showGreeting();
    }
}
