package game;

import java.io.*;

public class FileManager {
    private static final String FILE_PATH = "score.dat";  // or "data.ser"
    public static boolean save(Score s) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            System.out.println(s.checkScore() ? "New high score" : "Not new high score");
            oos.writeObject(s);
            oos.flush();
            return true;
        }
        catch(IOException e){
            System.out.println("Couldn't create object output stream");
            return false;
        }
    }
    public static Score load() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            return new Score((Score) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load score");
            return null;
        }
    }
}
