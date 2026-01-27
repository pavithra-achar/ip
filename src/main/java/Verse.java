import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Verse {
    private boolean proceed = true;
    private ArrayList<Task> list;

    public static void main(String[] args) {
        Verse bot = new Verse();

        String greeting = "Good day user, I am Verse. Thy words await my wit.\n" +
                "Speak, and declare thy query.\n";
        String farewell = "Till next thou call’st this system forth, farewell.\n";

        System.out.println("ACT I - Scene 1");
        System.out.println(greeting);

        bot.list = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);

        while (bot.proceed) {
            System.out.print("User : ");
            String sentence = sc.nextLine();
            String[] command = sentence.split(" ", 2);
            try {
                Command c = Command.valueOf(command[0].toUpperCase());
                String details = "";
                if(command.length > 1)
                    details = command[1];
                else
                    details = "";
                c.execute(bot, details);

            } catch (DukeException e) {
                System.out.println("Verse : " + e.getMessage());
            } catch (IllegalArgumentException e) {
                DukeException dE = new DukeException();
                System.out.println("Verse : " + dE.getMessage());
            }
        }

        sc.close();

        System.out.println();
        System.out.println("Final Act");
        System.out.println(farewell);
    }

    void exitProgram() {
        proceed = false;
    }

    void listTasks() {
        System.out.println("Verse : Here lies all that is noted:\n");

        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println((i + 1) + ". " + t);
        }
        System.out.println();
    }

    void markTaskAsDone(int index) throws TaskNotFoundException {
        //Index out of bounds error
        if(index > list.size())
            throw new TaskNotFoundException();

        Task tMark = list.get(index - 1);
        tMark.setDoneStatus(true);

        System.out.println("Verse : Task is now complete.");
    }

    void unmarkTaskAsDone(int index) throws TaskNotFoundException {
        if(index > list.size())
            throw new TaskNotFoundException();

        Task tUnmark = list.get(index - 1);
        tUnmark.setDoneStatus(false);

        System.out.println("Verse : Task is no longer complete.");
    }

    void createTodoTask(String desc) throws MissingParameterException {
        if(desc.isEmpty())
            throw new MissingParameterException("Thy todo description shall not be empty.");

        Task toDo = new ToDo(desc);
        list.add(toDo);

        System.out.println("Verse : " + desc + " hath been added to list.");

        //Display number of tasks
        System.out.println("There are " + list.size() + " tasks in thy list.");
    }

    void createDeadlineTask(String desc) throws MissingParameterException {
        String[] details = desc.split("/");

        //If description is empty
        if (details[0].isEmpty())
            throw new MissingParameterException("Thy deadline description shall not be empty.");
            //If date/time is empty
        else if (details.length == 1)
            throw new MissingParameterException("Thy date and time shall not be empty.");

        Task deadline = new Deadline(details[0].trim(),
                details[1].substring(2).trim());
        list.add(deadline);
        System.out.println("Verse : " + details[0].trim() + " hath been added to list.");

        //Display number of tasks
        System.out.println("There are " + list.size() + " tasks in thy list.");
    }

    void createEventTask(String desc) throws MissingParameterException {
        String[] details = desc.split("/");

        //If description is empty
        if (details[0].isEmpty())
            throw new MissingParameterException("Thy event description shall not be empty.");
            //If start date/time is empty
        else if (details.length == 1)
            throw new MissingParameterException("Thy starting date and time shall not be empty.");
            //If end date/time is empty
        else if (details.length == 2)
            throw new MissingParameterException("Thy ending date and time shall not be empty.");

        Task event = new Event(details[0].trim(),
                details[1].substring(5).trim(),
                details[2].substring(3).trim());
        list.add(event);
        System.out.println("Verse : " + details[0].trim() + " hath been added to list.");

        //Display number of tasks
        System.out.println("There are " + list.size() + " tasks in thy list.");
    }

    void deleteTask(int index) throws TaskNotFoundException {
        if(index > list.size())
            throw new TaskNotFoundException();

        Task tDelete = list.remove(index - 1);

        System.out.println("Verse : Duly noted. The following task is no longer in the list: \n" + tDelete );
    }
}
