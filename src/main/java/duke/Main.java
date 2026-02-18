package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Verse verse = new Verse();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Verse");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/verse.png"));
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setVerse(verse);
            stage.show();

            stage.setOnCloseRequest(windowEvent -> verse.handleInput("bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
