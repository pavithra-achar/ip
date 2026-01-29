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

    void exitProgram() {
        storage.saveTasks(list.getAll());
        canProceed = false;
    }

    void listTasks() {
        ui.showList(list.getAll());
    }

    void markTaskAsDone(int index) throws TaskNotFoundException {

        Task tMark = list.get(index - 1);
        tMark.setDoneStatus(true);

        ui.showMessage("Task is now complete.");
    }

    void unmarkTaskAsDone(int index) throws TaskNotFoundException {
        Task tUnmark = list.get(index - 1);
        tUnmark.setDoneStatus(false);

        ui.showMessage("Task is no longer complete.");
    }

    void createTodoTask(String desc) throws MissingParameterException {
        if(desc.isEmpty())
            throw new MissingParameterException("Thy todo description shall not be empty.");

        Task toDo = new ToDo(desc);
        list.add(toDo);

        ui.showMessage(desc + " hath been added to list.");

        //Display number of tasks
        ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

    void createDeadlineTask(String desc) throws MissingParameterException {
        String[] details = parser.parseDeadline(desc);

        Task deadline = new Deadline(details[0],
                parser.parseDateTime(details[1]));
        list.add(deadline);
        ui.showMessage(details[0] + " hath been added to list.");

        //Display number of tasks
        ui.showMessage("There are " + list.size() + " tasks in thy list.");
    }

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

    void deleteTask(int index) throws TaskNotFoundException {
        if(index > list.size())
            throw new TaskNotFoundException();

        Task task = list.remove(index - 1);

        ui.showMessage("Duly noted. The following task is no longer in the list: \n" + task);
    }
}
