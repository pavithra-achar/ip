package duke;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.MissingParameterException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {
    private final File file;

    public Storage(String folderPath, String fileName) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        file = new File(folder, fileName);
        if (!file.exists()) file.createNewFile();
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Parser parser = new Parser();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(",");
            Command c = Command.valueOf(s[0].toUpperCase());
            try {
                switch (c) {
                    case TODO -> tasks.add(new ToDo(s[1].trim()));
                    case DEADLINE -> tasks.add(new Deadline(s[1].trim(), parser.parseDateTime(s[2].trim())));
                    case EVENT -> tasks.add(new Event(s[1].trim(),
                                                      parser.parseDateTime(s[2].trim()),
                                                      parser.parseDateTime(s[3].trim())));
                }
            } catch (MissingParameterException e) {
                System.out.println("Verse : " + e.getMessage());
            }
        }

        sc.close();
        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     * 
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task t : tasks) {
                fw.write(t.getFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tasks", e);
        }
    }
}
