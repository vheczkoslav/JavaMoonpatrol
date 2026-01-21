package game;

import javafx.geometry.Point2D;
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
    private Rectangle rectangle;
    private short lastHeight = 0, lastWidth = 1;
    private final short minHeightCap = 0, maxHeightCap = 3;
    private final short minWidthCap = 1, maxWidthCap = 4;
    private List<GroundWave> groundWaves;

    private Random rand = new Random();

    Track track;
    Ground(Track track, Rectangle r){
        this.rectangle = r;
        this.groundWaves = new ArrayList<>();
        this.track = track;

        int height = (short) (rand.nextInt(3) + 1);
        int width = (short) (rand.nextInt(4) + 1);
        int realWidth = width * maxWidthCap;
        int realHeight = height * maxHeightCap;
        groundWaves.add(new GroundWave(new Point2D(
                rectangle.getWidth() - realWidth, rectangle.getY() - realHeight),
                new Rectangle(realWidth, realHeight)
        ));
    }

    public void draw(GraphicsContext gc){
        gc.setFill(groundColor);
        gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        groundWaves.forEach((groundWave -> groundWave.draw(gc)));
        gc.setFill(Color.BLACK);
    }

    public void simulate(double deltaTime){
        if(!groundWaves.isEmpty() && groundWaves.getLast().getX() <= rectangle.getWidth() - lastWidth){
            lastHeight += (short) (rand.nextInt(3) - 1); // -1, 0, or +1
            lastWidth += (short) (rand.nextInt(5) - 2); // 2 to -2
            if (lastHeight > maxHeightCap) lastHeight = maxHeightCap;
            if (lastHeight < minHeightCap) lastHeight = minHeightCap;
            if (lastWidth > maxWidthCap) lastWidth = maxWidthCap;
            if (lastWidth < minWidthCap) lastWidth = minWidthCap;

            for(int i = 0 ; i < groundWaves.size() - 1 ; i++){
                groundWaves.get(i).simulate(deltaTime);
            }
            if(groundWaves.getLast().isOutOfBounds()) groundWaves.removeLast();

            int realWidth = lastWidth * maxWidthCap;
            int realHeight = lastHeight * maxHeightCap;
            if(lastHeight > 0) {
                groundWaves.add(new GroundWave(new Point2D(
                        rectangle.getWidth() - realWidth, rectangle.getY() - realHeight),
                        new Rectangle(realWidth, realHeight)
                ));
            }
        }
    }
}
