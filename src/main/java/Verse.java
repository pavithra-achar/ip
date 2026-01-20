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

            switch (sentence) {
                case "bye" :
                    proceed = false;
                    break;

                case "list" :
                    System.out.println("Verse : Here lies all that is noted:\n");

                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i + 1) + ". " + t);
                    }
                    System.out.println();
                    break;

                case "mark" :
                    Task tMark = list.get(sc.nextInt() - 1);
                    tMark.setDoneStatus(true);
                    System.out.println("Verse : Task is now complete.");
                    break;

                case "unmark" :
                    Task tUnmark = list.get(sc.nextInt() - 1);
                    tUnmark.setDoneStatus(false);
                    System.out.println("Verse : Task is no longer complete.");
                    break;

                case "todo" :
                    String descTodo = sc.nextLine().trim();
                    Task toDo = new ToDo(descTodo);
                    list.add(toDo);
                    System.out.print("Verse : " + descTodo + " hath been added to list.");
                    System.out.println("There are " + list.size() + " tasks in thy list.");
                    break;

                case "deadline" :
                    String descDeadline = sc.nextLine().trim();
                    String[] deadlineDetails = descDeadline.split("/");

                    Task deadline = new Deadline(deadlineDetails[0].trim(),
                                                 deadlineDetails[1].substring(2).trim());
                    list.add(deadline);
                    System.out.print("Verse : " + deadlineDetails[0].trim() + " hath been added to list.");
                    System.out.println("There are " + list.size() + " tasks in thy list.");
                    break;

                case "event" :
                    String descEvent = sc.nextLine().trim();
                    String[] eventDetails = descEvent.split("/");

                    Task event = new Event(eventDetails[0].trim(),
                                           eventDetails[1].substring(5).trim(),
                                           eventDetails[2].substring(4).trim());
                    list.add(event);
                    System.out.print("Verse : " + eventDetails[0].trim() + " hath been added to list.");
                    System.out.println("There are " + list.size() + " tasks in thy list.");
                    break;

                default :
                    sentence = sc.nextLine();
                    Task tDefault = new Task(sentence);
                    list.add(tDefault);
                    System.out.println("Verse : " + sentence + " hath been added to list.");
            }
        }

        sc.close();

        System.out.println();
        System.out.println("Final Act");
        System.out.println(farewell);
    }
}
