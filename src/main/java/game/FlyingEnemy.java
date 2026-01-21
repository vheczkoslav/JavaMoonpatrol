package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.Random;

public class FlyingEnemy extends Collisionable {
    final int moveSpeed = 100;
    int widthLimit, heightLimit;
    double nextProjectile;
    Random random = new Random();
    private final CollisionableManager collisionableManager;
    FlyingEnemy(int widthLimit, int heightLimit, FE_SPRITE fes, CollisionableManager collisionableManager){
        this.image = new Image[1];
        image[0] = new Image(FlyingEnemy.class.getResourceAsStream(fes == FE_SPRITE.YELLOW ? "flying_enemy.png" : "flying_enemy2.png"));
        pos = new Point2D(0, 20);
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;
        nextProjectile = System.currentTimeMillis() + random.nextInt(500, 3000);
        this.collisionableManager = collisionableManager;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
    }

    @Override
    public void simulate(double deltaTime) {
        pos = pos.add(moveSpeed * deltaTime, 0);
        if(System.currentTimeMillis() > nextProjectile){
            collisionableManager.addCollisionable(
                    new FlyingEnemyProjectile(
                            new Point2D(pos.getX() + image[0].getWidth(), pos.getY() + image[0].getHeight() + 1),
                            heightLimit, widthLimit
                    )
            );
            nextProjectile = System.currentTimeMillis() + random.nextInt(1000, 4000);
        }
    }

    public boolean isOutOfBounds(){
        return pos.getX() > widthLimit;
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), image[0].getWidth(), image[0].getHeight());
    }
}
