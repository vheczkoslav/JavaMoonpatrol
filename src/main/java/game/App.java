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
            //Construct a main window with a canvas.
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/game/application.fxml"));
            Parent root = gameLoader.load();
            gameController = gameLoader.getController();
            CustomScene scene = new CustomScene(root);

            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Java 1 - 6th laboratory");
            primaryStage.show();
            //Exit program when main window is closed
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Platform.exit();
                    try {
                        if(FileManager.save(gameController.getS())){
                            System.out.println("Score data succesfully saved");
                        }
                        else{
                            System.out.println("Score data wasn't succesfully saved");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                }
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
