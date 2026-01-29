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

    public static void main(String[] args) {
        Verse bot = new Verse();
        
        bot.ui.showGreeting();

        try {
            bot.storage = new Storage("./data", "Verse.txt");
            bot.list = new TaskList(bot.storage.loadTasks());
        } catch (IOException e) {
            DukeException dukeException = new DukeException();
            bot.ui.showMessage(dukeException.getMessage());
        }
            
        Scanner sc = new Scanner(System.in);

        while (bot.canProceed) {
            bot.ui.readCommand();
            String sentence = sc.nextLine();
            
            try {
                Command c = bot.parser.parseCommand(sentence);
                String details = bot.parser.getDetails(sentence);
                c.execute(bot, details);
            } catch (DukeException e) {
                bot.ui.showMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                DukeException dE = new DukeException();
                bot.ui.showMessage(dE.getMessage());
            }
        }

        sc.close();
        bot.ui.showFarewell();
    }

    /**
     * Exits the program after saving tasks to storage.
     */
    void exitProgram() {
        storage.saveTasks(list.getAll());
        canProceed = false;
    }

    /** 
     * Lists all tasks in the task list.
     * 
     * @throws TaskNotFoundException
     */

    void listTasks() throws TaskNotFoundException {
        ui.showList(list.getAll(), "Here lies all that is noted:\n");
    }

    /**
     * Marks a task as done based on the given index.
     * @param index the index of the task to be marked as done.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    void markTaskAsDone(int index) throws TaskNotFoundException {
        list.markDone(index - 1);

        ui.showMessage("Task is now complete.");
    }

    /**
     * Unmarks a task as done based on the given index.
     * @param index the index of the task to be unmarked as done.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    void unmarkTaskAsDone(int index) throws TaskNotFoundException {
       list.unmarkDone(index - 1);

        ui.showMessage("Task is no longer complete.");
    }

    /** 
     * Creates a ToDo task and adds it to the task list.
     * 
     * @param desc the description of the ToDo task.
     * @throws MissingParameterException if the description is missing.
     */
    void createTodoTask(String desc) throws MissingParameterException {
        if(desc.isEmpty())
            throw new MissingParameterException("Thy todo description shall not be empty.");

        Task toDo = new ToDo(desc);
        list.add(toDo);

        ui.showMessage(desc + " hath been added to list.");

        //Display number of tasks
        ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    /** 
     * Creates a Deadline task and adds it to the task list.
     * 
     * @param desc the description and deadline of the Deadline task.
     * @throws MissingParameterException if the description or deadline is missing.
     */
    void createDeadlineTask(String desc) throws MissingParameterException {
        String[] details = parser.parseDeadline(desc);

        Task deadline = new Deadline(details[0],
                parser.parseDateTime(details[1]));
        list.add(deadline);
        ui.showMessage(details[0] + " hath been added to list.");

        //Display number of tasks
        ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    /** 
     * Creates an Event task and adds it to the task list.
     * 
     * @param desc the description, start time, and end time of the Event task.
     * @throws MissingParameterException if the description, start time, or end time is missing.
     */
    void createEventTask(String desc) throws MissingParameterException {
        String[] details = parser.parseEvent(desc);

        Task event = new Event(details[0],
                parser.parseDateTime(details[1]),
                parser.parseDateTime(details[2]));
        list.add(event);
        ui.showMessage(details[0] + " hath been added to list.");

        //Display number of tasks
        ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    /** 
     * Deletes a task from the task list based on the given index.
     * 
     * @param index the index of the task to be deleted.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    void deleteTask(int index) throws TaskNotFoundException {
        if(index > list.size())
            throw new TaskNotFoundException();

        Task task = list.remove(index - 1);

        ui.showMessage("Duly noted. The following task is no longer in the list: \n" + task);
    }

    void findTasks(String keyword) throws MissingParameterException {
        if (keyword.isEmpty()) {
            throw new MissingParameterException("Thy search keyword shall not be empty.");
        }

        TaskList foundTasks = list.findTasks(keyword);
        ui.showList(foundTasks.getAll(), "Here are the tasks that match thy search:\n");
    }
}
