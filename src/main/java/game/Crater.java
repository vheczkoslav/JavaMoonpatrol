package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Crater extends Collisionable {
    Random random;
    Crater(Point2D pos){
        this.pos = pos;
        random = new Random();
        short rand = (short)random.nextInt(3);
        this.image = new Image[1];
        if(rand == 0){
            image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("crater1.png"));
        }
        else if(rand == 1){
            image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("crater2.png"));
        }
        else{
            image[0] = new Image(FlyingEnemyProjectile.class.getResourceAsStream("crater3.png"));
        }
    }
    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), image[0].getWidth(), image[0].getHeight());
    }

    @Override
    public boolean isOutOfBounds() {
        return pos.getX() <= -image[0].getWidth();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
    }

    @Override
    public void simulate(double deltaTime) {
        pos = pos.add(-100 * deltaTime, 0);
    }
}
