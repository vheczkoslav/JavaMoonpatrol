package game;

import java.io.*;

public class FileManager {
    private final String FILE_PATH = "score.dat";  // or "data.ser"
    public boolean save(Score s) {
        File file = new File(FILE_PATH);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            s.checkScore();
            s.currentScore = 0;
            oos.writeObject(s);
            System.out.println("Saved successfully");
            return true;
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
            e.printStackTrace();  // ← This shows the real error!
            return false;
        }
    }
    public Score load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No saved score found (first run)");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Score) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load score: " + e.getMessage());
            return null;
        }
    }
}
