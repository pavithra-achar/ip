package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.IllegalParameterException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Main class of the Verse application.
 */
public class Verse {
    private TaskList list;
    private Ui ui = new Ui();
    private Storage storage;
    private Parser parser = new Parser();

    /**
     * Constructs a Verse object and initializes the task list from storage.
     */
    public Verse() {
        try {
            storage = new Storage("./data", "Verse.txt");
            list = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            DukeException dukeException = new DukeException("Loading thy tasks from storage hath failed.");
            ui.showErrorMessage(dukeException.getMessage());
        }
    }

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param input The user input string.
     * @return The response message to be displayed to the user.
     */
    String handleInput(String input) {
        assert storage != null : "Storage should be initialized";
        assert list != null : "TaskList should be initialized";
        assert ui != null : "UI should be initialized";
        assert parser != null : "Parser should be initialized";
        try {
            Command c = parser.parseCommand(input);
            String details = parser.getDetails(input);
            return c.execute(this, details);
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            UnknownCommandException ue = new UnknownCommandException();
            return ui.showErrorMessage(ue.getMessage());
        }
    }

    /**
     * Exits the program after saving tasks to storage.
     */
    String exitProgram() {
        storage.saveTasks(list.getAll());
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

        return Ui.MARK_DONE;
    }

    /**
     * Unmarks a task as done based on the given index.
     * @param index the index of the task to be unmarked as done.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    String unmarkTaskAsDone(int index) throws TaskNotFoundException {
        list.unmarkDone(index - 1);

        return Ui.MARK_UNDONE;
    }

    /**
     * Creates a ToDo task and adds it to the task list.
     *
     * @param desc the description of the ToDo task.
     * @throws MissingParameterException if the description is missing.
     */
    String createTodoTask(String desc) throws MissingParameterException {
        if (desc.isEmpty()) {
            throw new MissingParameterException("Thy todo description shall not be empty.");
        }

        Task toDo = new ToDo(desc);
        list.add(toDo);

        return ui.showTaskAdded(desc, list.size());
    }

    /**
     * Creates a Deadline task and adds it to the task list.
     *
     * @param desc the description and deadline of the Deadline task.
     * @throws MissingParameterException if the description or deadline is missing.
     */
    String createDeadlineTask(String desc) throws MissingParameterException {
        String[] details = parser.parseDeadline(desc);
        if (details.length != 2) {
            throw new MissingParameterException("Thy deadline description and date/time shall not be empty.");
        }

        Task deadline = new Deadline(details[0], parser.parseDateTime(details[1]));
        list.add(deadline);
        return ui.showTaskAdded(details[0], list.size());
    }

    /**
     * Creates an Event task and adds it to the task list.
     *
     * @param desc the description, start time, and end time of the Event task.
     * @throws MissingParameterException if the description, start time, or end time is missing.
     */
    String createEventTask(String desc) throws MissingParameterException {
        String[] details = parser.parseEvent(desc);

        if (details.length != 3) {
            throw new MissingParameterException("Thy event description, start time, and end time shall not be empty.");
        }

        Task event = new Event(details[0],
                parser.parseDateTime(details[1]),
                parser.parseDateTime(details[2]));
        list.add(event);
        return ui.showTaskAdded(details[0], list.size());
    }

    /**
     * Deletes a task from the task list based on the given index.
     *
     * @param index the index of the task to be deleted.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    String deleteTask(int index) throws TaskNotFoundException {
        if (index > list.size()) {
            throw new TaskNotFoundException();
        }

        Task task = list.remove(index - 1);

        return Ui.TASK_DELETED + task;
    }

    String findTasks(String keyword) throws MissingParameterException {
        if (keyword.isEmpty()) {
            throw new MissingParameterException("Thy search keyword shall not be empty.");
        }

        TaskList foundTasks = list.findTasks(keyword);
        return ui.showList(foundTasks.getAll(), "Here are the tasks that match thy search:\n");
    }

    String editTask(String args) throws TaskNotFoundException {
        String[] parts = args.split(" ", 3);
        if (parts.length < 3) {
            return "Thy edit command is incomplete. Please specify the task index, field to edit, and new value.";
        }
        try {
            Task task = list.editTask(parts[0], parts[1], parts[2]);
            return Ui.TASK_EDITED + task;
        } catch (IllegalParameterException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
    /**
     *  * Generates a response for the user's chat message.
     */
    public String greet() {
        return ui.showGreeting();
    }
}
