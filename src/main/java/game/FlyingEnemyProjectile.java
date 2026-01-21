package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FlyingEnemyProjectile extends Projectile{
    private final int heightLimit;
    private short direction; // sw - 0, s - 1, se - 2
    public FlyingEnemyProjectile(Point2D pos, int heightLimit, int widthLimit) {
        super(pos);
        this.image = new Image[2];
        this.heightLimit = heightLimit;
        if((int)pos.getX() < widthLimit / 3){
            image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("fly_missile_se.png"));
            direction = 2;
        }
        else if((int)pos.getX() >= widthLimit / 3 && (int)pos.getX() < (widthLimit / 3) * 2){
            image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("fly_missile_s.png"));
            direction = 1;
        }
        else{
            image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("fly_missile_sw.png"));
            direction = 0;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
    }

    @Override
    public void simulate(double deltaTime) {
        if(direction == 0){
            pos = pos.add(new Point2D(-160 * deltaTime, 200 * deltaTime));
        }
        else if(direction == 1){
            pos = pos.add(new Point2D(0, 200 * deltaTime));
        }
        else{
            pos = pos.add(new Point2D(160 * deltaTime, 200 * deltaTime));
        }
    }

    @Override
    public boolean isOutOfBounds() {
        return pos.getY() > heightLimit - image[0].getHeight();
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), image[0].getWidth(), image[0].getHeight());
    }
}
