package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Projectile extends Collisionable {
    protected Point2D pos;
    protected int delay; // delay between shots
    protected final int speed = 225;
    Projectile(Point2D pos){
        this.pos = pos;
    }
    public abstract void draw(GraphicsContext gc);
    public abstract Rectangle2D getBoundingBox();
}
