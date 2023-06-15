package nl.rug.ai.oop.rpg.model.location;

import java.io.*;

/**
 * This class provides functionality to save and load the state of the rooms using serialization.
 */
public class RoomStateManager {

    /**
     * Saves the state of the room manager to a file.
     *
     * @param locationManager The LocationManager object containing the rooms state to be saved.
     * @param fileName        The name of the file to save the room state to.
     */
    public static void saveRoomState(LocationManager locationManager, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(locationManager);
            out.close();
            fileOut.close();
            System.out.println("Room state saved successfully!!");
        } catch (IOException e) {
            System.out.println("Error saving room state: " + e.getMessage());
        }
    }

    /**
     * Loads the state of the rooms manager from a file.
     *
     * @param fileName The name of the file to load the room state from.
     * @return The LocationManager object containing the loaded room state, or null if loading failed.
     */
    public static LocationManager loadRoomState(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            LocationManager locationManager = (LocationManager) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Room state loaded successfully!!");
            return locationManager;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading room state: " + e.getMessage());
            return null;
        }
    }
}
