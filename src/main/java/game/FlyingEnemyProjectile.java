package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FlyingEnemyProjectile extends Projectile{
    private boolean direction = false; // false - south, true - southeast
    private final int heightLimit;
    public FlyingEnemyProjectile(Point2D pos, boolean direction, int heightLimit) {
        super(pos);
        this.image = new Image[2];
        this.direction = direction;
        this.heightLimit = heightLimit;
        image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("fly_missile_s.png"));
        image[1] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("fly_missile_se.png"));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[direction ? 1 : 0], pos.getX(), pos.getY());
    }

    @Override
    public void simulate(double deltaTime) {
        if(direction){
            pos = pos.add(new Point2D(4, 5));
        }
        else{
            pos = pos.add(new Point2D(0, 5));
        }
    }

    @Override
    public boolean isOutOfBounds() {
        return pos.getY() > heightLimit - (direction ? image[1].getHeight() : image[0].getHeight());
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), (direction ? image[1].getWidth() : image[0].getWidth()), (direction ? image[1].getWidth() : image[0].getWidth()));
    }
}
