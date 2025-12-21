package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class ProjectileManager {
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Projectile> toRemove = new ArrayList<>();

    Track t;

    private int horizontalDelay = 500, verticalDelay = 200;
    private double lastHorizontal, lastVertical;
    private int verticalInitialYPos, horizontalXBounds;
    public ProjectileManager(int GROUND_HEIGHT, Track t){
        lastHorizontal = System.currentTimeMillis();
        lastVertical = System.currentTimeMillis();
        verticalInitialYPos = (int)t.getSize().getHeight() - GROUND_HEIGHT - 32;
        horizontalXBounds = (int)t.getSize().getWidth();
        this.t = t;
    }
    public void shootProjectiles(int shipX){
        if(System.currentTimeMillis() > lastVertical+verticalDelay){
            VerticalProjectile vp = new VerticalProjectile(new Point2D(shipX + 38, verticalInitialYPos));
            projectiles.add(vp);
            lastVertical = System.currentTimeMillis();
            t.addCollisionable(vp);
        }
        if(System.currentTimeMillis() > lastHorizontal+horizontalDelay){
            HorizontalProjectile hp = new HorizontalProjectile(new Point2D(shipX + 80, verticalInitialYPos + 8), horizontalXBounds);
            projectiles.add(hp);
            lastHorizontal = System.currentTimeMillis();
            t.addCollisionable(hp);
        }
    }
    public void draw(GraphicsContext gc){
        for(Projectile p : projectiles){
            p.draw(gc);
        }
    }
    public void simulate() {
        for (Projectile p : projectiles) {
            p.move();
        }

        for (Projectile p : projectiles) {
            if (p.isOutOfBounds()) {
                toRemove.add(p);
                t.removeCollisionable(p);
            }
        }

        projectiles.removeAll(toRemove);

        toRemove.clear();
    }
}
