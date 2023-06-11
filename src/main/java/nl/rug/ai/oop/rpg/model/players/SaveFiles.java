package nl.rug.ai.oop.rpg.model.players;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RobertHielkema
 */
public class SaveFiles {

    private static SaveFiles saveFiles;
    private List<String> fileNames;

    private SaveFiles() {
        fileNames = new ArrayList<>();
    }

    public static SaveFiles getInstance(){
        if (saveFiles == null) {
            saveFiles = new SaveFiles();
        }
        return saveFiles;
    }

    public void saveFileNames(String filename){
        if(!fileNames.contains(filename)){
            fileNames.add(filename);
        }
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("SaveFiles"))) {
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void loadFileNames(){
        saveFiles = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("SaveFiles"))) {
            saveFiles = (SaveFiles) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
