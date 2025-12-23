package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class FlyingEnemy extends Collisionable {
    final int moveSpeed = 100;
    int widthLimit;
    FlyingEnemy(int widthLimit, FE_SPRITE fes){
        this.image = new Image[1];
        image[0] = new Image(FlyingEnemy.class.getResourceAsStream(fes == FE_SPRITE.YELLOW ? "flying_enemy.png" : "flying_enemy2.png"));
        pos = new Point2D(0, 20);
        this.widthLimit = widthLimit;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
    }

    @Override
    public void simulate(double deltaTime) {
        pos = pos.add(moveSpeed * deltaTime, 0);
    }

    public boolean checkBounds(){
        if(pos.getX() > widthLimit || pos.getX() < -image[0].getWidth()){
            return true;
        }
        return false;
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(pos.getX(), pos.getY(), image[0].getWidth(), image[0].getHeight());
    }
}
