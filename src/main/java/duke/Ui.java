package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles user interactions by displaying messages and prompts.
 */
public class Ui {

    public static final String MARK_DONE = "Task is now complete.";
    public static final String MARK_UNDONE = "Task is no longer complete.";
    public static final String TASK_DELETED = "Duly noted. The following task is no longer in the list: \n";
    public static final String TASK_EDITED = "Thy task hath been updated to: \n";
    private static final String GREETING = "Act I - Scene 1 \nGood day user, I am Verse. Thy words await my wit.\n"
            + "Speak, and declare thy query.\n";
    private static final String FAREWELL = "Final Act \nTill next thou call’st this system forth, farewell.\n";

    private static final String TASK_ADDED = " hath been added to list.";

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showErrorMessage(String message) {
        return "err" + message;
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasks The list of tasks to be displayed.
     * @param header The header message to be displayed before the tasks.
     */
    public String showList(ArrayList<Task> tasks, String header) {
        String output = header;
        for (int i = 0; i < tasks.size(); i++) {
            output += "\n" + ((i + 1) + ". " + tasks.get(i));
        }
        return "list" + output;
    }

    /**
     * Displays a greeting message to the user.
     */
    public String showGreeting() {
        return GREETING;
    }

    /**
     * Displays a farewell message to the user.
     */
    public String showFarewell() {
        return FAREWELL;
    }

    public String showTaskAdded(String desc, int listSize) {
        return desc + TASK_ADDED + "\n" + "There are " + listSize + " tasks in thy list.";
    }
}
