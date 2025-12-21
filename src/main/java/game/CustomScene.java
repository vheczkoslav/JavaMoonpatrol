package game;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.event.KeyListener;

public class CustomScene extends Scene {
    KeyListener keyListener;
    public CustomScene(Parent parent) {
        super(parent);
    }
    public void addKeyListener(KeyListener keyListener){
        this.keyListener = keyListener;
    }
}
