package game;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class Track {
    private Dimension2D size;
    private Ground ground;

    private static final int GROUND_HEIGHT = 48;
    private Score score;
    private Ship ship;

    private CollisionableManager collisionableManager;

    private final List<RenderEntity> r = new ArrayList<>();

    private GameController gameController;

    public Track(double width, double height, Score score, GameController gameController) {
        this.score = score;

        size = new Dimension2D(width, height);

        Rectangle groundRect = new Rectangle(0, size.getHeight() - GROUND_HEIGHT, getSize().getWidth(), GROUND_HEIGHT);

        ground = new Ground(this, groundRect);
        r.add(new BackgroundMountains(this, GROUND_HEIGHT));
        r.add(new FrontHills(this, GROUND_HEIGHT));

        collisionableManager = new CollisionableManager(this, GROUND_HEIGHT, score, groundRect, gameController);
        ship = collisionableManager.getShip();

        this.gameController = gameController;
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

    public Ship getS() {
        return ship;
    }

    public void shoot(){
        if(!ship.isDidJump()) collisionableManager.shootProjectiles();
    }
}
