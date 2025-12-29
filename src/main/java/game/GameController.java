package game;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameController {
    private DrawingThread timer;
    private Track track;
    private Score s;
    private App app;

    @FXML
    private Text highScoreText;

    @FXML
    private Text scoreText;

    /*@FXML
    private Text timeText;

    @FXML
    private Text livesText;*/

    @FXML
    private BorderPane root;

    @FXML
    private Canvas canvas;

    @FXML
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case KeyCode.A:
                track.getShip().setLeftMove(true);
                break;
            case KeyCode.D:
                track.getShip().setRightMove(true);
                break;
            case KeyCode.W:
                track.getShip().setJumpMove(true);
                break;
            case KeyCode.SPACE:
                track.shoot();
                break;
        }
    }

    @FXML
    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case KeyCode.A:
                track.getShip().setLeftMove(false);
                break;
            case KeyCode.D:
                track.getShip().setRightMove(false);
                break;
        }
    }

    @FXML
    void initialize() {
        root.setFocusTraversable(true);

        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'gameWindow.fxml'.";

        assert scoreText != null : "fx:id=\"scoreText\" was not injected: check your FXML file 'gameWindow.fxml'.";
        assert highScoreText != null : "fx:id=\"highScoreText\" was not injected: check your FXML file 'gameWindow.fxml'.";

        s = new FileManager().load();

        if(s == null){
            s = new Score();
        }
        setScoreText();
        highScoreText.setText("High score: " + String.valueOf(s.highScore));


        track = new Track(canvas.getWidth(), canvas.getHeight(), s, this);
        timer = new DrawingThread(canvas, track);
        timer.start();

        Platform.runLater(() -> canvas.requestFocus());
    }

    public Score getScore() {
        return s;
    }

    public void setScoreText(){
        scoreText.setText("Score: " + String.valueOf(s.currentScore));
    }

    public void switchToMenu() throws IOException {
        app.switchToMenu();
    }

    public void setApp(App app){
        this.app = app;
    }

    public void stop() throws Exception {
        timer.stop();
    }
}
