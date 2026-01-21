package game;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class GroundWave {
    private Point2D position;
    final private Rectangle rectangle;
    private final int moveBy = 125; // pixels per second when in combination with deltaTime
    private boolean isOut = false;

    GroundWave(Point2D position, Rectangle rectangle){
        this.position = position;
        this.rectangle = rectangle;
    }

    void simulate(double deltaTime){
        position = position.add(-moveBy * deltaTime, 0);
        if(position.getX() <= -moveBy){
            isOut = true;
        }
    }

    void draw(GraphicsContext gc){
        gc.fillRect(position.getX(), position.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    boolean isOutOfBounds(){
        return isOut;
    }

    int getX(){
        return (int)position.getX();
    }
}
