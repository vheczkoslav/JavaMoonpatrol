package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Projectile implements Collisionable {
    protected Point2D pos;
    protected int delay; // delay between shots
    protected final int speed = 5;
    Projectile(Point2D pos){
        this.pos = pos;
    }
    public abstract void move();
    public abstract void draw(GraphicsContext gc);
    public abstract boolean isOutOfBounds();
    public abstract Rectangle2D getBoundingBox();
}
