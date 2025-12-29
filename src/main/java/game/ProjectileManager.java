package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class ProjectileManager {
    private CollisionableManager collisionableManager;

    private int horizontalDelay = 500, verticalDelay = 200;
    private double lastHorizontal, lastVertical;
    private int verticalInitialYPos, horizontalXBounds;
    public ProjectileManager(Track t, int groundHeight, CollisionableManager collisionableManager){
        lastHorizontal = System.currentTimeMillis();
        lastVertical = System.currentTimeMillis();
        verticalInitialYPos = (int)t.getSize().getHeight() - groundHeight - 32;
        horizontalXBounds = (int)t.getSize().getWidth();
        this.collisionableManager = collisionableManager;
    }
    public void shootProjectiles(int shipX){
        if(System.currentTimeMillis() > lastVertical+verticalDelay){
            VerticalProjectile vp = new VerticalProjectile(new Point2D(shipX + 38, verticalInitialYPos));
            collisionableManager.addCollisionable(vp);
            lastVertical = System.currentTimeMillis();
        }
        if(System.currentTimeMillis() > lastHorizontal+horizontalDelay){
            HorizontalProjectile hp = new HorizontalProjectile(new Point2D(shipX + 80, verticalInitialYPos + 12), horizontalXBounds);
            collisionableManager.addCollisionable(hp);
            lastHorizontal = System.currentTimeMillis();
        }
    }
}
