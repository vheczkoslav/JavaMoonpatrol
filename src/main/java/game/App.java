package game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class <b>App</b> - extends class Application and it is an entry point of the program
 *
 * @author Java I
 */

public class App extends Application {
        public static void main(String[] args) {
        launch(args);
    }

    private GameController gameController;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/game/application.fxml"));
            Parent root = gameLoader.load();
            gameController = gameLoader.getController();
            CustomScene scene = new CustomScene(root);

            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Java 1 - 6th laboratory");
            primaryStage.show();

            primaryStage.setOnCloseRequest(e -> {
                if (!FileManager.save(gameController.getScore())) {
                    System.out.println("Score data wasn't successfully saved");
                } else {
                    System.out.println("Score data successfully saved");
                }
                Platform.exit();
                System.exit(0);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        gameController.stop();
        super.stop();
    }

    private void exitProgram(WindowEvent evt) {
        System.exit(0);
    }
}
