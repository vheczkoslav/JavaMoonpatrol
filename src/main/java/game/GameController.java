package game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class GameController {
    private DrawingThread timer;
    private Track track;
    private Score s;

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
                track.getS().setLeftMove(true);
                break;
            case KeyCode.D:
                track.getS().setRightMove(true);
                break;
            case KeyCode.W:
                track.getS().setJumpMove(true);
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
                track.getS().setLeftMove(false);
                break;
            case KeyCode.D:
                track.getS().setRightMove(false);
                break;
        }
    }

    @FXML
    void initialize() {
        root.setFocusTraversable(true);

        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'gameWindow.fxml'.";

        assert scoreText != null : "fx:id=\"scoreText\" was not injected: check your FXML file 'gameWindow.fxml'.";
        assert highScoreText != null : "fx:id=\"highScoreText\" was not injected: check your FXML file 'gameWindow.fxml'.";

        /*root.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((obs2, oldWin, newWin) -> {
                    if (newWin != null) {
                        root.requestFocus();   // now it receives key events
                    }
                });
            }
        });*/

        s = FileManager.load();

        if(s == null){
            s = new Score();
        }
        else{
            scoreText.setText("Score: " + String.valueOf(s.currentScore));
            highScoreText.setText("High score: " + String.valueOf(s.highScore));
        }

        track = new Track(canvas.getWidth(), canvas.getHeight(), s, this);
        timer = new DrawingThread(canvas, track);
        timer.start();

        Platform.runLater(() -> canvas.requestFocus());
    }

    public Score getScore() {
        return s;
    }

    public void setScoreText(){
        scoreText.setText(String.valueOf(s.currentScore));
    }

    public void stop() throws Exception {
        timer.stop();
    }
}
