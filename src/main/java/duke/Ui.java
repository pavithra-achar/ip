package duke;
import java.util.ArrayList;

import duke.task.Task;

public class Ui {
    public void showMessage(String message) {
        System.out.println("Verse : " + message);
    }

    public void showList(ArrayList<Task> tasks) {
        showMessage("Here lies all that is noted:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println();
    }

    public void readCommand() {
        System.out.print("User : ");
    }

    public void showGreeting() {
        String greeting = "Good day user, I am Verse. Thy words await my wit.\n" +
                          "Speak, and declare thy query.\n";
        System.out.println("ACT I - Scene 1");
        System.out.println(greeting);
    }

    public void showFarewell() {
        String farewell = "Till next thou call’st this system forth, farewell.\n";
        System.out.println("Final Act");
        System.out.println(farewell);
    }
}