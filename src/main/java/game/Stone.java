package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.geometry.Point2D;

import java.awt.*;

public class Stone extends Collisionable {
    public Stone(STONE_SIZE stoneSize, int trackWidth, int groundHeight){
        image = new Image[1];
        switch(stoneSize){
            case SMALL -> image[0] = new Image(Stone.class.getResourceAsStream("stone-S.png"));
            case MEDIUM -> image[0] = new Image(Stone.class.getResourceAsStream("stone-M.png"));
            case LARGE -> image[0] = new Image(Stone.class.getResourceAsStream("stone-L.png"));
        }
        int offset = (stoneSize == STONE_SIZE.MEDIUM || stoneSize == STONE_SIZE.LARGE) ? 2 : 0;
        pos = new Point2D(trackWidth - image[0].getWidth(), groundHeight - image[0].getHeight() + offset);
    }
    public void draw(GraphicsContext gc){
        gc.drawImage(image[0], pos.getX(), pos.getY());
    }
    public void simulate(double deltaTime){
        pos = new Point2D(pos.getX() - 250 * deltaTime, pos.getY());
    }
    public boolean isOutOfBounds(){
        return (pos.getX() < -image[0].getWidth());
    }
    public Rectangle2D getBoundingBox(){
        return new Rectangle2D(pos.getX(), pos.getY(), image[0].getWidth(), image[0].getHeight() - 2);
    }
}
