package game;

import java.io.*;

public class FileManager {
    private static final String FILE_PATH = "score.dat";  // or "data.ser"
    public static boolean save(Score s) {
        File file = new File(FILE_PATH);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(s);
            System.out.println("Saved successfully");
            return true;
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
            e.printStackTrace();  // ← This shows the real error!
            return false;
        }
    }
    public static Score load() {
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
