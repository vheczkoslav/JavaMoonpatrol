package game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawingThread extends AnimationTimer {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private Track track;
    private long lastFrame = 0;

    public DrawingThread(Canvas canvas, Track track) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.track = track;
    }

    /**
     * Draws objects into the canvas. Put you code here.
     */
    @Override
    public void handle(long now) {
        double delta = lastFrame == 0 ? 0 : (now - lastFrame) / 1_000_000_000D;
        lastFrame = now;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        track.draw(gc);

        //drawFps(delta);

        track.simulate(delta);
    }

    private void drawFps(double delta) {
        int fps = calcFps(delta);
        gc.setFont(new Font("Arial", 30));
        gc.setFill(Color.BLACK);
        gc.fillText(String.format("FPS: %04d", fps), 10, canvas.getHeight() - 10);
    }

    private double fpsSum = 0;
    private double fpsCount = 0;
    private int averageFps = 0;

    private int calcFps(double delta) {
        fpsSum += 1 / delta;
        fpsCount += 1;
        if (fpsCount >= 100) {
            averageFps = (int) (fpsSum / fpsCount);
            fpsSum = 0;
            fpsCount = 0;
        }
        return averageFps;
    }

}
