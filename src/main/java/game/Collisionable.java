package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public abstract class Collisionable implements Renderable {
    public abstract Rectangle2D getBoundingBox();
    Track track;
    Point2D pos;
    Image[] image;
    Collisionable(Track track){
        this.track = track;
    }
    Collisionable(){};
}
