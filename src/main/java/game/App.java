package game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

/**
 * Class <b>App</b> - extends class Application and it is an entry point of the program
 *
 * @author Java I
 */

public class App extends Application {
        public static void main(String[] args) {
        launch(args);
    }

    private boolean state; // false = menuController, true = gameController
    private GameController gameController;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;

            switchToMenu();

            primaryStage.show();

            primaryStage.setOnCloseRequest(e -> {
                if(state){
                    if (!(new FileManager()).save(gameController.getScore())) {
                        System.out.println("Score data wasn't successfully saved");
                    } else {
                        System.out.println("Score data successfully saved");
                    }
                }
                Platform.exit();
                System.exit(0);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToGame() throws IOException {
        // Construct a main window with a canvas.
        state = true;
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("application.fxml"));
        Parent root = gameLoader.load();
        GameController gameController = gameLoader.getController();
        gameController.setApp(this);
        Scene scene = new Scene(root);
        URL cssUrl = getClass().getResource("application.css");
        scene.getStylesheets().add(cssUrl.toString());
        primaryStage.setScene(scene);
        this.gameController = gameController;
    }

    public void switchToMenu() throws IOException {
        // Construct a main window with a canvas.
        state = false;
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = menuLoader.load();
        MenuController menuController = menuLoader.getController();
        menuController.setApp(this);
        Scene scene = new Scene(root);
        URL cssUrl = getClass().getResource("application.css");
        scene.getStylesheets().add(cssUrl.toString());
        primaryStage.setScene(scene);
        if(gameController != null) gameController = null;
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
