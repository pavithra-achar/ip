import java.util.ArrayList;
import java.util.Scanner;

public class Verse {
    public static void main(String[] args) {
        String greeting = "Good day user, I am Verse. Thy words await my wit.\n" +
                "Speak, and declare thy query.\n";
        String farewell = "Till next thou call’st this system forth, farewell.\n";

        System.out.println("ACT I - Scene 1");
        System.out.println(greeting);

        boolean proceed = true;
        ArrayList<Task> list = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);

        while (proceed) {
            System.out.print("User : ");
            String sentence = sc.next();

            try {
                switch (sentence) {
                    //exit program
                    case "bye":
                        proceed = false;
                        break;

                    //display list
                    case "list":
                        System.out.println("Verse : Here lies all that is noted:\n");

                        for (int i = 0; i < list.size(); i++) {
                            Task t = list.get(i);
                            System.out.println((i + 1) + ". " + t);
                        }
                        System.out.println();
                        break;

                    //mark as done
                    case "mark":
                        //index of task to be marked as done
                        int indexMark = sc.nextInt();

                        //Index out of bounds error
                        if(indexMark > list.size())
                            throw new TaskNotFoundException();

                        Task tMark = list.get(indexMark - 1);
                        tMark.setDoneStatus(true);

                        System.out.println("Verse : Task is now complete.");
                        break;

                    //mark as undone
                    case "unmark":
                        //index of task to be marked as undone
                        int indexUnMark = sc.nextInt();

                        //Index out of bounds error
                        if(indexUnMark > list.size())
                            throw new TaskNotFoundException();

                        Task tUnmark = list.get(indexUnMark - 1);
                        tUnmark.setDoneStatus(false);

                        System.out.println("Verse : Task is no longer complete.");
                        break;

                    //Add a todo task
                    case "todo":
                        String descTodo = sc.nextLine().trim();
                        //If description is empty
                        if(descTodo.isEmpty())
                            throw new MissingParameterException("Thy todo description shall not be empty.");

                        Task toDo = new ToDo(descTodo);
                        list.add(toDo);

                        System.out.println("Verse : " + descTodo + " hath been added to list.");

                        //Display number of tasks
                        System.out.println("There are " + list.size() + " tasks in thy list.");
                        break;

                    //Add a deadline task
                    case "deadline":
                        String descDeadline = sc.nextLine().trim();
                        String[] deadlineDetails = descDeadline.split("/");

                        //If description is empty
                        if (deadlineDetails[0].isEmpty())
                            throw new MissingParameterException("Thy deadline description shall not be empty.");
                        //If date/time is empty
                        else if (deadlineDetails.length == 1)
                            throw new MissingParameterException("Thy date and time shall not be empty.");

                        Task deadline = new Deadline(deadlineDetails[0].trim(),
                                deadlineDetails[1].substring(2).trim());
                        list.add(deadline);
                        System.out.println("Verse : " + deadlineDetails[0].trim() + " hath been added to list.");

                        //Display number of tasks
                        System.out.println("There are " + list.size() + " tasks in thy list.");
                        break;

                    case "event":
                        String descEvent = sc.nextLine().trim();
                        String[] eventDetails = descEvent.split("/");

                        //If description is empty
                        if (eventDetails[0].isEmpty())
                            throw new MissingParameterException("Thy event description shall not be empty.");
                        //If start date/time is empty
                        else if (eventDetails.length == 1)
                            throw new MissingParameterException("Thy starting date and time shall not be empty.");
                        //If end date/time is empty
                        else if (eventDetails.length == 2)
                            throw new MissingParameterException("Thy ending date and time shall not be empty.");

                        Task event = new Event(eventDetails[0].trim(),
                                eventDetails[1].substring(5).trim(),
                                eventDetails[2].substring(3).trim());
                        list.add(event);
                        System.out.println("Verse : " + eventDetails[0].trim() + " hath been added to list.");

                        //Display number of tasks
                        System.out.println("There are " + list.size() + " tasks in thy list.");
                        break;

                    case "delete" :
                        //index of task to be deleted
                        int indexDelete = sc.nextInt();

                        //Index out of bounds error
                        if(indexDelete > list.size())
                            throw new TaskNotFoundException();

                        Task tDelete = list.remove(indexDelete - 1);

                        System.out.println("Verse : Duly noted. The following task is no longer in the list: \n" + tDelete );
                        break;

                    default:
                        throw new UnknownCommandException();
                }

            } catch (DukeException e) {
                System.out.println("Verse : " + e.getMessage());
            }
        }

        sc.close();

        System.out.println();
        System.out.println("Final Act");
        System.out.println(farewell);
    }
}
