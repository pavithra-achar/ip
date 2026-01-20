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
                        System.out.println((i + 1) + ". ["+ t.getStatusIcon() +"] " + t);
                    }
                    System.out.println();
                    break;

                case "mark" :
                    Task tMark = list.get(sc.nextInt() - 1);
                    tMark.setDoneStatus(true);
                    System.out.println("Verse : " + tMark + " is now complete.");
                    break;

                case "unmark" :
                    Task tUnmark = list.get(sc.nextInt() - 1);
                    tUnmark.setDoneStatus(false);
                    System.out.println("Verse : " + tUnmark + " is no longer complete.");
                    break;

                default :
                    sentence = sentence + sc.nextLine();
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
