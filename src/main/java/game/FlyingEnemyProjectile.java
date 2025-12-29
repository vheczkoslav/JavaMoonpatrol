package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class FlyingEnemyProjectile extends Projectile{
    FlyingEnemyProjectile(Point2D pos) {
        super(pos);
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public void simulate(double deltaTime) {

    }

    @Override
    public boolean isOutOfBounds() {
        return false;
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return null;
    }
}
