package duke;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.MissingParameterException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    private final File file;

    public Storage(String folderPath, String fileName) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        file = new File(folder, fileName);
        if (!file.exists()) file.createNewFile();
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Parser parser = new Parser();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(",");
            Command c = Command.valueOf(s[0].toUpperCase());
            try {
                switch (c) {
                    case TODO -> tasks.add(new ToDo(s[1]));
                    case DEADLINE -> tasks.add(new Deadline(s[1], parser.parseDateTime(s[2])));
                    case EVENT -> tasks.add(new Event(s[1],
                                                      parser.parseDateTime(s[2]),
                                                      parser.parseDateTime(s[3])));
                }
            } catch (MissingParameterException e) {
                System.out.println("Verse : " + e.getMessage());
            }
        }

        sc.close();
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task t : tasks) {
                fw.write(t.fileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tasks", e);
        }
    }
}
