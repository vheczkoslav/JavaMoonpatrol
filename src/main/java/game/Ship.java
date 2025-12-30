package game;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Collisionable {
    private List<Wheel> wheels = new ArrayList<>();

    private boolean leftMove = false, rightMove = false, didJump = false;
    private int defaultY;
    private final int JUMP_HEIGHT = 80, JUMP_DURATION = 1;
    private final int moveSpeed = 10;
    private double jumpTimer;

    public void setLeftMove(boolean t){
        if(rightMove && t) rightMove = false;
        leftMove = t;
    }

    public void setRightMove(boolean t){
        if(leftMove && t) leftMove = false;
        rightMove = t;
    }

    public void setJumpMove(boolean t){
        if(!didJump && t){
            didJump = true;
            jumpTimer = 0;
        }
    }

    Ship(Track track, int GROUND_HEIGHT){
        super(track);
        image = new Image[1];
        image[0] = new Image(FrontHills.class.getResourceAsStream("ship.png"));
        // kvuli pneumatikam
        int yOffset = 4;
        defaultY = (int)((track.getSize().getHeight() - GROUND_HEIGHT) - image[0].getHeight() - yOffset);
        pos = new Point2D(0, defaultY);
        final short WHEEL_COUNT = 3;
        for(int i = 0; i < WHEEL_COUNT; i++){
            wheels.add(new Wheel(track, GROUND_HEIGHT, (int)pos.getX() + 5 + (i * 25)));
        }
    }

    @Override
    public void simulate(double deltaTime) {
        if(!didJump){
            for (Wheel wheel : wheels) {
                wheel.simulate(deltaTime);
            }
        }
        else{
            double normalizedTime = jumpTimer / JUMP_DURATION;
            double height;
            if(normalizedTime <= 1.0){
                height = Math.sin(normalizedTime * Math.PI) * JUMP_HEIGHT;
                pos = new Point2D(pos.getX(), defaultY - height);
                jumpTimer += deltaTime;
                for(Wheel wheel : wheels){
                    wheel.simulate(deltaTime, height);
                }
            }
            else{
                pos = new Point2D(pos.getX(), defaultY);
                didJump = false;
                for(Wheel wheel : wheels) wheel.reset();
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
        for (Wheel wheel : wheels) {
            wheel.draw(gc);
        }
    }

    public void move(){
        if(leftMove && pos.getX() > 0){
            pos = new Point2D(pos.getX() - moveSpeed * 0.75, pos.getY());
            for(Wheel w: wheels){
                w.move(false);
            }
        }
        if(rightMove && pos.getX() < track.getSize().getWidth() / 1.5){
            pos = new Point2D(pos.getX() + moveSpeed, pos.getY());
            for(Wheel w: wheels){
                w.move(true);
            }
        }
    }

    public boolean isDidJump(){
        return didJump;
    }

    public Rectangle2D getBoundingBox(){
        return new Rectangle2D(pos.getX(), pos.getY(), image[0].getWidth(), image[0].getHeight());
    }

    @Override
    public boolean isOutOfBounds() {
        return false;
    }
}
