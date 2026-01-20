import java.util.Scanner;

public class Verse {
    public static void main(String[] args) {
        String greeting = "Good day user, I am Verse. Thy words await my wit.\n" +
                "Speak, and declare thy query.\n";
        String farewell = "Till next thou call’st this system forth, farewell.\n";

        System.out.println("ACT I - Scene 1");
        System.out.println(greeting + "\n");

        boolean proceed = true;

        Scanner sc = new Scanner(System.in);

        while (proceed) {
            System.out.print("User : ");
            String sentence = sc.nextLine();

            if (sentence.equals("bye")) {
                proceed = false;
            } else {
                System.out.println("Verse : " + sentence);
            }
        }

        sc.close();

        System.out.println();
        System.out.println("ACT I - Scene 2");
        System.out.println(farewell);
    }
}
