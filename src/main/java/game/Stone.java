package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.geometry.Point2D;

import java.awt.*;

public class Stone extends Collisionable{
    Image i;
    Point2D pos;
    public Stone(STONE_SIZE stoneSize, int trackWidth, int groundHeight){
        switch(stoneSize){
            case SMALL -> i = new Image(Stone.class.getResourceAsStream("stone-S.png"));
            case MEDIUM -> i = new Image(Stone.class.getResourceAsStream("stone-M.png"));
            case LARGE -> i = new Image(Stone.class.getResourceAsStream("stone-L.png"));
        }
        int offset = (stoneSize == STONE_SIZE.MEDIUM || stoneSize == STONE_SIZE.LARGE) ? 2 : 0;
        pos = new Point2D(trackWidth - i.getWidth(), groundHeight - i.getHeight() + offset);
    }
    public void draw(GraphicsContext gc){
        gc.drawImage(i, pos.getX(), pos.getY());
    }
    public void simulate(double deltaTime){
        pos = new Point2D(pos.getX() - 250 * deltaTime, pos.getY());
    }
    public boolean isOutOfBounds(){
        return (pos.getX() < -i.getWidth());
    }
    public Rectangle2D getBoundingBox(){
        return new Rectangle2D(pos.getX(), pos.getY(), i.getWidth(), i.getHeight() - 2);
    }
}
