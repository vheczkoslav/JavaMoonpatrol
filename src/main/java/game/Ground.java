package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ground {
    private Color groundColor = Color.web("#C06000");
    private Rectangle r;
    private short last = 0;
    private final short cap = 3;
    private short[] groundWaves;

    private Random rand = new Random();
    private long nextTick;

    private List<Stone> stones = new ArrayList<>();
    Track track;
    Ground(Track track, final int HEIGHT){
        r = new Rectangle(0, track.getSize().getHeight() - HEIGHT, track.getSize().getWidth(), HEIGHT);
        groundWaves = new short[(int)track.getSize().getWidth()/3];
        Arrays.fill(groundWaves, (short) 0);
        nextTick = System.currentTimeMillis() + rand.nextInt(5_000, 15_000);
        this.track = track;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(groundColor);
        gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        for(int i = groundWaves.length - 1; i >= 0; i--){
            gc.fillRect(i * 3, (int)r.getY() - groundWaves[i], 3, groundWaves[i]);
        }
        gc.setFill(Color.BLACK);

        for(Stone s : stones){
            s.draw(gc);
        }
    }

    public void simulate(double deltaTime){
        last += (short) (rand.nextInt(3) - 1); // -1, 0, nebo +1
        if (last > cap) last = cap;
        if (last < -cap) last = -cap;

        for (int i = 0; i < groundWaves.length - 1; i++) {
            groundWaves[i] = groundWaves[i + 1];
        }

        for(Stone s : stones) s.move(deltaTime);

        if(System.currentTimeMillis() > nextTick){
            stones.add(new Stone(new STONE_SIZE[]{STONE_SIZE.SMALL, STONE_SIZE.MEDIUM, STONE_SIZE.LARGE}[rand.nextInt(3)], (int)r.getWidth(), (int)r.getY()));
            nextTick = System.currentTimeMillis() + rand.nextInt(5_000, 15_000);
            track.addCollisionable(stones.getLast());
        }

        groundWaves[groundWaves.length - 1] = (short) (last * 3);

        for(Stone s : stones){
            if(s.isOutOfBounds()) track.removeCollisionable(s);
        }
    }
}
