package game;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class Track {
    private Dimension2D size;
    private Ground ground;

    private Ship ship;

    private CollisionableManager collisionableManager;
    public final static int groundHeight = 48;

    private final List<RenderEntity> r = new ArrayList<>();

    public Track(double width, double height, Score score, GameController gameController) {
        size = new Dimension2D(width, height);

        Rectangle groundRect = new Rectangle(0, size.getHeight() - groundHeight, getSize().getWidth(), groundHeight);

        ground = new Ground(this, groundRect);
        r.add(new BackgroundMountains(this, groundHeight));
        r.add(new FrontHills(this, groundHeight));

        collisionableManager = new CollisionableManager(this, score, groundRect, gameController);
        ship = collisionableManager.getShip();
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,size.getWidth(),size.getHeight());
        r.forEach(e -> e.draw(gc));
        collisionableManager.draw(gc);
        ground.draw(gc);
    }

    public void simulate(double deltaTime) {
        ground.simulate(deltaTime);
        r.forEach(e -> e.simulate(deltaTime));
        collisionableManager.simulate(deltaTime);
    }

    public Dimension2D getSize(){
        return size;
    }

    public Ship getShip() {
        return ship;
    }

    public void shoot(){
        if(!ship.isDidJump()) collisionableManager.shootProjectiles();
    }

    public void cleanup() {
        collisionableManager.clear();
    }
}
