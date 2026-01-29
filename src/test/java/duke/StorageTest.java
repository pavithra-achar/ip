package duke;

import static org.junit.jupiter.api.Assertions.*;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;

public class StorageTest {

    private File testFile;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        File folder = new File("./testData");
        folder.mkdirs();

        testFile = new File(folder, "testVerse.txt");
        storage = new Storage("./testData", "testVerse.txt");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("todo,Read book" + System.lineSeparator());
            fw.write("deadline,Submit report,12-12-2012 23:59");
        } catch (IOException e) {
            System.out.println("Error creating test file: " + e.getMessage());
        }
    }

    @AfterEach
    void cleanUp() {
        testFile.delete();
        new File("./testData").delete();
    }

    @Test
    void loadTasks_validFile_tasksLoadedCorrectly() throws IOException {
        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(2, tasks.size());
        assertInstanceOf(ToDo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));

        assertEquals("Read book", tasks.get(0).getDescription());
        assertEquals("Submit report", tasks.get(1).getDescription());
    }

    @Test
    void loadTasks_corruptedFile_throwsException() {
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("deadline, Bad date task, not-a-date\n");
            ArrayList<Task> tasks = storage.loadTasks();
            assertEquals(0, tasks.size());
        } catch (IOException e) {
            System.out.println("Error during test: " + e.getMessage());
        }
    }
}
