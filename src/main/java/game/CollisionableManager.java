package game;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollisionableManager {
    List<Collisionable> collisionableList = new ArrayList<>();
    List<Collisionable> toRemove = new ArrayList<>();
    List<Collisionable> toAdd = new ArrayList<>();

    private GameController gameController;

    private Ship ship;

    private FlyingEnemyGenerator flyingEnemyGenerator;
    private ProjectileManager projectileManager;

    private double nextStone;
    private Random random = new Random();

    private Score score;
    private Rectangle groundRect;

    CollisionableManager(Track track, Score score, Rectangle groundRect, GameController gameController){
        flyingEnemyGenerator = new FlyingEnemyGenerator(this, (int)track.getSize().getWidth(), track);
        ship = new Ship(track, Track.groundHeight);
        this.groundRect = groundRect;
        collisionableList.add(ship);
        projectileManager = new ProjectileManager(track, Track.groundHeight, this);
        this.score = score;
        nextStone = System.currentTimeMillis() + random.nextInt(5_000, 15_000);
        this.gameController = gameController;
    }

    public void checkCollisions() {
        for (int i = 0; i < collisionableList.size() - 1; i++) {
            for (int j = i + 1; j < collisionableList.size(); j++) {
                Collisionable c1 = collisionableList.get(i);
                Collisionable c2 = collisionableList.get(j);
                if (c1 != c2 && c1.getBoundingBox().intersects(c2.getBoundingBox())) {
                    if ((c1 instanceof Ship && (c2 instanceof Stone || c2 instanceof FlyingEnemyProjectile))
                    || (c2 instanceof Ship && (c1 instanceof Stone || c1 instanceof  FlyingEnemyProjectile))) {
                        try {
                            gameController.switchToMenu();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if ((c1 instanceof HorizontalProjectile && c2 instanceof Stone)
                    || (c2 instanceof HorizontalProjectile && c1 instanceof Stone)) {
                        score.increaseScore(50);
                        toRemove.add(c1 instanceof Stone ? c1 : c2);
                        gameController.setScoreText();
                    }
                    if ((c1 instanceof VerticalProjectile && c2 instanceof FlyingEnemy)
                    || (c2 instanceof VerticalProjectile && c1 instanceof FlyingEnemy)) {
                        score.increaseScore(100);
                        toRemove.add(c1 instanceof FlyingEnemy ? c1 : c2);
                        gameController.setScoreText();
                    }
                    if((c1 instanceof Ship && c2 instanceof Crater) || (c2 instanceof Ship && c1 instanceof Crater)){
                        try {
                            gameController.switchToMenu();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
    public void draw(GraphicsContext gc){
        for(Collisionable c: collisionableList){
            c.draw(gc);
        }
    }
    public void simulate(double deltaTime){
        collisionableList.addAll(toAdd);
        toAdd.clear();

        flyingEnemyGenerator.simulate();
        ship.move(deltaTime);
        for(Collisionable c: collisionableList){
            c.simulate(deltaTime);
        }

        if(System.currentTimeMillis() > nextStone){
            nextStone = System.currentTimeMillis() + random.nextInt(5_000, 15_000);
            collisionableList.add(new Stone(new STONE_SIZE[]{STONE_SIZE.SMALL, STONE_SIZE.MEDIUM, STONE_SIZE.LARGE}[random.nextInt(3)], (int) groundRect.getWidth(), (int) groundRect.getY()));
        }

        for (Collisionable collisionable : collisionableList) {
            if (collisionable.isOutOfBounds()) {
                if(collisionable instanceof FlyingEnemyProjectile){
                    int x = (int)collisionable.getBoundingBox().getMinX();
                    addCollisionable(new Crater(new Point2D(x - 6, groundRect.getY() - 9)));
                }
                toRemove.add(collisionable);
            }
        }
        checkCollisions();

        collisionableList.removeAll(toRemove);
        toRemove.clear();
    }

    public void shootProjectiles(){
        projectileManager.shootProjectiles((int)ship.pos.getX());
    }

    public Ship getShip(){return ship;}

    public void addCollisionable(Collisionable c){
        toAdd.add(c);
    }

    public void clear() {
        collisionableList.clear();
        toRemove.clear();
    }
}
