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
            String sentence = sc.nextLine();

            switch (sentence) {
                case "bye" :
                    proceed = false;
                    break;
                case "list" :
                    System.out.println("Verse : Here lies all that is noted:");

                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i + 1) + ". ["+ t.getStatusIcon() +" ] " + t);
                    }
                    break;
                default :
                    Task t = new Task(sentence);
                    list.add(t);
                    System.out.println("Verse : " + sentence + " hath been added to list.");
            }
        }

        sc.close();

        System.out.println();
        System.out.println("Final Act");
        System.out.println(farewell);
    }
}
