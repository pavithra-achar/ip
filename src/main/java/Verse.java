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
        ArrayList<String> list = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);

        while (proceed) {
            System.out.print("User : ");
            String sentence = sc.nextLine();

            if (sentence.equals("bye")) {
                proceed = false;
            } else if (sentence.equals("list")) {
                System.out.println("Verse : Here lies all that is noted:");

                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(sentence);
                System.out.println("Verse : " + sentence + " hath been added to list.");
            }
        }

        sc.close();

        System.out.println();
        System.out.println("ACT I - Scene 2");
        System.out.println(farewell);
    }
}
