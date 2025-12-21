package game;

import java.io.*;

public class FileManager {
    public static boolean save(Score s) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.csv"))) {
            oos.writeObject(s);
            //oos.flush();
            return true;
        }
        catch(IOException e){
            System.out.println("Couldn't create object output stream");
            return false;
        }
    }
    public static Score load() throws Exception {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.csv"))){
            Score s = (Score)ois.readObject();
            return s;
        } catch (IOException e) {
            System.out.println("Could not load score");
            return null;
        }
    }
}
