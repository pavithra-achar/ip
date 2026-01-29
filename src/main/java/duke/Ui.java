package duke;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles user interactions by displaying messages and prompts.
 */
public class Ui {
    /**
     * Displays a message to the user with the prefix "Verse : ".
     * 
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println("Verse : " + message);
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasks
     */
    public void showList(ArrayList<Task> tasks) {
        showMessage("Here lies all that is noted:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println();
    }

    /**
     * Prompts the user for a command with a prefix "User : ".
     */
    public void readCommand() {
        System.out.print("User : ");
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        String greeting = "Good day user, I am Verse. Thy words await my wit.\n" +
                          "Speak, and declare thy query.\n";
        System.out.println("ACT I - Scene 1");
        System.out.println(greeting);
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showFarewell() {
        String farewell = "Till next thou call’st this system forth, farewell.\n";
        System.out.println("Final Act");
        System.out.println(farewell);
    }
}