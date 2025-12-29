package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MenuController {
    private App app;

    @FXML
    private TextArea menuText;

    @FXML
    private Button gameToggle;

    public void changeText(){ // jiz po prvni smrti
        menuText.setText("You died! Try again :P");
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void switchGame(ActionEvent event) {
        try {
            app.switchToGame();
        } catch (Exception e) {
            System.out.println("Something went horribly wrong :(");
        }
    }
}
