package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles user interactions by displaying messages and prompts.
 */
public class Ui {
    /**
     * Displays a message to the user with the prefix "Verse :".
     *
     * @param message The message to be displayed.
     */
    public String showMessage(String message) {
        System.out.println("Verse : " + message);
        return message;
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasks The list of tasks to be displayed.
     * @param header The header message to be displayed before the tasks.
     */
    public String showList(ArrayList<Task> tasks, String header) {
        String output = showMessage(header);
        
        for (int i = 0; i < tasks.size(); i++) {
            output += "\n" + ((i + 1) + ". " + tasks.get(i));
        }
        return output;
    }

    /**
     * Displays a greeting message to the user.
     */
    public String showGreeting() {
        String greeting = "Good day user, I am Verse. Thy words await my wit.\n"
                + "Speak, and declare thy query.\n";
        System.out.println("ACT I - Scene 1");
        System.out.println(greeting);
        return "Act I - Scene 1\n" + greeting;
    }

    /**
     * Displays a farewell message to the user.
     */
    public String showFarewell() {
        String farewell = "Till next thou call’st this system forth, farewell.\n";
        System.out.println("Final Act");
        System.out.println(farewell);
        return "Final Act\n" + farewell;
    }
}
