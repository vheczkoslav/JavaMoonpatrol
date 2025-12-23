package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.geometry.Point2D;

public class HorizontalProjectile extends Projectile{
    private Image image;
    private int bounds;
    HorizontalProjectile(Point2D pos, int bounds) {
        super(pos);
        image = new Image(FrontHills.class.getResourceAsStream("missile.png"));
        this.delay = 500; // 0.5s
        this.bounds = bounds - (int)image.getWidth();
    }

    @Override
    public void simulate(double deltaTime) {
        pos = new Point2D(pos.getX() + speed, pos.getY());
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image, pos.getX(), pos.getY());
    }

    public boolean isOutOfBounds(){
        return pos.getX() > bounds;
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), image.getWidth(), image.getHeight());
    }
}
