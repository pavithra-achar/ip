package duke;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Verse verse;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/mask-removebg-preview.png"));
    private final Image verseImage = new Image(this.getClass().getResourceAsStream("/images/verse.png"));

    /** Initializes the main window and binds the scroll pane to the height of the dialog container. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /** Injects the Verse instance */
    public void setVerse(Verse v) {
        verse = v;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(verse.greet(), verseImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Verse's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();

        String response = verse.handleInput(input);
        if (response.startsWith("err")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response.substring(3), verseImage)
            );
        } else if (response.startsWith("list")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getListDialog(response.substring(4), verseImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, verseImage)
            );
        }

        // Exit the application if the user inputs "bye"
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> System.exit(0));
            pause.play(); // Start the pause
        }
        userInput.clear();
    }
}
