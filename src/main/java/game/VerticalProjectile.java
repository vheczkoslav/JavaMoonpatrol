package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class VerticalProjectile extends Projectile{
    private int height = 8, width = 2;
    VerticalProjectile(Point2D pos) {
        super(pos);
        this.pos = pos;
        this.delay = 200; // 0.2s
    }

    @Override
    public void simulate(double deltaTime) {
        pos = new Point2D(pos.getX(), pos.getY() - speed);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255 ,255));
        gc.fillRect(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public boolean isOutOfBounds() {
        return pos.getY() < 0;
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), width, height);
    }
}
